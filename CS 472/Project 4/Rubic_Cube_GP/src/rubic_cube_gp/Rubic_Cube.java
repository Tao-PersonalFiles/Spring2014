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
public final class Rubic_Cube {   // 2*2*2 Cube
    int Rubic[][][] = new int[6][2][2]; // 
    
    // set color index
    int white  = 0;
    int red    = 1;
    int blue   = 2;
    int green  = 3;
    int yellow = 4;
    int orange = 5;
    int tmp;    // use for rotation
    
    public Rubic_Cube(){
        setCube();
    }
    
    public void setCube(){
        // set front face
        Rubic[0][0][0] = white;
        Rubic[0][0][1] = orange;
        Rubic[0][1][0] = green;
        Rubic[0][1][1] = yellow;
        
        // set right face
        Rubic[1][0][0] = green;
        Rubic[1][0][1] = green;
        Rubic[1][1][0] = blue;
        Rubic[1][1][1] = blue;
        
        // set up face
        Rubic[2][0][0] = red;
        Rubic[2][0][1] = yellow;
        Rubic[2][1][0] = red;
        Rubic[2][1][1] = white;
        
        // set back face
        Rubic[3][0][0] = orange;
        Rubic[3][0][1] = yellow;
        Rubic[3][1][0] = white;
        Rubic[3][1][1] = green;
        
        // set left face
        Rubic[4][0][0] = blue;
        Rubic[4][0][1] = blue;
        Rubic[4][1][0] = white;
        Rubic[4][1][1] = yellow;
        
        // set down face
        Rubic[5][0][0] = red;
        Rubic[5][0][1] = orange;
        Rubic[5][1][0] = red;
        Rubic[5][1][1] = orange;
    }
    
    public void Rotate_R(){
        tmp = Rubic[0][0][1];
        Rubic[0][0][1] = Rubic[5][0][1];
        Rubic[5][0][1] = Rubic[3][1][0];
        Rubic[3][1][0] = Rubic[2][0][1];
        Rubic[2][0][1] = tmp;
        
        tmp = Rubic[2][1][1];
        Rubic[2][1][1] = Rubic[0][1][1];
        Rubic[0][1][1] = Rubic[5][1][1];
        Rubic[5][1][1] = Rubic[3][0][0];
        Rubic[3][0][0] = tmp;
        
        tmp = Rubic[1][0][0];
        Rubic[1][0][0] = Rubic[1][1][0];
        Rubic[1][1][0] = Rubic[1][1][1];
        Rubic[1][1][1] = Rubic[1][0][1];
        Rubic[1][0][1] = tmp;
    }
    
    public void printCube(){
        System.out.printf("%5s %s %n", Color(Rubic[2][0][0]), Color(Rubic[2][0][1]));
        System.out.printf("%5s %s %n", Color(Rubic[2][1][0]), Color(Rubic[2][1][1]));
        
        System.out.printf("%s %s ", Color(Rubic[4][0][0]), Color(Rubic[4][0][1]));
        System.out.printf("%s %s ", Color(Rubic[0][0][0]), Color(Rubic[0][0][1]));
        System.out.printf("%s %s ", Color(Rubic[1][0][0]), Color(Rubic[1][0][1]));
        System.out.printf("%s %s %n", Color(Rubic[3][0][0]), Color(Rubic[3][0][1]));
        
        System.out.printf("%s %s ", Color(Rubic[4][1][0]), Color(Rubic[4][1][1]));
        System.out.printf("%s %s ", Color(Rubic[0][1][0]), Color(Rubic[0][1][1]));
        System.out.printf("%s %s ", Color(Rubic[1][1][0]), Color(Rubic[1][1][1]));
        System.out.printf("%s %s %n", Color(Rubic[3][1][0]), Color(Rubic[3][1][1]));
        
        System.out.printf("%5s %s %n", Color(Rubic[5][0][0]), Color(Rubic[5][0][1]));
        System.out.printf("%5s %s %n", Color(Rubic[5][1][0]), Color(Rubic[5][1][1]));
    }
    
    public String Color(int p){
        String c = null;
        switch(p){
            case 0:
                c = "W";
                break;
            case 1:
                c = "R";
                break;
            case 2:
                c = "B";
                break;
            case 3:
                c = "G";
                break;
            case 4:
                c = "Y";
                break;  
            case 5:
                c = "O";
                break;
        }
        return c;
    }
}
