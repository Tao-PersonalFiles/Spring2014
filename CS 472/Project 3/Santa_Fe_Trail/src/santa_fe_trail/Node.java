/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package santa_fe_trail;

/**
 *
 * @author Tao Zhang
 */

public class Node {
    Node [] branch;
    Node parent;
    
    int branch_length = 3;
    
    int type;
    
    int height;
    /**
    public enum TypeCode {
        prog2,  // --0
        prog3,  // --1
        iffoodahead,    //--2
        left,   // --3
        forward,// --4
        right   // --5
    }
    */
    
    
    public Node(){
        branch = new Node[4];
        for(int i = 0; i < branch_length; i++){
            branch[i] = null;
        }
        parent = null;
    }
    
    
}


