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
public class Ant {
    public Node root;
    
    public int max_depth;
    
    public int terms;
    public int non_terms;
    
    public int count;
    Node node_tracker;
    
    Random random = new Random();
    
    Board board = new Board();
    
    
    public Ant(){
        this.root = new Node();
        terms = 0;
        non_terms = 0;
    }
    
    public boolean isEmpty(){
        return root == null;
    }
    
    /** ==================================
     *      Functions to generate the ant
     *  ==================================
     * @param max
     */
    public void generate(int max){
        max_depth = max;
        root.type = random.nextInt(2);
        root.height = 0;
        if(root.type == 0){
            root.branch[0] = new Node();
            full(1, max, root.branch[0]);
            root.branch[1] = new Node();
            grow(1, max, root.branch[1]);
        }else if(root.type == 1){
            root.branch[0] = new Node();
            grow(1, max, root.branch[0]); 
            root.branch[1] = new Node();
            full(1, max, root.branch[1]);
            root.branch[2] = new Node();
            grow(1, max, root.branch[2]);
        }
        
    }
    
    public void full(int depth, int max, Node parent){
        parent.height = depth;
        if(depth >= max){   
            parent.type = 3 + random.nextInt(3);
        }else{
            parent.type = random.nextInt(3);
            
            if(parent.type == 2){
                parent.branch[0] = new Node();
                parent.branch[0].type = 4;  // move forward
                parent.branch[0].parent = parent;
                parent.branch[0].height = depth + 1;
                
                parent.branch[1] = new Node();
                parent.branch[1].parent = parent;
                full(depth+1, max, parent.branch[1]);
            }else{
                for(int i = 0; i < parent.type + 2; i++){
                    parent.branch[i] = new Node();
                    parent.branch[i].parent = parent;
                    full(depth+1, max, parent.branch[i]);
                }
            }
        }
        
        //System.out.println(parent.type);
    }
    
    public void grow(int depth, int max, Node parent){
        parent.height = depth;
        if(depth >= max){   
            parent.type = 3 + random.nextInt(3);
        }else{
            parent.type = random.nextInt(6);
            
            if(parent.type == 2){
                parent.branch[0] = new Node();
                parent.branch[0].type = 4;  // move forward
                parent.branch[0].parent = parent;
                parent.branch[0].height = depth+1;
                
                parent.branch[1] = new Node();
                parent.branch[1].parent = parent;
                grow(depth+1, max, parent.branch[1]);
            }else if(parent.type < 2){
                for(int i = 0; i < parent.type + 2; i++){
                    parent.branch[i] = new Node();
                    parent.branch[i].parent = parent;
                    grow(depth+1, max, parent.branch[i]);
                }
            }
        }
    }
    public void resetHeight(){
        max_depth = 0;
        resetH(0, this.root);
    }
    
    public void resetH(int depth, Node p){
        p.height = depth;
        if(depth > max_depth){
            max_depth = depth;
        }
        
        if(p.type < 3){
            for(int i=0; i < p.branch_length; i++){
                if(p.branch[i] != null)
                    resetH(depth+1, p.branch[i]);
            }
        }
    }
    
    /** ==================================
     *      Functions to delete the node
     *  ==================================
     */
    public void erase(){
        deleteNode(root);
    }
    
    public void deleteNode(Node n){
        int i;
        if(n.type < 3){
            for( i = 0; i < n.branch_length; i++){
                if(n.branch[i] != null)
                    deleteNode(n.branch[i]);
            }
        }
        
        for( i = 0; i < n.branch_length; i++ ){
            if(n.branch[i] != null)
                n.branch[i] = null;
        }
        n.parent = null;
        n = null;
    }
    
    /** ==================================
     *      Functions to copy the node
     *  ==================================
     * @param source
     */
    public void copy(Node source){
        copyNode(this.root, source);
    }
    
    public void copyNode(Node self, Node source){
        self.type = source.type;
        self.height = source.height;
        int i;
        if(source.type < 3){
            for( i = 0; i < source.branch_length; i++ ){
                if(source.branch[i] != null){
                    self.branch[i] = new Node();
                    self.branch[i].parent = self;
                    copyNode(self.branch[i], source.branch[i]);
                }
            }
        }
    }
    
    /** ==================================
     *      Functions to calculate the fitness
     *  ==================================
     * @return 
     */
    public double fitness(){
        evaluate(root);
        
        double f, a ,b;
        a = (double) board.food_collected  / 89.00 ;
        b = (double) (board.total_step) / (89+55) - 1.00;
        /*if(a != 1){
            f = a;
        }else{
            f = a - b;
        }*/
        f = a;
        return f;
    }
    
    public void evaluate( Node n){
        if(board.total_step < 600 && board.food_collected < 89){
            switch(n.type){
                case 0:
                    evaluate( n.branch[0]);
                    evaluate( n.branch[1]);
                    break;
                case 1:
                    evaluate( n.branch[0]);
                    evaluate( n.branch[1]);
                    evaluate( n.branch[2]);
                    break;
                case 2:
                    if(checkfoodahead()){
                        evaluate(n.branch[0]);
                    }else{
                        evaluate(n.branch[1]);
                    }
                    break;
                case 3:     // turn left
                    move(-1);
                    break;
                case 4:     // forward
                    move(0);
                    break;
                case 5:     // right
                    move(1);
                    break;                
            }
        }
    }
    
    public void move( int towards ){
        board.d += towards;
        board.adjust_d();
        
        switch(board.d){
            case 0:     // move up
                board.y--;
                board.adjust_axis();
                break;
            case 1:     // move right
                board.x++;
                board.adjust_axis();
                break;
            case 2:     // move down
                board.y++;
                board.adjust_axis();
                break;
            case 3:     // move left
                board.x--;
                board.adjust_axis();
                break;         
        }
        collectFood();
    }
        
    public boolean checkfoodahead(){
        boolean foodahead = false;
        int x,y;
        switch(board.d){
            case 0: // up
                y = board.y - 1;
                if(y < 0) y += 32;
                if(board.SantaFeTrail[board.x][y] == 1){
                    foodahead = true;
                }
                break;
            case 1: // right
                x = board.x + 1;
                if(x > 31) x -= 32;
                if(board.SantaFeTrail[x][board.y] == 1){
                    foodahead = true;
                }
                break;
            case 2: // down
                y = board.y + 1;
                if(y > 31) y -= 32;
                if(board.SantaFeTrail[board.x][y] == 1){
                    foodahead = true;
                }
                break;
            case 3:
                x = board.x - 1;
                if(x < 0) x += 32;
                if(board.SantaFeTrail[x][board.y] == 1){
                    foodahead = true;
                }
                break;
        }
        return foodahead;
    }
    
    public void collectFood(){
        
        if(board.SantaFeTrail[board.x][board.y] == 1){
            board.food_collected++;
        }
        
        board.total_step++;
        
        board.SantaFeTrail[board.x][board.y] = board.d + 10;
        
    }
    
    
    /** ==================================
     *      Functions to calculate the size of terms and non-terms
     *  ==================================
     */
    public void calc_size(){
        terms = 0;
        non_terms = 0;   
        countSize(root);
    }
    
    public void countSize(Node n){
        if(n.type >= 3){
            terms++;
        }else {
            non_terms++;
            for(int i = 0; i < n.branch_length; i++){
                if(n.branch[i] != null){
                    countSize(n.branch[i]);
                }
            }
        }
    }
    
    /** ==================================
     *      Functions to mutate
     *  ==================================
     */
    public void Mutation(){
        int position = random.nextInt(terms+non_terms) + 1;
        //System.out.println("Position: " + position);
        resetCount();
        TrackRoot(root, position);
        //System.out.println(position);
        
        for(int i = 0; i < node_tracker.branch_length; i++){
            if(node_tracker.branch[i] != null){
                deleteNode(node_tracker.branch[i]);
            }
        }
        
        grow(node_tracker.height, max_depth, node_tracker); 
        //System.out.println("Old node type: " + node_tracker.type);
        /*
        if(node_tracker.type <= 2){
            node_tracker.type = random.nextInt(3);
            
            switch(node_tracker.type){
                case 0:
                    if(node_tracker.branch[0].type == 4){
                        grow(node_tracker.height+1, max_depth, 
                                node_tracker.branch[0]);
                    }
                    if(node_tracker.branch[2] != null){
                        deleteNode(node_tracker.branch[2]);
                        node_tracker.branch[2] = null;
                    }
                    break;
                case 1:
                    if(node_tracker.branch[0].type == 4){
                        grow(node_tracker.height+1, max_depth, 
                                node_tracker.branch[0]);
                    }
                    if(node_tracker.branch[2] == null){
                        node_tracker.branch[2] = new Node();
                        node_tracker.branch[2].parent = node_tracker;
                        grow(node_tracker.height+1, max_depth, 
                                node_tracker.branch[2]);
                    }
                    break;
                case 2:
                    deleteNode(node_tracker.branch[0]);
                    node_tracker.branch[0] = null;
                    node_tracker.branch[0] = new Node();
                    node_tracker.branch[0].type = 4;
                    node_tracker.branch[0].parent = node_tracker;
                    node_tracker.branch[0].height = node_tracker.height + 1;
                    
                    if(node_tracker.branch[2] != null){
                        deleteNode(node_tracker.branch[2]);
                        node_tracker.branch[2] = null;
                    }
                    break;
            }
        }else if(node_tracker.type > 2){
            node_tracker.type = random.nextInt(3) + 3;
        }
        */
        //System.out.println("New node type: " + node_tracker.type);
    }
    
    public void resetCount(){
        count = 0;
    }
    
    public void TrackRoot(Node sub, int position){
        count++;
        
        if(count == position){
            node_tracker = sub;
        }else if(count < position ){
            for(int i = 0; i < sub.branch_length; i++){
                if(sub.branch[i] != null && count < position ){
                    TrackRoot(sub.branch[i], position);
                }
            }
        }
    }
    
    /** ==================================
     *      Functions to print the tree
     *  ==================================
     */
    public void printAnt(){
        if(isEmpty()){
            System.out.println("The Tree is Empty");
        }else{
            printNode(root);
            System.out.println();
        }
    }
    
    public void printNode(Node n){
        switch(n.type){
            case 0:
                System.out.printf("prog2 ");
                break;
            case 1:
                System.out.printf("prog3 ");
                break;
            case 2:
                System.out.printf("iffoodahead ");
                break;
            case 3:
                System.out.printf("l ");
                break;
            case 4:
                System.out.printf("m ");
                break;
            case 5:
                System.out.printf("r ");
                break;   
        }
        //System.out.println("test");
        for(int i = 0; i < n.branch_length; i++){
            if(n.branch[i] != null){
                printNode(n.branch[i]);
            }
        }        
    }
}
