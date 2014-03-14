/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cs324_hw4;

import javax.swing.JComponent;

/**
 *
 * @author 张涛
 */
public class Modeling {
    
    Matrix m = new Matrix();
    
    
    double xW;
    double yW;
    
    public Modeling(){
        xW = 0;
        yW = 0;
    }
    
    public double[] Map3D(
            double [] p,
            double [][] cT)
    {
        double [] tp = new double[3];   // x', y', z'
        ApplyTransform(p, cT, tp);
        // Project onto xy plane
        double [] xy_point = {tp[0],tp[1]};
        
        //System.out.printf("%.2f, %.2f, %.2f%n", tp1[0], tp1[1],tp1[2]);
        
        return xy_point;
    }
    
    public double[] Trans3D(
            double [] p,
            double [][] aT,
            double [][] cT)
    {
        double [] tp1 = new double[3];  // x', y', z'
        double [] tp2 = new double[3];  // x'', y'', z''
        ApplyTransform(p, aT, tp1);
        ApplyTransform(tp1, cT, tp2);

         // Project onto xy plane
        double [] xy_point = {tp2[0],tp2[1]};
        
        return xy_point;
    }
    
    public void ApplyTransform(
            double [] p,    // a point
            double [][] aT, // active transform
            double [] tp)   // transformed point
    {  
        // point to vecter
        double [][] v;
        v = m.makeMatrix(1,4);
        v[0][0] = p[0];
        v[0][1] = p[1];
        v[0][2] = p[2];
        v[0][3] = 1;
        
        // Multiply vector to active transform
        double [][] tv; // transformed vector
        tv = m.makeMatrix(1, 4);
        
        m.MultiplyMatrix(v, aT, tv);
        
        int i;
         
        // transform to point
        
        for(i = 0; i< 3; i++){
            tp[i] = tv[0][i]/tv[0][3];
            //System.out.printf("%.2f, ", tp[i]);
        }
        //System.out.println(tv[0][i]);
    }
}
