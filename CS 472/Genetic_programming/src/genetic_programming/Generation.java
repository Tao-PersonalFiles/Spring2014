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
    
    public double [] X;
    public double [] Y;
    
    // method to make random values
    Random randomGenerator = new Random();
    
    public void getdata(double [] x, double [] y){
        X = new double[x.length];
        X = x;
        Y = new double[y.length];
        Y = y;
    }
    
    public void generate(int p){
        pop_size = p;
        pop = new Individual[pop_size];
        fitness = new double[pop_size];
        t_size = new int[pop_size];
        n_size = new int[pop_size];
        generatePop();
    }
    
    public void generatePop(){
        int max = 10;   // max depth for each individual
        int i;
        for(i = 0; i < pop_size; i++){
            pop[i] = new Individual();
            pop[i].generate(max);
            fitness[i] = pop[i].fitness(X,Y);
            pop[i].calc_size();
            t_size[i] = pop[i].terms;
            n_size[i] = pop[i].non_terms;
        }
        
    }
    
    public int getPopSize(){
        return pop_size;
    }
    
    public int best(){
        int best = 0;
        double bestFitness = fitness[0];
        int i;
        for(i = 0; i < pop_size; i++){
            if(fitness[i] < bestFitness){  // largest is best right now
                best = i;
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
    
    public void steady_state(){
        int father, mother;
        boolean good = true;
        Individual son;
        Individual dau;
        
        int loser1, loser2;
        boolean bad = false;
        
        int count = 0;
        while(count < pop_size*100){
            father = tournament(good);
            while((mother = tournament(good)) == father){
                // do nothing
                // just make sure father != mother
            }
            
            // crossover to get children
            son = new Individual();
            dau = new Individual();
            son.copyNode(son.root, pop[father].root);
            dau.copyNode(dau.root, pop[mother].root);
            son.calc_size();
            dau.calc_size();
            crossover(son, dau);
                    
            // mutate
            son.Mutation();
            dau.Mutation();
            // find two losers
            loser1 = tournament(bad);
            while((loser2 = tournament(bad)) == loser1){
                // do nothing
                // just make sure loser1 != loser2
            }
            // replace two losers by chilren
            pop[loser1].deleteNode(pop[loser1].root);
            pop[loser2].deleteNode(pop[loser2].root);
            pop[loser1] = son;
            pop[loser2] = dau;
            
            // refresh the data
            pop[loser1].calc_size();
            pop[loser2].calc_size();
            t_size[loser1] = pop[loser1].terms;
            t_size[loser2] = pop[loser2].terms;
            n_size[loser1] = pop[loser1].non_terms;
            n_size[loser1] = pop[loser1].non_terms;
            fitness[loser1] = pop[loser1].fitness(X, Y);
            fitness[loser2] = pop[loser2].fitness(X, Y);
            count++;
        }
    }
    
    public int tournament(boolean good){
        int winner = randomGenerator.nextInt(pop_size);
        double winner_fitness = fitness[winner];
        int tmp;
        
        int i;
        int N = pop_size/3;
        for(i = 0; i < N; i++){
            tmp = randomGenerator.nextInt(pop_size);
            if(good == true && fitness[tmp] < winner_fitness){
                winner_fitness = fitness[tmp];
                winner = tmp;
            }else if(good == false && fitness[tmp] > winner_fitness){
                winner_fitness = fitness[tmp];
                winner = tmp;
            }
        }
        
        return winner;
    }
    
    public void crossover(Individual son, Individual dau){
        Node gene_of_son = new Node();
        Node gene_of_dau = new Node();
        
        // set random gene position for parents
        int r_son, r_dau;
        
        boolean rule = false;
        
        while(rule != true){
            // generate random position of father's gene
            r_son = randomGenerator.nextInt(son.terms + son.non_terms);
            // point to random gene
            son.resetCount();
            gene_of_son = son.TrackRoot(son.root, r_son);
            // apply 90/10 rule?
            if(gene_of_son.type < 4 && randomGenerator.nextInt(100) < 90){
                rule = true;
            }else if(gene_of_son.type >= 4 && randomGenerator.nextInt(100) < 10){
                rule = true;
            }
        }
        
        rule = false;
        while(rule != true){
            // generate random position of mother's gene
            r_dau = randomGenerator.nextInt(dau.terms + dau.non_terms);
            // point to random gene
            dau.resetCount();
            gene_of_dau = dau.TrackRoot(dau.root, r_dau);
            // apply 90/10 rule?
            if(gene_of_dau.type < 4 && randomGenerator.nextInt(100) < 90){
                rule = true;
            }else if(gene_of_dau.type >= 4 && randomGenerator.nextInt(100) < 10){
                rule = true;
            }
        }
        
        // put father's gene into another tmp individual
        Individual tmp_gene = new Individual();
        tmp_gene.copyNode(tmp_gene.root, gene_of_son);
        
        // delete father's gene
        if(gene_of_son.left != null){
            son.deleteNode(gene_of_son.left);
            gene_of_son.left = null;
        }
        if(gene_of_son.right != null){
            son.deleteNode(gene_of_son.right);
            gene_of_son.right = null;
        }
        // copy mother's gene onto father
        son.copyNode(gene_of_son, gene_of_dau);
        
        // delete mother's gene
        if(gene_of_dau.left != null){
            dau.deleteNode(gene_of_dau.left);
            gene_of_dau.left = null;
        }
        if(gene_of_dau.right != null){
            dau.deleteNode(gene_of_dau.right);
            gene_of_dau.right = null;
        }
        // copy tmp individual (father's gene) onto mother
        dau.copyNode(gene_of_dau, tmp_gene.root);
        
    }
    
}
