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
        
        Genetic_programming gp = new Genetic_programming();
        //gp.testMutation();
        gp.testCrossover();
        //gp.testSteadyState();
        
    }
    
    public void testGeneration(){
        Generation g = new Generation();
        g.Generation(5);
        System.out.printf("BestFit: %.4f%n", g.best());
        System.out.printf("AvgFit: %.4f%n", g.avgFit());
        System.out.printf("Avg Term Size: %.2f%n", g.avgTsize());
        System.out.printf("Avg Non-term size: %.2f%n", g.avgNsize());
    }
    
    public void testMutation(){
        Individual i = new Individual();
        i.generate(3);
        i.calc_size();
        i.printTree();
        
        //i.deleteNode(i.root.left);
        
        i.Mutation();
        i.printTree();
    }
    
    public void testCrossover(){
        Generation g = new Generation();
        g.Generation(5);
        g.getInput(1);
        g.pop[1].printTree();
        g.pop[4].printTree();
        g.crossover(g.pop[1], g.pop[4]);
        System.out.println("After crossover");
        g.pop[1].printTree();
        g.pop[4].printTree();
    }
    
    public void testSteadyState(){
        Generation g = new Generation();
        g.Generation(10);
        g.getInput(1);
        int i;
        for(i = 0; i < g.pop_size; i ++){
           // g.pop[i].printTree();
        }
        System.out.println();
        g.steady_state();
        for(i = 0; i < g.pop_size; i ++){
           // g.pop[i].printTree();
        }
    }
    
    public void testIndividual(){
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
    }
    
}
