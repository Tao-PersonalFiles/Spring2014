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
public class Rubic_Cube_GP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Rubic_Cube_GP r = new Rubic_Cube_GP();
        r.testIndividual();
        //r.testCube();
    }
    
    public void testIndividual(){
        Individual i = new Individual();
        i.generate(8);
        i.printSolution();
        System.out.println(i.fitness());
        System.out.println(i.rubic.total_rotation);
    }
    
    public void testCube(){
        Rubic_Cube rubic = new Rubic_Cube();
        rubic.printCube();
        System.out.println();
        rubic.Rotate_D();
        rubic.printCube();
        
    }
}
