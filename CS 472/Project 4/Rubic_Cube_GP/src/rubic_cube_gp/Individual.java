/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rubic_cube_gp;

import java.util.Random;

/**
 *
 * @author 张涛
 */
public class Individual {
    public Node root;
    public int max_depth;
    
    public int terms;
    public int non_terms;
    
    public int count;
    Node node_tracker;
    
    Random random = new Random();
    
    Rubic_Cube rubic = new Rubic_Cube();
    
    public Individual(){
        this.root = new Node();
        terms = 0;
        non_terms = 0;
    }
    
    public boolean isEmpty(){
        return root == null;
    }
    
    /** ==================================
     *      Functions to generate the ant
     *  ==================================
     * @param max
     */
    
    public void generate(int max){
        max_depth = max;
        root.type = random.nextInt(2);
        root.height = 0;
        if(root.type == 0){
            root.branch[0] = new Node();
            full(1, max, root.branch[0]);
            root.branch[1] = new Node();
            grow(1, max, root.branch[1]);
        }else if(root.type == 1){
            root.branch[0] = new Node();
            grow(1, max, root.branch[0]); 
            root.branch[1] = new Node();
            full(1, max, root.branch[1]);
            root.branch[2] = new Node();
            grow(1, max, root.branch[2]);
        }
    }
    
    public void full(int depth, int max, Node parent){
        parent.height = depth;
        if(depth >= max){   
            parent.type = 2 + random.nextInt(6);
        }else{
            parent.type = random.nextInt(2);    // prog 2 or prog 3
            for(int i = 0; i < parent.type + 2; i++){
                parent.branch[i] = new Node();
                parent.branch[i].parent = parent;
                full(depth+1, max, parent.branch[i]);
            }    
        }
    }
    
    public void grow(int depth, int max, Node parent){
        parent.height = depth;
        if(depth >= max){   
            parent.type = 2 + random.nextInt(6);
        }else{
            if(random.nextInt(10) < 5){  // 50 % to be structure
                parent.type = random.nextInt(2);    // prog 2 or prog 3
                for(int i = 0; i < parent.type + 2; i++){
                    parent.branch[i] = new Node();
                    parent.branch[i].parent = parent;
                    full(depth+1, max, parent.branch[i]);
                }
            }else{
                parent.type = 2 + random.nextInt(6);
            }
        }
    }
    
    public void resetHeight(){
        max_depth = 0;
        resetH(0, this.root);
    }
    
    public void resetH(int depth, Node p){
        p.height = depth;
        if(depth > max_depth){
            max_depth = depth;
        }
        
        if(p.type < 2){
            for(int i=0; i < p.branch_length; i++){
                if(p.branch[i] != null)
                    resetH(depth+1, p.branch[i]);
            }
        }
    }
    
    /** ==================================
     *      Functions to delete the node
     *  ==================================
     */
    public void erase(){
        deleteNode(root);
    }
    
    public void deleteNode(Node n){
        int i;
        if(n.type < 2){
            for( i = 0; i < n.branch_length; i++){
                if(n.branch[i] != null)
                    deleteNode(n.branch[i]);
            }
        }
        
        for( i = 0; i < n.branch_length; i++ ){
            if(n.branch[i] != null)
                n.branch[i] = null;
        }
        n.parent = null;
        n = null;
    }
    
    /** ==================================
     *      Functions to copy the node
     *  ==================================
     * @param source
     */
    public void copy(Node source){
        copyNode(this.root, source);
    }
    
    public void copyNode(Node self, Node source){
        self.type = source.type;
        self.height = source.height;
        int i;
        if(source.type < 2){
            for( i = 0; i < source.branch_length; i++ ){
                if(source.branch[i] != null){
                    self.branch[i] = new Node();
                    self.branch[i].parent = self;
                    copyNode(self.branch[i], source.branch[i]);
                }
            }
        }
    }
    
    /** ==================================
     *      Functions to calculate the fitness
     *  ==================================
     * @return 
     */
    
    public double fitness(){
        return 0;
    }
    
    /** ==================================
     *      Functions to calculate the size of terms and non-terms
     *  ==================================
     */
    public void calc_size(){
        terms = 0;
        non_terms = 0;   
        countSize(root);
    }
    
    public void countSize(Node n){
        if(n.type >= 2){
            terms++;
        }else {
            non_terms++;
            for(int i = 0; i < n.branch_length; i++){
                if(n.branch[i] != null){
                    countSize(n.branch[i]);
                }
            }
        }
    }
    
    /** ==================================
     *      Functions to mutate
     *  ==================================
     */
    public void Mutation(){
        
    }
    
    public void printSolution(){
        if(isEmpty()){
            System.out.println("The Tree is Empty");
        }else{
            printNode(root);
            System.out.println();
        }
    }
    
    public void printNode(Node n){
        switch(n.type){
            case 0:
            case 1:
                for(int i = 0; i < n.branch_length; i++){
                    if(n.branch[i] != null){
                        printNode(n.branch[i]);
                    }
                } 
                break;
            case 2:     // Front
                System.out.printf("F ");
                break;
            case 3:     // Right
                System.out.printf("R ");
                break;
            case 4:     // Up
                System.out.printf("U ");
                break;
            case 5:     // Back
                System.out.printf("B ");
                break;
            case 6:     // Left
                System.out.printf("L ");
                break; 
            case 7:     // Down
                System.out.printf("D ");
                break;
            // Counter Clockwise
        }
    }
        
}
