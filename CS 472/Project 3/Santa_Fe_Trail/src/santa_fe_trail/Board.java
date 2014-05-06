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
public final class Board {
    int SantaFeTrail[][] = new int[32][32];
    
    int x, y;
    int d;
    
    //int food_step;
    int total_step;
    
    int food_collected;
    
    int total_food;
    
    public Board(){
        x =0;
        y = 0;
        d = 1;
        //food_step = 0;
        total_step = 0;
        food_collected = 0;
        setBoard();
    }
    
    public void adjust_d(){
        if(this.d < 0){
            this.d += 4;
        }else if(this.d > 3){
            this.d -= 4;
        }
    }
    
    public void adjust_axis(){
        if(this.x < 0){
            this.x += 32;
        }else if(this.x > 31){
            this.x -= 32;
        }
        
        if(this.y < 0){
            this.y += 32;
        }else if(this.y > 31){
            this.y -= 32;
        }
    }
    
    public void setBoard(){
    //  Set up Santa Fe Trail --------------------------------------------------------------------
		for ( int i=0; i<32; i++){
			for ( int j=0; j<32; j++){
				SantaFeTrail[i][j] = 0;
			}
		}
		
		SantaFeTrail[1][0] = 1;
		SantaFeTrail[2][0] = 1;
		SantaFeTrail[3][0] = 1;

		SantaFeTrail[3][1] = 1;
		SantaFeTrail[3][2] = 1;
		SantaFeTrail[3][3] = 1;
		SantaFeTrail[3][4] = 1;
		SantaFeTrail[3][5] = 1;
		
		SantaFeTrail[4][5] = 1;
		SantaFeTrail[5][5] = 1;
		SantaFeTrail[6][5] = 1;
		
		SantaFeTrail[8][5] = 1;
		SantaFeTrail[9][5] = 1;
		SantaFeTrail[10][5] = 1;
		SantaFeTrail[11][5] = 1;
		SantaFeTrail[12][5] = 1;
		
		SantaFeTrail[12][6] = 1;
		SantaFeTrail[12][7] = 1;
		SantaFeTrail[12][8] = 1;
		SantaFeTrail[12][9] = 1;
		
		SantaFeTrail[12][11] = 1;
		SantaFeTrail[12][12] = 1;
		SantaFeTrail[12][13] = 1;
		SantaFeTrail[12][14] = 1;
		
		SantaFeTrail[12][17] = 1;
		SantaFeTrail[12][18] = 1;
		SantaFeTrail[12][19] = 1;
		SantaFeTrail[12][20] = 1;
		SantaFeTrail[12][21] = 1;
		SantaFeTrail[12][22] = 1;
		SantaFeTrail[12][23] = 1;
		
		SantaFeTrail[11][24] = 1;
		SantaFeTrail[10][24] = 1;
		SantaFeTrail[9][24] = 1;
		SantaFeTrail[8][24] = 1;
		SantaFeTrail[7][24] = 1;
		
		SantaFeTrail[4][24] = 1;
		SantaFeTrail[3][24] = 1;
		
		SantaFeTrail[1][25] = 1;
		SantaFeTrail[1][26] = 1;
		SantaFeTrail[1][27] = 1;
		SantaFeTrail[1][28] = 1;
		
		SantaFeTrail[2][30] = 1;
		SantaFeTrail[3][30] = 1;
		SantaFeTrail[4][30] = 1;
		SantaFeTrail[5][30] = 1;
		
		SantaFeTrail[7][29] = 1;
		SantaFeTrail[7][28] = 1;
		
		SantaFeTrail[8][27] = 1;
		SantaFeTrail[9][27] = 1;
		SantaFeTrail[10][27] = 1;
		SantaFeTrail[11][27] = 1;
		SantaFeTrail[12][27] = 1;
		SantaFeTrail[13][27] = 1;
		SantaFeTrail[14][27] = 1;
		
		SantaFeTrail[16][26] = 1;
		SantaFeTrail[16][25] = 1;
		SantaFeTrail[16][24] = 1;
		
		SantaFeTrail[16][21] = 1;
		SantaFeTrail[16][20] = 1;
		SantaFeTrail[16][19] = 1;
		SantaFeTrail[16][18] = 1;
		
		SantaFeTrail[17][15] = 1;
		
		SantaFeTrail[20][14] = 1;
		SantaFeTrail[20][13] = 1;
		
		SantaFeTrail[20][10] = 1;
		SantaFeTrail[20][9] = 1;
		SantaFeTrail[20][8] = 1;
		SantaFeTrail[20][7] = 1;
		
		SantaFeTrail[21][5] = 1;
		SantaFeTrail[22][5] = 1;
		
		SantaFeTrail[24][4] = 1;
		SantaFeTrail[24][3] = 1;
		
		SantaFeTrail[25][2] = 1;
		SantaFeTrail[26][2] = 1;
		SantaFeTrail[27][2] = 1;
		
		SantaFeTrail[29][3] = 1;
		SantaFeTrail[29][4] = 1;
		
		SantaFeTrail[29][6] = 1;
		
		SantaFeTrail[29][9] = 1;
		
		SantaFeTrail[29][12] = 1;

		SantaFeTrail[28][14] = 1;
		SantaFeTrail[27][14] = 1;
		SantaFeTrail[26][14] = 1;
		
		SantaFeTrail[23][15] = 1;
		
		SantaFeTrail[24][18] = 1;
		
		SantaFeTrail[27][19] = 1;
		
		SantaFeTrail[26][22] = 1;

		SantaFeTrail[23][23] = 1;
    }
    
    public void printBoard(){
        total_food = 0;
        for ( int j=0; j<32; j++){
	    for ( int i=0; i<32; i++){
                //System.out.printf("%d ", SantaFeTrail[i][j]);
                
                switch(SantaFeTrail[i][j]){
                    case 0:
                        System.out.printf("  ");
                        break;
                    case 1:
                        System.out.printf("1 ");
                        total_food++;
                        break;
                    case 10:
                        System.out.printf("A ");
                        break;
                    case 11:
                        System.out.printf("> ");
                        break;
                    case 12:
                        System.out.printf("V ");
                        break;
                    case 13:
                        System.out.printf("< ");
                        break;
                }
            }
            System.out.println();
	}
    }
    
}
