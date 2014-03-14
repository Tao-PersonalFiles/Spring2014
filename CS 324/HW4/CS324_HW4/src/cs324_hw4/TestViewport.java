/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cs324_hw4;

import java.awt.Graphics;
import javax.swing.JComponent;

/**
 *
 * @author 张涛
 */
public class TestViewport extends JComponent{
    
    static int frameWidth;
    static int frameHeight;
    
    Viewport v = new Viewport();
    Modeling model = new Modeling();
    Matrix m = new Matrix();
    CameraTransform ct = new CameraTransform(1,0,1,30,45,0,20);
    
    public TestViewport(int w, int h){
        frameWidth = w;
        frameHeight = h;
    }
    
    @Override
    public void paintComponent(Graphics g){
        draw();
    }
    
    public void draw()
    {
        double [] xy;
        v.SetViewport(0,500,300,300);
        v.SetWindow(-5, -5, 5, 5);
        double [][] p = {{1,1,1},{1,1,-1},{1,-1,1},{1,-1,-1},{-1,1,1},{-1,1,-1},{-1,-1,1},{-1,-1,-1}};
        int i;
        for(i = 0; i < p.length; i++){
            xy = model.Map3D(p[i], ct.CAMERA);
            System.out.println("Model: "+ xy[0] + " " + xy[1]);
            v.MoveTo(xy[0], xy[1]);
            System.out.println("viewport: "+v.xV + " " + v.yV);
        }
    }
}
