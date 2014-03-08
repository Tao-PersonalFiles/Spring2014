/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package genetic_programming;

import java.util.Random;

/**
 *
 * @author 张涛
 */
public class Individual{
    // root node pointer. Be null for empty tree;
    public Node root;
    
    public int terms;
    public int non_terms;
    
    // creates an empty binary tree
    public void Individual(int max){
        root = null;
        generate(max);
    }
    
    public boolean isEmpty(){
        return root == null;
    }
    
    public void generate(int max){
        Node n = new Node();
        root = n;   // point root to new start node
        full(0, max, n);
    }
    
    // creates full trees 
    public void full(int depth, int max, Node parent){
        Random randomGenerator = new Random();
        Node p_copy = parent;
        if(depth >= max){   // 4 for X, 5 for const_value
            parent.type = 4 + randomGenerator.nextInt(2);
        }else{  // 0 = +, 1 = -, 2 = *, 3 = /
            parent.type = randomGenerator.nextInt(4);
                // generate left leaf
            Node l = new Node();
            parent.left = l;
            l.parent = p_copy;
            full(depth+1,max,l);
                //generate right leaf
            Node r = new Node();
            parent.right = r;
            r.parent = p_copy;
            full(depth+1,max,r);
        }
        
        if(parent.type == 5){ // random const_value 0~9
            parent.const_value = randomGenerator.nextInt(10);
        }
    }
    
    public void erase(){
        deleteNode(root);   // point to root and start delete
        root = null;
    }
    
    public void deleteNode(Node n){
            /* if non_terminal, keep tracing the leaves
             * until we delete terminals
             */
        if(n.type < 4){
            if(n.left != null)
                deleteNode(n.left);
            
            if(n.right != null)
                deleteNode(n.right);
        }
            // delete each node
        n.left = null; 
        n.right = null;
        n.parent = null;
    }
    
    public void copy(Node source){
        Node n = new Node();
        root = n;
        copyNode(n,source);
    }
    
    public void copyNode(Node self, Node source){        
        self.type = source.type;
        self.const_value = source.const_value;
        if(source.type < 4){
            if(source.left != null){
                Node l = new Node();
                self.left = l;
                copyNode(l, source.left);
            }
            
            if(source.right != null){
                Node r = new Node();
                self.right = r;
                copyNode(r, source.right);
            }
        }
    }
    
    public double fitness(double X){
        double z;
        z = evaluate(X, root);
        return z;
    }
    
    public double evaluate(double X, Node node){
        double l;
        double r;
        switch(node.type){
            case 0: // +
                l = evaluate(X, node.left);
                r = evaluate(X, node.right);
                return l+r;
            case 1: // -
                l = evaluate(X, node.left);
                r = evaluate(X, node.right);
                return l-r;
            case 2: // *
                l = evaluate(X, node.left);
                r = evaluate(X, node.right);
                return l*r;
            case 3: // /
                l = evaluate(X, node.left);
                r = evaluate(X, node.right);
                if(r == 0){
                    return 0;
                }else{
                    return l/r;
                }
            case 4: // X
                return X;
            case 5: // const_value
                return node.const_value;
            default:
                System.out.println("Error, unknown instruction");
                
        }
        return -1;
    }
    
    public void calc_size(){
        terms = 0;
        non_terms = 0;              
        count(root);
    }
    
    public void count(Node n){
        if(n.type >= 4){
            terms++;
        }else {
            non_terms++;
            count(n.left);
            count(n.right);
        }
    }
    
    public void printTree(){
        if(isEmpty())
            System.out.println("The tree is emtpy");
        else{
            printNode(root);
            System.out.println(" = ?");
        }
    }
    
    public void printNode(Node n){
        if(n.left != null){
            printNode(n.left);
        }
        
        switch(n.type){
            case 0:
                System.out.printf(" + ");
                break;
            case 1:
                System.out.printf(" - ");
                break;
            case 2:
                System.out.printf(" * ");
                break;
            case 3: 
                System.out.printf(" / ");
                break;
            case 4:
                System.out.printf("X");
                break;
            case 5:
                System.out.printf("%.2f", n.const_value);
                break;
            default:
                System.err.println("Error: unknown node type");
                break;
        }
        
        
        if(n.right != null){
            printNode(n.right);
        }
    }
    
}

