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
        /*
        Individual t = new Individual();
        t.generate(3);
        t.printTree();
        int X = 1;
        double z = t.fitness(X);
        System.out.println("Fitness: y( "+ X + " ) = "+z);
        
        t.calc_size();
        System.out.printf("Terms: %d, Non-Terms: %d%n",t.terms, t.non_terms);
        
        Individual c = new Individual();
        c.copy(t.root);
        
        t.erase();
        t.printTree();
        
        c.printTree();
        X = 2;
        z = c.fitness(X);
        System.out.println("Fitness: y( "+ X + " ) = "+z);
        
        c.calc_size();
        System.out.printf("Terms: %d, Non-Terms: %d%n",t.terms, t.non_terms);
        */
        
        Generation g = new Generation();
        g.Generation(5, 1);
        System.out.printf("BestFit: %.4f%n", g.bestFit());
        System.out.printf("AvgFit: %.4f%n", g.avgFit());
        System.out.printf("Avg Term Size: %.2f%n", g.avgTsize());
        System.out.printf("Avg Non-term size: %.2f%n", g.avgNsize());
    }
    
}
