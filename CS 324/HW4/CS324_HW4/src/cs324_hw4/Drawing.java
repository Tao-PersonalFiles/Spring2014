/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cs324_hw4;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

/**
 *
 * @author 张涛
 */
public class Drawing extends JComponent{
    
    static int frameWidth;
    static int frameHeight;
    
    CameraTransform ct = new CameraTransform();
    Matrix matrix = new Matrix();
    Modeling model = new Modeling();
    Viewport v = new Viewport();
    
    // define all the enum transform code from CT
    CameraTransform.tfCode x_t = CameraTransform.tfCode.X_TRANS;
    CameraTransform.tfCode y_t = CameraTransform.tfCode.Y_TRANS;
    CameraTransform.tfCode z_t = CameraTransform.tfCode.Z_TRANS;
    CameraTransform.tfCode y_r = CameraTransform.tfCode.Y_ROT;
    CameraTransform.tfCode scale = CameraTransform.tfCode.SCALE;
    CameraTransform.tfCode origin = CameraTransform.tfCode.ORIGIN;
    
    public double [][] CAMERA = matrix.makeMatrix(4, 4);
          
    public Drawing(int w, int h){
        frameWidth = w;
        frameHeight = h;
    }
    
    @Override
    public void paintComponent(Graphics g){
        drawInfo(g);
        //drawFunction(g);
        //drawRubik(g);
        drawHallway(g);
    }
    
    public void drawInfo(Graphics g){
        // Write down My name and class at second quadrant
        g.drawString("Tao Zhang", 20, 20);
        g.drawString("CS 324", 20,40);
        g.drawString("Programming Assignment #4", 20, 60);
        g.drawString("Feb 14th, 2014", 20, 80);
    }
    
    public void drawAxis(Graphics g){
        double [] xy;
        double [] z_p = {0,0,10};
        double [] z_n = {0,0,-10};
        xy = model.Map3D(z_p, CAMERA);
        v.MoveTo(xy[0], xy[1]);
        xy = model.Map3D(z_n, CAMERA);
        v.DrawTo(g, xy[0], xy[1]);
        
        double [] y_p = {0,10,0};
        double [] y_n = {0,-10,0};
        xy = model.Map3D(y_p, CAMERA);
        v.MoveTo(xy[0], xy[1]);
        xy = model.Map3D(y_n, CAMERA);
        v.DrawTo(g, xy[0], xy[1]);
        
        double [] x_p = {10,0,0};
        double [] x_n = {-10,0,0};
        xy = model.Map3D(x_p, CAMERA);
        v.MoveTo(xy[0], xy[1]);
        xy = model.Map3D(x_n, CAMERA);
        v.DrawTo(g, xy[0], xy[1]);
    }
    
    //===================================================================
    //          Draw Functions
    //===================================================================
    public void drawFunction(Graphics g){
        ct.DefineCameraTransform(0, 0, 0, 60, 30, 30, 10);
        v.SetViewport(200,300,100,100);
        v.SetWindow(-1.25, -1.25, 1.25, 1.25);
        
        ct.getCameraTransform(CAMERA);
        
        drawAxis(g);
        
        double [] xy;
        double [] p = new double[3];
        double i,j;
        for(i = -1.25; i < 1.25; i+=0.1){
            p[0] = i;               // x
            p[1] = -1.25;           // y
            p[2] = f(p[0],p[1]);    // z
            xy = model.Map3D(p, CAMERA);
            v.MoveTo(xy[0], xy[1]);
            for(j = -1.25; j < 1.25; j+=0.1){
                p[0] = i;               // x
                p[1] = j;               // y
                p[2] = f(p[0],p[1]);    // z
                xy = model.Map3D(p, CAMERA);
                v.DrawTo(g, xy[0], xy[1]);
            }
        }
        
        for(j = -1.25; j < 1.25; j+=0.1){
            p[0] = -1.25;           // x
            p[1] = j;               // y
            p[2] = f(p[0],p[1]);    // z
            xy = model.Map3D(p, CAMERA);
            v.MoveTo(xy[0], xy[1]);
            for(i = -1.25; i < 1.25; i+=0.1){
                p[0] = i;               // x
                p[1] = j;               // y
                p[2] = f(p[0],p[1]);    // z
                xy = model.Map3D(p, CAMERA);
                v.DrawTo(g, xy[0], xy[1]);
            }
        }
        
    }
    
    public double f(double x, double y){
            return ( Math.pow(x, 2)+Math.pow(y,2)-Math.pow(x, 3)-8*x*Math.pow(y, 4) );
    }
    
    //===================================================================
    //          Draw Rubiks
    //===================================================================
    public void drawRubik(Graphics g){
        ct.DefineCameraTransform(0, 0, 0, 40, 30, 0, 20);
        v.SetViewport(150, 350, 200, 200);
        v.SetWindow(-5, -5, 5, 5);
        
        ct.getCameraTransform(CAMERA);
        //drawAxis(g);
        
        //double [][] cM = matrix.makeMatrix(4, 4);
        //matrix.copyMatrix(CAMERA, cM);
        
        //double [][] cT = matrix.makeMatrix(4, 4);
        
        double [][][] cube = {
            {{-1,-1,1}, {-1,1,1}, {1,1,1}, {1,-1,1}},  // front
            {{-1,-1,1}, {-1,-1,-1}, {1,-1,-1}, {1,-1,1}}, // bot
            {{-1,1,-1}, {-1,1,1}, {1,1,1}, {1,1,-1}},  // up
            {{-1,1,1}, {-1,1,-1}, {-1,-1,-1}, {-1,-1,1}},    // left
            {{1,1,1},{1,-1,1},{1,-1,-1},{1,1,-1}},  // right
            {{1,1,-1},{1,-1,-1},{-1,-1,-1},{-1,1,-1}}   // back 
        };
        
        //active tarnsformation for origin
        double [][] aT = matrix.makeMatrix(4, 4);
        double [][] M;      // tmp transform
        double [][] M2;     // tmp transform
        
        
        // middle 9
        ct.DefineElementaryTransform(aT, origin, 1);
        drawCube(cube,g,aT);
        
        // middle left
        aT = matrix.makeMatrix(4, 4);   // refresh
        ct.DefineElementaryTransform(aT, x_t, -5);
        drawCube(cube,g,aT);
        
        // middle right
        aT = matrix.makeMatrix(4, 4);   // refresh
        ct.DefineElementaryTransform(aT, x_t, 5);
        drawCube(cube,g,aT);
        
        // middle up
        aT = matrix.makeMatrix(4, 4);   // refresh
        ct.DefineElementaryTransform(aT, y_t, 5);
        drawCube(cube,g,aT);
        
        // middle bot
        aT = matrix.makeMatrix(4, 4);   // refresh
        ct.DefineElementaryTransform(aT, y_t, -5);
        drawCube(cube,g,aT);
        
        // middle left up
        aT = matrix.makeMatrix(4, 4);   // refresh
        M = matrix.makeMatrix(4, 4);    // refresh
        M2 = matrix.makeMatrix(4, 4);   // refresh
        ct.DefineElementaryTransform(aT, x_t, -5);
        
        ct.DefineElementaryTransform(M, y_t, 5);
        matrix.MultiplyMatrix(aT, M, M2);
        matrix.copyMatrix(M2, aT);
        drawCube(cube,g,aT);
        
        // middle left bot
        aT = matrix.makeMatrix(4, 4);   // refresh
        M = matrix.makeMatrix(4, 4);    // refresh
        M2 = matrix.makeMatrix(4, 4);   // refresh
        ct.DefineElementaryTransform(aT, x_t, -5);
        
        ct.DefineElementaryTransform(M, y_t, -5);
        matrix.MultiplyMatrix(aT, M, M2);
        matrix.copyMatrix(M2, aT);
        drawCube(cube,g,aT);
        
        // middle right up
        aT = matrix.makeMatrix(4, 4);   // refresh
        M = matrix.makeMatrix(4, 4);    // refresh
        M2 = matrix.makeMatrix(4, 4);   // refresh
        ct.DefineElementaryTransform(aT, x_t, 5);
        
        ct.DefineElementaryTransform(M, y_t, 5);
        matrix.MultiplyMatrix(aT, M, M2);
        matrix.copyMatrix(M2, aT);
        drawCube(cube,g,aT);
        
        // middle right bot
        aT = matrix.makeMatrix(4, 4);   // refresh
        M = matrix.makeMatrix(4, 4);    // refresh
        M2 = matrix.makeMatrix(4, 4);   // refresh
        ct.DefineElementaryTransform(aT, x_t, 5);
        
        ct.DefineElementaryTransform(M, y_t, -5);
        matrix.MultiplyMatrix(aT, M, M2);
        matrix.copyMatrix(M2, aT);
        drawCube(cube,g,aT);
        
        g.setColor(Color.red);
        
        // middle front
        aT = matrix.makeMatrix(4, 4);   // refresh
        ct.DefineElementaryTransform(aT, z_t, 5);
        
        drawCube(cube,g,aT);
        
        // front - left
        aT = matrix.makeMatrix(4, 4);   // refresh
        M = matrix.makeMatrix(4, 4);    // refresh
        M2 = matrix.makeMatrix(4, 4);   // refresh
        ct.DefineElementaryTransform(aT, z_t, 5);
        ct.DefineElementaryTransform(M, x_t, -5);
        matrix.MultiplyMatrix(aT, M, M2);
        matrix.copyMatrix(M2, aT);
        
        drawCube(cube,g,aT);
        
        // front - right
        aT = matrix.makeMatrix(4, 4);   // refresh
        M = matrix.makeMatrix(4, 4);    // refresh
        M2 = matrix.makeMatrix(4, 4);   // refresh
        ct.DefineElementaryTransform(aT, z_t, 5);
        ct.DefineElementaryTransform(M, x_t, 5);
        matrix.MultiplyMatrix(aT, M, M2);
        matrix.copyMatrix(M2, aT);
        
        drawCube(cube,g,aT);
        
        // front - up
        aT = matrix.makeMatrix(4, 4);   // refresh
        M = matrix.makeMatrix(4, 4);    // refresh
        M2 = matrix.makeMatrix(4, 4);   // refresh
        ct.DefineElementaryTransform(aT, z_t, 5);
        ct.DefineElementaryTransform(M, y_t, 5);
        matrix.MultiplyMatrix(aT, M, M2);
        matrix.copyMatrix(M2, aT);
        
        drawCube(cube,g,aT);
        
        // front - bot
        aT = matrix.makeMatrix(4, 4);   // refresh
        M = matrix.makeMatrix(4, 4);    // refresh
        M2 = matrix.makeMatrix(4, 4);   // refresh
        ct.DefineElementaryTransform(aT, z_t, 5);
        ct.DefineElementaryTransform(M, y_t, -5);
        matrix.MultiplyMatrix(aT, M, M2);
        matrix.copyMatrix(M2, aT);
        
        drawCube(cube,g,aT);
        
        // front - left up
        aT = matrix.makeMatrix(4, 4);   // refresh
        M = matrix.makeMatrix(4, 4);    // refresh
        M2 = matrix.makeMatrix(4, 4);   // refresh
        ct.DefineElementaryTransform(aT, z_t, 5);
        
        ct.DefineElementaryTransform(M, x_t, -5);
        matrix.MultiplyMatrix(aT, M, M2);
        matrix.copyMatrix(M2, aT);
        
        M = matrix.makeMatrix(4, 4);    // refresh
        M2 = matrix.makeMatrix(4, 4);   // refresh
        ct.DefineElementaryTransform(M, y_t, 5);
        matrix.MultiplyMatrix(aT, M, M2);
        matrix.copyMatrix(M2, aT);
                
        drawCube(cube,g,aT);
        
        // front - left bot
        aT = matrix.makeMatrix(4, 4);   // refresh
        M = matrix.makeMatrix(4, 4);    // refresh
        M2 = matrix.makeMatrix(4, 4);   // refresh
        ct.DefineElementaryTransform(aT, z_t, 5);
        
        ct.DefineElementaryTransform(M, x_t, -5);
        matrix.MultiplyMatrix(aT, M, M2);
        matrix.copyMatrix(M2, aT);
        
        M = matrix.makeMatrix(4, 4);    // refresh
        M2 = matrix.makeMatrix(4, 4);   // refresh
        ct.DefineElementaryTransform(M, y_t, -5);
        matrix.MultiplyMatrix(aT, M, M2);
        matrix.copyMatrix(M2, aT);
                
        drawCube(cube,g,aT);
        
        // front - right up
        aT = matrix.makeMatrix(4, 4);   // refresh
        M = matrix.makeMatrix(4, 4);    // refresh
        M2 = matrix.makeMatrix(4, 4);   // refresh
        ct.DefineElementaryTransform(aT, z_t, 5);
        
        ct.DefineElementaryTransform(M, x_t, 5);
        matrix.MultiplyMatrix(aT, M, M2);
        matrix.copyMatrix(M2, aT);
        
        M = matrix.makeMatrix(4, 4);    // refresh
        M2 = matrix.makeMatrix(4, 4);   // refresh
        ct.DefineElementaryTransform(M, y_t, 5);
        matrix.MultiplyMatrix(aT, M, M2);
        matrix.copyMatrix(M2, aT);
                
        drawCube(cube,g,aT);
        
        // front - right bot
        aT = matrix.makeMatrix(4, 4);   // refresh
        M = matrix.makeMatrix(4, 4);    // refresh
        M2 = matrix.makeMatrix(4, 4);   // refresh
        ct.DefineElementaryTransform(aT, z_t, 5);
        
        ct.DefineElementaryTransform(M, x_t, 5);
        matrix.MultiplyMatrix(aT, M, M2);
        matrix.copyMatrix(M2, aT);
        
        M = matrix.makeMatrix(4, 4);    // refresh
        M2 = matrix.makeMatrix(4, 4);   // refresh
        ct.DefineElementaryTransform(M, y_t, -5);
        matrix.MultiplyMatrix(aT, M, M2);
        matrix.copyMatrix(M2, aT);
                
        drawCube(cube,g,aT);
        
        g.setColor(Color.blue);
        // middle back
        aT = matrix.makeMatrix(4, 4);   // refresh
        ct.DefineElementaryTransform(aT, z_t, -5);
        drawCube(cube,g,aT);
        
        // back - left
        aT = matrix.makeMatrix(4, 4);   // refresh
        M = matrix.makeMatrix(4, 4);    // refresh
        M2 = matrix.makeMatrix(4, 4);   // refresh
        ct.DefineElementaryTransform(aT, z_t, -5);
        ct.DefineElementaryTransform(M, x_t, -5);
        matrix.MultiplyMatrix(aT, M, M2);
        matrix.copyMatrix(M2, aT);
        
        drawCube(cube,g,aT);
        
        // ack - right
        aT = matrix.makeMatrix(4, 4);   // refresh
        M = matrix.makeMatrix(4, 4);    // refresh
        M2 = matrix.makeMatrix(4, 4);   // refresh
        ct.DefineElementaryTransform(aT, z_t, -5);
        ct.DefineElementaryTransform(M, x_t, 5);
        matrix.MultiplyMatrix(aT, M, M2);
        matrix.copyMatrix(M2, aT);
        
        drawCube(cube,g,aT);
        
        // back - up
        aT = matrix.makeMatrix(4, 4);   // refresh
        M = matrix.makeMatrix(4, 4);    // refresh
        M2 = matrix.makeMatrix(4, 4);   // refresh
        ct.DefineElementaryTransform(aT, z_t, -5);
        ct.DefineElementaryTransform(M, y_t, 5);
        matrix.MultiplyMatrix(aT, M, M2);
        matrix.copyMatrix(M2, aT);
        
        drawCube(cube,g,aT);
        
        // back - bot
        aT = matrix.makeMatrix(4, 4);   // refresh
        M = matrix.makeMatrix(4, 4);    // refresh
        M2 = matrix.makeMatrix(4, 4);   // refresh
        ct.DefineElementaryTransform(aT, z_t, -5);
        ct.DefineElementaryTransform(M, y_t, -5);
        matrix.MultiplyMatrix(aT, M, M2);
        matrix.copyMatrix(M2, aT);
        
        drawCube(cube,g,aT);
        
        // back - left up
        aT = matrix.makeMatrix(4, 4);   // refresh
        M = matrix.makeMatrix(4, 4);    // refresh
        M2 = matrix.makeMatrix(4, 4);   // refresh
        ct.DefineElementaryTransform(aT, z_t, -5);
        
        ct.DefineElementaryTransform(M, x_t, -5);
        matrix.MultiplyMatrix(aT, M, M2);
        matrix.copyMatrix(M2, aT);
        
        M = matrix.makeMatrix(4, 4);    // refresh
        M2 = matrix.makeMatrix(4, 4);   // refresh
        ct.DefineElementaryTransform(M, y_t, 5);
        matrix.MultiplyMatrix(aT, M, M2);
        matrix.copyMatrix(M2, aT);
                
        drawCube(cube,g,aT);
        
        // back - left bot
        aT = matrix.makeMatrix(4, 4);   // refresh
        M = matrix.makeMatrix(4, 4);    // refresh
        M2 = matrix.makeMatrix(4, 4);   // refresh
        ct.DefineElementaryTransform(aT, z_t, -5);
        
        ct.DefineElementaryTransform(M, x_t, -5);
        matrix.MultiplyMatrix(aT, M, M2);
        matrix.copyMatrix(M2, aT);
        
        M = matrix.makeMatrix(4, 4);    // refresh
        M2 = matrix.makeMatrix(4, 4);   // refresh
        ct.DefineElementaryTransform(M, y_t, -5);
        matrix.MultiplyMatrix(aT, M, M2);
        matrix.copyMatrix(M2, aT);
                
        drawCube(cube,g,aT);
        
        // back - right up
        aT = matrix.makeMatrix(4, 4);   // refresh
        M = matrix.makeMatrix(4, 4);    // refresh
        M2 = matrix.makeMatrix(4, 4);   // refresh
        ct.DefineElementaryTransform(aT, z_t, -5);
        
        ct.DefineElementaryTransform(M, x_t, 5);
        matrix.MultiplyMatrix(aT, M, M2);
        matrix.copyMatrix(M2, aT);
        
        M = matrix.makeMatrix(4, 4);    // refresh
        M2 = matrix.makeMatrix(4, 4);   // refresh
        ct.DefineElementaryTransform(M, y_t, 5);
        matrix.MultiplyMatrix(aT, M, M2);
        matrix.copyMatrix(M2, aT);
                
        drawCube(cube,g,aT);
        
        // back - right bot
        aT = matrix.makeMatrix(4, 4);   // refresh
        M = matrix.makeMatrix(4, 4);    // refresh
        M2 = matrix.makeMatrix(4, 4);   // refresh
        ct.DefineElementaryTransform(aT, z_t, -5);
        
        ct.DefineElementaryTransform(M, x_t, 5);
        matrix.MultiplyMatrix(aT, M, M2);
        matrix.copyMatrix(M2, aT);
        
        M = matrix.makeMatrix(4, 4);    // refresh
        M2 = matrix.makeMatrix(4, 4);   // refresh
        ct.DefineElementaryTransform(M, y_t, -5);
        matrix.MultiplyMatrix(aT, M, M2);
        matrix.copyMatrix(M2, aT);
                
        drawCube(cube,g,aT);
        
    }
    
    public void drawCube(double [][][] cube, Graphics g, double [][] aT)
    {
        int i;
        for(i = 0; i < cube.length; i++){
            //System.out.println("Square["+i+"]");
            drawSquare(cube[i], g, aT);
        }
    }
    
    public void drawSquare(double [][] square, Graphics g, double [][] aT)
    {
        double [] xy;
        xy = model.Trans3D(square[0], aT, CAMERA);
        //System.out.printf("Model: x %.2f, y %.2f%n",xy[0], xy[1]);
        v.MoveTo(xy[0], xy[1]);
        //System.out.printf("Viewport: xV %.2f, yV %.2f%n",v.xV, v.yV);
        int i;
        for(i = 1; i < square.length; i++){
            xy = model.Trans3D(square[i],aT, CAMERA);
            //System.out.printf("Model: x %.2f, y %.2f%n",xy[0], xy[1]);
            v.DrawTo(g, xy[0], xy[1]);
            //System.out.printf("Viewport: xV %.2f, yV %.2f%n",v.xV, v.yV);
        }
        xy = model.Trans3D(square[0], aT, CAMERA);
        //System.out.printf("Model: x %.2f, y %.2f%n",xy[0], xy[1]);
        v.DrawTo(g, xy[0], xy[1]);
        //System.out.printf("Viewport: xV %.2f, yV %.2f%n",v.xV, v.yV);
    }
    
    //===================================================================
    //          Draw Hallway
    //===================================================================
    public void drawHallway(Graphics g){
        ct.DefineCameraTransform(0, 0, 0, 30, 45, 0, 200);
        v.SetViewport(0, 500, 500, 500);
        v.SetWindow(-50, -50, 50, 50);
        
        ct.getCameraTransform(CAMERA);
        
        double [][][] hallway = {
            {{0,0,0},{10,0,0},{10,10,0},{0,10,0}},
            {{0,0,0},{0,0,50},{0,10,50},{0,10,0}},
            {{0,0,0},{0,0,50},{10,0,50},{10,0,0}},
            {{10,10,50},{10,10,0},{0,10,0},{0,10,50}},
            {{10,10,50},{0,10,50},{0,0,50},{10,0,50}},
            {{10,10,50},{10,10,0},{10,0,0},{10,0,50}}
        };
        
        double [][] door = {
            {0,0,0},{2,0,0},{2,6,0},{0,6,0}
        };
        
        double [][] aT = matrix.makeMatrix(4, 4);   // the active transformation
        double [][] M = matrix.makeMatrix(4, 4);    // tmp transform
        double [][] M2 = matrix.makeMatrix(4, 4);   // tmp transform
        ct.DefineElementaryTransform(aT, origin, 1);
        
            // draw the main hallway
        drawCube(hallway, g, aT);
        
        // draw the little groove 
        double [][][] cube = {
            {{-1,-1,1}, {-1,1,1}, {1,1,1}, {1,-1,1}},  // front
            {{-1,-1,1}, {-1,-1,-1}, {1,-1,-1}, {1,-1,1}}, // bot
            {{-1,1,-1}, {-1,1,1}, {1,1,1}, {1,1,-1}},  // up
            {{-1,1,1}, {-1,1,-1}, {-1,-1,-1}, {-1,-1,1}},    // left
            {{1,1,1},{1,-1,1},{1,-1,-1},{1,1,-1}},  // right
            {{1,1,-1},{1,-1,-1},{-1,-1,-1},{-1,1,-1}}   // back 
        };
        
        
        
        
        
        //drawCube(cube, g, aT);
        
        //draw all the doors
        ct.DefineElementaryTransform(aT, x_t, 6);
        drawSquare(door, g, aT);
        
        aT = matrix.makeMatrix(4, 4);   // refresh
        ct.DefineElementaryTransform(aT, origin, 1);
        ct.DefineElementaryTransform(M, y_r, -90);
        matrix.MultiplyMatrix(aT, M, M2);
        matrix.copyMatrix(M2, aT);
        drawSquare(door, g, aT);
        
        
        
    }
    
    public void drawName(Graphics g){
        double [][][] T = {
            {{},{},{},{}},
            {{},{},{},{}},
            {{},{},{},{}},
            {{},{},{},{}},
            {{},{},{},{}},
            {{},{},{},{}}
        };
        
        double [][][] A = {
            
        };
        
        double [][][] O = {
            
        };
    }
}
