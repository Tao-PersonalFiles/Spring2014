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
public class Node {
        Node left;
        Node right;
        Node parent;
        
        int type;   // 0 = +; 1 = -; 2 = *; 3 = /; 4 = X; 5 = const
        double const_value;
        
        public void Node(/*int t, double c*/){
            left = null;
            right = null;
            //type = t;
            //const_value = c;
        }
}
