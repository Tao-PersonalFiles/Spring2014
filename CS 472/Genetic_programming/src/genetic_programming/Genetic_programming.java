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
public class Genetic_programming {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Tree t = new Tree();
        t.generate(0, 5);
        t.printTree();
        
    }
    
}
