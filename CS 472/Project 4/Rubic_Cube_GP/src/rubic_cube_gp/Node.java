/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rubic_cube_gp;

/**
 *
 * @author 张涛
 */
public class Node {
    Node [] branch;
    Node parent;
    
    int branch_length = 3;
    
    int type;
    
    int height;
    
    public Node(){
        branch = new Node[4];
        for(int i = 0; i < branch_length; i++){
            branch[i] = null;
        }
        parent = null;
    }
}
