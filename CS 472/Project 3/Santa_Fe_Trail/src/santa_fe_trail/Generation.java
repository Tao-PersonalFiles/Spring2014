/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package santa_fe_trail;

import java.util.Random;

/**
 *
 * @author 张涛
 */
public class Generation {
    public int pop_size = 100;
    public Ant [] pop;
    public double [] fitness; // fitness of each individual
    public int [] t_size;  //sizes for terminals of each individual
    public int [] n_size;  //sizes for non_terms of each individual
    
    public int ant_max_depth = 8;
    public int max_terms = 600;
    
    Random random = new Random();
    
    public Generation(){
        pop = new Ant[pop_size];
        fitness = new double[pop_size];
        t_size = new int[pop_size];
        n_size = new int[pop_size];
    }
    
    public Generation(int p){
        pop_size = p;
        pop = new Ant[pop_size];
        fitness = new double[pop_size];
        t_size = new int[pop_size];
        n_size = new int[pop_size];
    }
    
    public void generate(){
        int i;
        boolean rule = false;
        for(i = 0; i < pop_size; i++){
            while(rule != true){
                pop[i] = new Ant();
                pop[i].generate(ant_max_depth);
                fitness[i] = pop[i].fitness();
                pop[i].calc_size();
                t_size[i] = pop[i].terms;
                n_size[i] = pop[i].non_terms;
                if(pop[i].terms <= max_terms){
                    rule = true;
                }
            }
            rule = false;
        }
    }
    
    public int best(){
        int best = 0;
        double bestFitness = fitness[0];
        int i;
        for(i = 0; i < pop_size; i++){
            if(fitness[i] > bestFitness){  
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
        int father = 0, mother = 1;
        boolean good = true;
        Ant son, son_copy = null;
        Ant dau, dau_copy = null;
        
        int loser1 = 0, loser2 = 1;
        boolean bad = false;
        
        boolean rules;
        int mutation_type = 0;
        
        int count = 0;
        while( count < 5000000){
            father = tournament(good);
            while((mother = tournament(good)) == father){
                // do nothing
                // just make sure father != mother
            }
            
            // make sure after crossover, the max_depth <= 8, terms <= 600
            rules = false;
            while(rules != true){
                son_copy = new Ant();
                dau_copy = new Ant();
                son_copy.copy(pop[father].root);
                dau_copy.copy(pop[mother].root);
                son_copy.calc_size();
                dau_copy.calc_size();
            
                crossover(son_copy, dau_copy);
                son_copy.calc_size();
                dau_copy.calc_size();
                
                son_copy.resetHeight();
                dau_copy.resetHeight();
                
                if(son_copy.max_depth <= this.ant_max_depth
                        && dau_copy.max_depth <= this.ant_max_depth){
                    rules = true;
                    mutation_type = 0;
                }else if(son_copy.max_depth <= this.ant_max_depth){
                    dau_copy.erase();
                    dau_copy = new Ant();
                    dau_copy.copy(pop[mother].root);
                    rules = true;
                    mutation_type = 1;
                }else if(dau_copy.max_depth <= this.ant_max_depth){
                    son_copy.erase();
                    son_copy = new Ant();
                    son_copy.copy(pop[father].root);
                    rules = true;
                    mutation_type = 2;
                }
            }
            
            // crossover to get children
            if(mutation_type == 0 || mutation_type == 1)
            {
                son = new Ant();
                son.copy(son_copy.root);
                son.calc_size();
                if(random.nextInt(100) < 50){
                    son.Mutation();
                }
                loser1 = tournament(bad);
                pop[loser1].deleteNode(pop[loser1].root);
                pop[loser1] = son;
                pop[loser1].calc_size();
                t_size[loser1] = pop[loser1].terms;
                n_size[loser1] = pop[loser1].non_terms;
                fitness[loser1] = pop[loser1].fitness();
            }
            
            if(mutation_type == 0 || mutation_type == 2){
                dau = new Ant();
                dau.copy(dau_copy.root);
                dau.calc_size();
                if(random.nextInt(100) < 50){
                    dau.Mutation();
                }
                if(mutation_type == 0){
                    while((loser2 = tournament(bad)) == loser1){ }
                }
                pop[loser2].deleteNode(pop[loser2].root);
                pop[loser2] = dau;
                pop[loser2].calc_size();
                t_size[loser2] = pop[loser2].terms;
                n_size[loser2] = pop[loser2].non_terms;
                fitness[loser2] = pop[loser2].fitness();
            }
            /*
            if(count % 100000 == 0){
                System.out.printf("%n %d %n", count);
                System.out.printf("Best: %.4f %n", fitness[best()]);
                System.out.println("Food Collected: " + pop[best()].board.food_collected);
                System.out.println("Terms: " + t_size[best()]);
                System.out.println("Total move: " + pop[best()].board.total_step);
                pop[best()].board.printBoard();
            }
                    */
            //System.out.println(count);
            count++;
        }
        System.out.printf("%n %d %n", count);
                System.out.printf("Best: %.4f %n", fitness[best()]);
                System.out.println("Food Collected: " + pop[best()].board.food_collected);
                System.out.println("Terms: " + t_size[best()]);
                System.out.println("Total move: " + pop[best()].board.total_step);
                pop[best()].board.printBoard();
    }
    
    public int tournament(boolean good){
        int winner = random.nextInt(pop_size);
        double winner_fitness = fitness[winner];
        int tmp;
        int i;
        int N = pop_size/3;
        for(i = 0; i < N; i++){
            tmp = random.nextInt(pop_size);
            if(good == true && fitness[tmp] > winner_fitness){
                winner_fitness = fitness[tmp];
                winner = tmp;
            }else if(good == false && fitness[tmp] < winner_fitness){
                winner_fitness = fitness[tmp];
                winner = tmp;
            }
        }
        return winner;
    }
    
    public void crossover(Ant son, Ant dau){
        Node gene_of_son = new Node();
        Node gene_of_dau = new Node();
        
        // set random gene position for parents
        int p_son, p_dau;
        
        boolean rule = false;
        
        while(rule != true){
            p_son = 1 + random.nextInt(son.terms + son.non_terms);
            
            son.resetCount();                        
            son.TrackRoot(son.root, p_son);            
            gene_of_son = son.node_tracker;
            // apply 90/10 rule?
            if(gene_of_son.type < 3 && random.nextInt(100) < 90){
                rule = true;
            }else if(gene_of_son.type >= 3 && random.nextInt(100) < 10){
                rule = true;
            }
        }
        
        rule = false;
        while(rule != true){
            p_dau = 1 + random.nextInt(dau.terms + dau.non_terms);
            
            dau.resetCount();            
            dau.TrackRoot(dau.root, p_dau);            
            gene_of_dau = dau.node_tracker;
            // apply 90/10 rule?
            if(gene_of_dau.type < 3 && random.nextInt(100) < 90){
                rule = true;
            }else if(gene_of_dau.type >= 3 && random.nextInt(100) < 10){
                rule = true;
            }
        }
        
        // put father's gene into another tmp individual
        Ant tmp_gene = new Ant();
        tmp_gene.copyNode(tmp_gene.root, gene_of_son);
        
        // delete father's gene
        int i;
        for(i = 0; i < gene_of_son.branch_length; i++){
            if(gene_of_son.branch[i] != null){
                son.deleteNode(gene_of_son.branch[i]);
                gene_of_son.branch[i] = null;
            }
        }
        // copy mother's gene onto father
        son.copyNode(gene_of_son, gene_of_dau);
        
        // delete mother's gene
        for(i = 0; i < gene_of_dau.branch_length; i++){
            if(gene_of_dau.branch[i] != null){
                dau.deleteNode(gene_of_dau.branch[i]);
                gene_of_dau.branch[i] = null;
            }
        }
        
        dau.copyNode(gene_of_dau, tmp_gene.root);
    }
}
