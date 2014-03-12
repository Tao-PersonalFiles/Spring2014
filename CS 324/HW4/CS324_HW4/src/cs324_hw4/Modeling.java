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
        
    }
    
    public void Move3D(
            double [] p,
            double [][] cT)
    {
        double [] tp1 = new double[3];   // x', y', z'
        ApplyTransform(p, cT, tp1);
        // Project onto xy plane
        xW = tp1[0];
        yW = tp1[1];
        
        //viewport.MoveTo(xW, yW);
        //xF = viewport.xF;
        //yF = viewport.yF;
    }
    
    public void Draw3D(
            double [] p,
            double [][] aT,
            double [][] cT)
    {
        double [] tp1 = new double[3];   // x', y', z'
        double [] tp2 = new double[3];  // x'', y'', z''
        ApplyTransform(p, aT, tp1);
        ApplyTransform(tp1, cT, tp2);

            // Project onto xy plane
        xW = tp2[0];
        yW = tp2[1];
        
        //viewport.MoveTo(xW, yW);
        
        
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
        int i;
        for( i = 0; i < tv.length;i++){
            tv[0][i] = 0;
        }
        
        m.MultiplyMatrix(v, aT, tv);
        
        // transform to point
        for(i = 0; i< 3; i++){
            tp[i] = tv[0][i]/tv[0][3];
        }
    }
}
