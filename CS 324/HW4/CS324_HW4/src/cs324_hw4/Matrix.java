/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cs324_hw4;

/**
 *
 * @author 张涛
 */
public class Matrix {
    
    public Matrix(){
        
    }
    
    /* This function just make a matrix  
     * by reading the number of rows and cols
     * with all values set to be 0
     */
    public double [][] makeMatrix(int row, int col){
        double [][] M = new double[row][col];
        int i,j;
        for(i = 0; i < M.length; i++){
            for(j = 0; j < M[i].length; j++){
                M[i][j] = 0;
            }
        }
        return M;
    }
    
    /* Function to mutiply two matrix
     */
    public void MultiplyMatrix(
            double [][] tfM, double [][] M1, double [][] M2){
        int i,j,k;
        for(i = 0; i < tfM.length && i < M1.length; i++){
            for(j = 0; j < tfM[i].length && j < M1[i].length; j++){
                for(k = 0; k < tfM[i].length && k < M1.length; k++){
                    M2[i][j] += tfM[i][k] * M1[k][j];
                }
            }
        }
    }
    
    /* Function to copy the values of a matrix to another 
     */
    public void copyMatrix(double [][] M, double [][] copy_m){
        int row = M.length;
        int col = M[0].length;        
        int i,j;
        for(i = 0; i < row; i++){
            for(j = 0; j < col; j++){
                copy_m[i][j] = M[i][j];
            }
        }
    }
    
    /* Function to print out the matrix
     */
    public void printMatrix(double [][] m){
        int i,j;
        for(i = 0; i < m.length; i++){
            System.out.printf("| ");
            for(j = 0; j < m[i].length; j++){
                System.out.printf("%.2f  ", m[i][j]);
            }
            System.out.printf(" | ");
            System.out.println();
        }
    }
}
