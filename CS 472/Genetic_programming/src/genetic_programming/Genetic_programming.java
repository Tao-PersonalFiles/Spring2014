/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package genetic_programming;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author 张涛
 */
public class Genetic_programming {
    
    double [] X;
    double [] Y;
    
    int i = 0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Genetic_programming gp = new Genetic_programming();
        gp.getdata();
        //gp.testMutation();
        //gp.testCrossover();
        //gp.testTournament();
        gp.testSteadyState();
        
        //gp.testfitness();
        //gp.printdata();
    }
    
    public void getdata(){
        X = new double[81];
        Y = new double[81];
        i = 0;
        boolean found = false;
        File f = new File("project3data.txt");
        try (Scanner scanner = new Scanner(f);){
            while (scanner.hasNextLine() && found == false) {
                String line = scanner.nextLine();
                LineSplit(line);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public void LineSplit(String line){
        StringTokenizer strTok = new StringTokenizer(line);
        
        String tmp;
        
        if(strTok.hasMoreElements()){
            tmp = (String) strTok.nextElement();
            X[i] = Double.parseDouble(tmp);
        }
        if(strTok.hasMoreElements()){
            tmp = (String) strTok.nextElement();
            //System.out.println(tmp);
            Y[i] = Double.parseDouble(tmp);
        }
        
        if(strTok.hasMoreElements()){
            System.err.println("data file err");
        }else{
            i++;
        }
    }
    
    public void printdata(){
        int j;
        for(j = 0; j < X.length; j++){
            System.out.printf("%10f     %10f%n", X[j], Y[j]);
        }
            
    }
    
    public void testfitness(){
        Generation g = new Generation();
        g.getdata(X,Y);
        g.generate(100);
        System.out.printf("BestFit: %d%n", g.best());
        System.out.printf("AvgFit: %.4f%n", g.avgFit());
        System.out.printf("Avg Term Size: %.2f%n", g.avgTsize());
        System.out.printf("Avg Non-term size: %.2f%n", g.avgNsize());
    }
    
    public void testGeneration(){
        Generation g = new Generation();
        g.generate(5);
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
        g.getdata(X,Y);
        g.generate(100);
        g.pop[1].printTree();
        g.pop[4].printTree();
        g.crossover(g.pop[1], g.pop[4]);
        System.out.println("After crossover");
        g.pop[1].printTree();
        g.pop[4].printTree();
    }
    
    public void testTournament(){
        Generation g = new Generation();
        g.getdata(X,Y);
        g.generate(100);// size of 100
        System.out.println(g.tournament(true));
    }
    
    public void testSteadyState(){
        Generation g = new Generation();
        g.getdata(X,Y);
        g.generate(10);
        System.out.printf("BestFit: %d%n", g.best());
        System.out.printf("BestFit: %f%n", g.fitness[g.best()]);
        System.out.printf("AvgFit: %.4f%n", g.avgFit());
        System.out.printf("Avg Term Size: %.2f%n", g.avgTsize());
        System.out.printf("Avg Non-term size: %.2f%n", g.avgNsize());
        int i;
        for(i = 0; i < g.pop_size; i ++){
           // g.pop[i].printTree();
        }
        System.out.println();
        g.steady_state();
        for(i = 0; i < g.pop_size; i ++){
           // g.pop[i].printTree();
        }
        
        System.out.printf("BestIndex: %d%n", g.best());
        System.out.printf("BestFit: %f%n", g.fitness[g.best()]);
        System.out.printf("AvgFit: %.4f%n", g.avgFit());
        System.out.printf("Avg Term Size: %.2f%n", g.avgTsize());
        System.out.printf("Avg Non-term size: %.2f%n", g.avgNsize());
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
