/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package genetic_programming;

/**
 *
 * @author 张涛
 */
public class Individual {
    public double fitness;
    public int size;
    public int terms;
    public int non_terms;
    
    private Node top;
    
    // init the individual
    public void Individual(){
        top = null;
    }
    
    public boolean isEmpty(){
        return top == null;
    }
    
    public void generate(int depth, int max, Node p){
        
    }
    
    public void delete(){
        
    }
    
    public void printNodeList(){
        
    }
    
    public double fitness(){
        return 0;
    }
    
    public void calc_size(){
        
    }
}
