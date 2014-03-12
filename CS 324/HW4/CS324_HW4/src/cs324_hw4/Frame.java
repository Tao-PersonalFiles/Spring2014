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
    
    public void drawInfo(Graphics g){
        // Write down My name and class at second quadrant
        g.drawString("Tao Zhang", 20, 20);
        g.drawString("CS 324", 20,40);
        g.drawString("Programming Assignment #2", 20, 60);
        g.drawString("Feb 14th, 2014", 20, 80);
        
    }
    
    public void drawFunction(Graphics g){
        double x,y;
        // set Viewport
        
        v.SetViewport(100,frameHeight-200,frameWidth -200,100);
        v.SetWindow(-1.25, -1.25, 1.25, 1.25);
        // set Camera Transform matrix
        ct.DefineCameraTransform(0,0,0,45,45,45,100);
        
        double [] p = new double [3];
        // start draw function
        double i,j;
        for(i = -1.25; i < 1.25; i+=0.1){
            p[0] = i; //x
            p[1] = 1.25; //y
            p[2] = Math.pow(i, 2)+Math.pow(1.25,2)-Math.pow(i, 3)-8*i*Math.pow(1.25, 4); //z
            model.Move3D(p, ct.CAMERA);
            x = model.xW;
            y = model.yW;
            v.MoveTo(x, y);
            for(j = -1.25; j < 1.25; j+= 0.1){
                p[0] = i; //x
                p[1] = j; //y
                p[2] = Math.pow(i, 2)+Math.pow(j,2)-Math.pow(i, 3)-8*i*Math.pow(j, 4); //z

                model.Draw3D(p, fM, ct.CAMERA);
                v.DrawTo(g, model.xW, model.yW);
            }
        }
        
        for(j = -1.25; j < 1.25; j+=0.1){
            p[0] = 1.25; //x
            p[1] = j; //y
            p[2] = Math.pow(1.25, 2)+Math.pow(j,2)-Math.pow(1.25, 3)-8*1.25*Math.pow(j, 4); //z
            model.Move3D(p, ct.CAMERA);
            x = model.xW;
            y = model.yW;
            v.MoveTo(x, y);
            for(i = -1.25; i < 1.25; i+= 0.1){
                p[0] = i; //x
                p[1] = j; //y
                p[2] = Math.pow(i, 2)+Math.pow(j,2)-Math.pow(i, 3)-8*i*Math.pow(j, 4); //z

                model.Draw3D(p, fM, ct.CAMERA);
                v.DrawTo(g, model.xW, model.yW);
            }
        }
        
        
    }
    
    public void drawRubik(Graphics g){
        // set Viewport
        v.SetViewport(100,frameHeight-200,frameWidth -200,100);
        v.SetWindow(-10, -10, 10, 10);
        // set Camera Transform matrix
        ct.DefineCameraTransform(0,0,0,0,0,0,10);
        
        // front
        double [][] square = {
            {-1,-1,1},
            {-1,1,1},
            {1,1,1},
            {1,-1,1}
        };
        
        drawSquare(square,g);
        
        //  bottom
        double [][] M = m.makeMatrix(4, 4);
        double [][] cT = m.makeMatrix(4, 4);
        CameraTransform.tfCode x_r = CameraTransform.tfCode.X_ROT;
        ct.DefineElementaryTransform(M,x_r,90);
        m.MultiplyMatrix(M, ct.CAMERA, cT);
        //draw
        
        
    }
    
    public void drawSquare(
            double [][] square, Graphics g
            ){
        model.Move3D(square[0], ct.CAMERA);
        v.MoveTo(model.xW, model.yW);
        int i,j;
        for(i = 1; i < 4; i++){
            model.Draw3D(square[i], fM, ct.CAMERA);
            v.DrawTo(g, model.xW, model.yW);     
        }
        model.Draw3D(square[0], fM, ct.CAMERA);
        v.DrawTo(g, model.xW, model.yW);
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
