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
public class Tree {
    
    // root node pointer. Be null for empty tree;
    private Node root;
    
    public int terms;
    public double non_terms;
    
    // creates an empty binary tree
    public void Tree(){
        root = null;
        
        terms = 0;
        non_terms = 0;
    }
    
    public void generate(int depth, int max){
        Node n = new Node();
        root = n;
        full(depth, max, n);
    }
    
    public void full(int depth, int max, Node parent){
        
        Random randomGenerator = new Random();
        Node p_copy = parent;
        if(depth >= max){
            parent.type = 4 + randomGenerator.nextInt(2);
        }else{
            parent.type = randomGenerator.nextInt(4);
            Node l = new Node();
            parent.left = l;
            l.parent = p_copy;
            full(depth+1,max,l);
            
            Node r = new Node();
            parent.right = r;
            r.parent = p_copy;
            full(depth+1,max,r);
        }
        
        if(parent.type == 5){
            parent.const_value = randomGenerator.nextInt(10);
        }
    }
    
    public void erase(){
        delete(root);
    }
    
    public void delete(Node n){
        if(n.type < 4){
            
        }
    }
    
    public void copy(Node source){
        Node source_copy = new Node();
        source_copy.type = source.type;
        source_copy.const_value = source.const_value;
        if(source_copy.type < 4){
            if(source.left != null){
                copy(source.left);
            }else{
                source.left = null;
            }
            
            if(source.right != null){
                copy(source.right);
            }else{
                source.right = null;
            }
        }
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
    
    public void calc_size(Node n){
        if(n.type >= 4){
            terms++;
        }else {
            non_terms++;
            calc_size(n.left);
            calc_size(n.right);
        }
    }
    
    public void printTree(){
        printNode(root);
    }
    
    public void printNode(Node n){
        if(n.type == 4){
            System.out.println("X");
        }else if(n.type == 5){
            System.out.println(n.const_value);
        }else if(n.type < 4){
            printNode(n.left);
            switch(n.type){
                case 0:
                    System.out.println(" + ");
                case 1:
                    System.out.println(" - ");
                case 2:
                    System.out.println(" * ");
                case 3: 
                    System.out.println(" / ");
                default:
                    System.err.println("Error: unknown node type");
            }
            printNode(n.right);
        }
    }
    
    
}
