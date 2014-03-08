/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package genetic_programming;

import java.util.Random;

/**
 *
 * @author 张涛
 */
public class Generation {
    
    public int pop_size;
    public Individual [] pop;
    public double [] fitness; // fitness of each individual
    public int [] t_size;  //sizes for terminals of each individual
    public int [] n_size;  //sizes for non_terms of each individual
    
    public double X;
    
    public void Generation(int p, double x_in){
        pop_size = p;
        X = x_in;
        pop = new Individual[pop_size];
        fitness = new double[pop_size];
        t_size = new int[pop_size];
        n_size = new int[pop_size];
        generatePop();
    }
    
    public void generatePop(){
        Random r = new Random();
        int max;
        int i;
        for(i = 0; i < pop_size; i++){
            max = r.nextInt(5); // random max depth 0~5
            pop[i] = new Individual();
            pop[i].Individual(max);
            fitness[i] = pop[i].fitness(X);
            pop[i].calc_size();
            t_size[i] = pop[i].terms;
            n_size[i] = pop[i].non_terms;
        }
        
    }
    
    public int getPopSize(){
        return pop_size;
    }
    
    public double bestFit(){
        double best = fitness[0];
        int i;
        for(i = 0; i < pop_size; i++){
            if(fitness[i] > best){  // largest is best right now
                best = fitness[i];
            }
        }
        return best;
    }
    
    public double avgFit(){
        double sum = 0;
        int i;
        for(i = 0; i < pop_size; i++){
            sum += fitness[i];
        }
        return sum/pop_size;
    }
    
    public double avgTsize(){
        double sum = 0;
        int i;
        for(i = 0; i < pop_size; i++){
            sum += t_size[i];
        }
        return sum/pop_size;
    }
    
    public double avgNsize(){
        double sum = 0;
        int i;
        for(i = 0; i < pop_size; i++){
            sum += n_size[i];
        }
        return sum/pop_size;
    }
    
}
