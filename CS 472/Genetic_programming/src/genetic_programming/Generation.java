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
    
    // method to make random values
    Random randomGenerator = new Random();
    
    public void Generation(int p){
        pop_size = p;
        pop = new Individual[pop_size];
        fitness = new double[pop_size];
        t_size = new int[pop_size];
        n_size = new int[pop_size];
        generatePop();
    }
    
    public void getInput(double x_in){
        X = x_in;
    }
    
    public void generatePop(){
        Random r = new Random();
        int max;
        int i;
        for(i = 0; i < pop_size; i++){
            max = r.nextInt(5); // random max depth 0~5
            pop[i] = new Individual();
            pop[i].generate(max);
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
    
    public void tournament(){
        
    }
    
    public void crossover(int father, int mother){
        Node gene_of_father;
        Node gene_of_mother;
        
        // get random gene position for parents
        int r_father, r_mother;
        
        boolean rule = false;
        /*
        while(rule != true){
            r_father = randomGenerator.nextInt(t_size[father] + n_size[father]);
            // point to random gene
            pop[father].resetCount();
            gene_of_father = pop[father].TrackRoot(pop[father].root, r_father);
            if(gene_of_father.type < 4 && randomGenerator.nextInt(100) < 90){
                rule = true;
            }else if(gene_of_father.type >= 4 && randomGenerator.nextInt(100) < 10){
                rule = true;
            }
        }
        rule = false;
        
        while(rule != true){
            r_mother = randomGenerator.nextInt(t_size[mother] + n_size[mother]);
            // point to random gene
            pop[mother].resetCount();
            gene_of_mother = pop[mother].TrackRoot(pop[mother].root, r_mother);
            if(gene_of_mother.type < 4 && randomGenerator.nextInt(100) < 90){
                rule = true;
            }else if(gene_of_mother.type >= 4 && randomGenerator.nextInt(100) < 10){
                rule = true;
            }
        }
        */
        r_father = randomGenerator.nextInt(t_size[father] + n_size[father]);
        // point to random gene
        pop[father].resetCount();
        gene_of_father = pop[father].TrackRoot(pop[father].root, r_father);

        r_mother = randomGenerator.nextInt(t_size[mother] + n_size[mother]);
        // point to random gene
        pop[mother].resetCount();
        gene_of_mother = pop[mother].TrackRoot(pop[mother].root, r_mother);
        
        // put father's gene into another tmp individual
        Individual tmp_gene = new Individual();
        tmp_gene.copyNode(tmp_gene.root, gene_of_father);
        tmp_gene.printTree();
        // delete father's gene
        if(gene_of_father.left != null){
            pop[father].deleteNode(gene_of_father.left);
        }
        if(gene_of_father.right != null){
        pop[father].deleteNode(gene_of_father.right);
        }
        // copy mother's gene onto father
        pop[father].copyNode(gene_of_father, gene_of_mother);
        
        // delete mother's gene
        if(gene_of_mother.left != null){
            pop[mother].deleteNode(gene_of_mother.left);
        }
        if(gene_of_mother.right != null){
            pop[mother].deleteNode(gene_of_mother.right);
        }
        // copy tmp individual (father's gene) onto mother
        pop[mother].copyNode(gene_of_mother, tmp_gene.root);
    }
    
}
