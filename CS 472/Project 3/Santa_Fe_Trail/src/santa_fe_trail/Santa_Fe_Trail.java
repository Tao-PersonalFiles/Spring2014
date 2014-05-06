/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package santa_fe_trail;

/**
 *
 * @author 张涛
 */
public class Santa_Fe_Trail {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Santa_Fe_Trail t = new Santa_Fe_Trail();
        //t.testboard();
        //t.testant();
        //t.testNodeTracker();
        //t.testCalc_size();
        //t.testMutation();
        //t.testFitness();
        //t.testmath();
        //t.testPop();
        //t.testSteadyState();
        //t.testCrossover();
        t.SFT();
    }
    
    public void testboard(){
        Board b = new Board();
        b.printBoard();
        System.out.println(b.total_food);
    }
    
    public void testant(){
        Ant a = new Ant();
        //a.generate(3);
        a.full(0,2,a.root);
        a.printAnt();
        
        Node n = new Node();
        a.copyNode(n, a.root);
        
        a.printNode(n);
    }
    
    public void testNodeTracker(){
        Ant a = new Ant();
        //a.generate(3);
        a.full(0,2,a.root);
        a.printAnt();
        
        a.TrackRoot(a.root, 1);
        System.out.println(a.node_tracker.type);
        
        a.resetCount();
        a.TrackRoot(a.root, 2);
        System.out.println(a.node_tracker.type);
        a.resetCount();
        a.TrackRoot(a.root, 3);
        Node n = new Node();
        n = a.node_tracker;
        System.out.println(a.node_tracker.type);
        
        a.resetCount();
        a.TrackRoot(a.root, 4);
        System.out.println(a.node_tracker.type);
        
        a.resetCount();
        a.TrackRoot(a.root, 5);
        System.out.println(a.node_tracker.type);
    }
    
    public void testCalc_size(){
        Ant a = new Ant();
        a.generate(3);
        a.printAnt();
        
        a.calc_size();
        System.out.println("terms:" + a.terms + "   non_terms: "+ a.non_terms);
    }
    
    public void testMutation(){
        Ant a = new Ant();
        a.generate(4);
        a.printAnt();
        a.calc_size();
        
        a.Mutation();
        a.printAnt();
    }
    
    public void testFitness(){
        Ant a = new Ant();
        a.generate(10);
        //a.printAnt();
        
        a.calc_size();
        System.out.println("terms:" + a.terms + "   non_terms: "+ a.non_terms);
        
        a.board.printBoard();
        System.out.printf("%nAfter move:%n");
        double f = a.fitness();
        
        a.board.printBoard();
        System.out.printf("%f%n", f);
        System.out.println(a.board.food_collected);
        System.out.println(a.board.total_step);
        //System.out.println(a.board.);
    }
    
    public void testPop(){
        Generation g = new Generation();
        g.generate();
        
        int best = g.best();
        g.pop[best].board.printBoard();
        System.out.println(g.fitness[best]);
        
        System.out.println(g.avgFit());
        
        System.out.println(g.avgTsize());
    
        for(int i = 0; i < g.pop_size; i++){
            System.out.println(g.t_size[i]);
        }
    }
    
    public void testSteadyState(){
        Generation g = new Generation();
        g.generate();
        
        int best = g.best();
        System.out.println("terms:" + g.pop[best].terms 
                + "   non_terms: "+  g.pop[best].non_terms);
        System.out.println(g.fitness[best]);
        System.out.println(g.avgFit());
        g.pop[best].board.printBoard();
        
        
        g.steady_state();
    }
    
    public void testCrossover(){
        Ant a = new Ant();
        Ant b = new Ant();
        a.generate(5);
        b.generate(5);
        a.calc_size();
        b.calc_size();
        a.printAnt();
        b.printAnt();
        System.out.println("a max: "+ a.max_depth);
        System.out.println("b max: " + b.max_depth);
        System.out.println();
        Generation g = new Generation();
        g.crossover(a, b);
        a.calc_size();
        b.calc_size();
        a.resetHeight();
        b.resetHeight();
        a.printAnt();
        b.printAnt();
        System.out.println("a max: "+ a.max_depth);
        System.out.println("b max: " + b.max_depth);
    }
    
    public void SFT(){
        Generation [] g;
        g = new Generation[10];
        
        for(int i = 0; i < 10; i++){
            g[i] = new Generation();
            g[i].generate();
            g[i].steady_state();
        }
    }
}
