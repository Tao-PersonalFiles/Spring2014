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
public class Frame extends JComponent{
    
    Matrix m = new Matrix();
    
    static int frameWidth;
    static int frameHeight;
    
    //set an fixed matrix that remain aT unchange
    double [][] fM = m.makeMatrix(4, 4);
    
    Viewport v = new Viewport();
    CameraTransform ct = new CameraTransform();
    Modeling model = new Modeling();
    
    public Frame(int w, int h){
        frameWidth = w;
        frameHeight = h;
        fM[0][0] = 1;
        fM[1][1] = 1;
        fM[2][2] = 1;
        fM[3][3] = 1;
    }
    
    @Override
    public void paintComponent(Graphics g){
        drawInfo(g);
        //drawFunction(g);
        drawRubik(g);
    }
    
    public void testViewport(){
        
    }
    
    public void drawInfo(Graphics g){
        // Write down My name and class at second quadrant
        g.drawString("Tao Zhang", 20, 20);
        g.drawString("CS 324", 20,40);
        g.drawString("Programming Assignment #2", 20, 60);
        g.drawString("Feb 14th, 2014", 20, 80);
    }
    
    public void drawAxis(Graphics g){
        double [] z_p = {0,0,100};
        double [] z_n = {0,0,-100};
        model.Map3D(z_p, ct.CAMERA);
        v.MoveTo(model.xW, model.yW);
        model.Map3D(z_n, fM);
        v.DrawTo(g, model.xW, model.yW);
        
        double [] y_p = {0,100,0};
        double [] y_n = {0,-100,0};
        model.Map3D(y_p, ct.CAMERA);
        v.MoveTo(model.xW, model.yW);
        model.Map3D(y_n, fM);
        v.DrawTo(g, model.xW, model.yW);
        
        double [] x_p = {100,0,0};
        double [] x_n = {-100,0,0};
        model.Map3D(x_p, ct.CAMERA);
        v.MoveTo(model.xW, model.yW);
        model.Map3D(x_n, fM);
        v.DrawTo(g, model.xW, model.yW);
        
    }
    
    public void drawFunction(Graphics g){
        double x,y;
        // set Viewport
        
        v.SetViewport(100,400,250,250);
        v.SetWindow(-1.25, -1.25, 1.25, 1.25);
        // set Camera Transform matrix
        ct.DefineCameraTransform(0,0,0,30,0,15,10);
        
        double [] p = new double [3];
        // start draw function
        double i,j;
        for(i = -1.25; i < 1.25; i+=0.2){
            p[0] = i; //x
            p[1] = 1.25; //y
            p[2] = f(i, 1.25); //z
            model.Map3D(p, ct.CAMERA);
            x = model.xW;
            y = model.yW;
            v.MoveTo(x, y);
            for(j = -1.25; j < 1.25; j+= 0.2){
                p[0] = i; //x
                p[1] = j; //y
                p[2] = f(i,j); //z

                model.Map3D(p, ct.CAMERA);
                v.DrawTo(g, model.xW, model.yW);
            }
        }
        
        
        
        for(j = -1.25; j < 1.25; j+=0.2){
            p[0] = 1.25; //x
            p[1] = j; //y
            p[2] = f(1.25,j); //z
            model.Map3D(p, ct.CAMERA);
            x = model.xW;
            y = model.yW;
            v.MoveTo(x, y);
            for(i = -1.25; i < 1.25; i+= 0.2){
                p[0] = i; //x
                p[1] = j; //y
                p[2] = f(i,j); //z

                model.Map3D(p, ct.CAMERA);
                v.DrawTo(g, model.xW, model.yW);
            }
        }
        
        
    }
    
    public double f(double x, double y){
            return ( Math.pow(x, 2)+Math.pow(y,2)-Math.pow(x, 3)-8*x*Math.pow(y, 4) );
        }
    
    public void drawRubik(Graphics g){
        // set Viewport
        v.SetViewport(100,400,100,100);
        v.SetWindow(-5, -5, 5, 5);
        // set Camera Transform matrix
        ct.DefineCameraTransform(0,0,0,30,45,60,20);
        
       // drawAxis(g);
        
        // front
        double [][][] cube = {
            {{-1,-1,1}, {-1,1,1}, {1,1,1}, {1,-1,1}},  // front
            {{-1,-1,1}, {-1,-1,-1}, {1,-1,-1}, {1,-1,1}}, // bot
            {{-1,1,-1}, {-1,1,1}, {1,1,1}, {1,1,-1}},  // up
            {{-1,1,1}, {-1,1,-1}, {-1,-1,-1}, {-1,-1,1}},    // left
            {{1,1,1},{1,-1,1},{1,-1,-1},{1,1,-1}},  // right
            {{1,1,-1},{1,-1,-1},{-1,-1,-1},{-1,1,-1}}   // back 
        };
        
        drawCube(cube,g);
        
        
        
    }
    
    public void drawCube(
            double [][][] cube, Graphics g)
    {
        int i;
        for(i = 0; i < cube.length; i++){
            drawSquare(cube[i],g);
        }
    }
    
    public void drawSquare(
            double [][] square, Graphics g)
    {
        double [] xy;
        xy = model.Map3D(square[0], ct.CAMERA);
        v.MoveTo(xy[0], xy[1]);
        int i,j;
        for(i = 1; i < 4; i++){
            xy = model.Map3D(square[i], ct.CAMERA);
            v.DrawTo(g, xy[0], xy[1]);     
        }
        xy = model.Map3D(square[0], ct.CAMERA);
        v.DrawTo(g, xy[0], xy[1]);
    }
    
    public void drawHallway(){
        // set Viewport
        v.SetViewport(100,frameHeight-200,frameWidth -200,100);
        v.SetWindow(-10, -10, 10, 10);
        // set Camera Transform matrix
        ct.DefineCameraTransform(0,0,0,0,0,0,10);
        
        
    }
    
    public void draw3DName(){
        
    }
}
