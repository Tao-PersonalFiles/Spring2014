/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cs324_hw2;

import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author TaoZhang
 */
public class CS324_HW2 extends JComponent
{

    /**
     * @param args the command line arguments
     */
    
        // Set the width and the height of frame
    static int frameWidth = 500;
    static int frameHeight = 500;
        // set the default coordinates (x,y) on frame
    double xF = 0;
    double yF = 0;
    
        /* set the start position (xS,yS) of viewport
         * which is the left-bot point of viewport
         */
    double xS = 0;  
    double yS = 0;  
        // These are default values for viewport
    double xVmin = 0;
    double xVmax = 0;
    double yVmin = 0;
    double yVmax = 0;
        // set the point (xV,yV) on viewport
    double xV = 0;
    double yV = 0;
        // set the default values for window
    double xWmin = 0;
    double xWmax = 0;
    double yWmin = 0;
    double yWmax = 0;
    
    
    
    /* Main function creates the frame */
    public static void main(String[] args) 
    {
        JFrame f = new JFrame();
        
        //  Exit application when the window is closed
        f.addWindowListener( new WindowAdapter() {
            @Override
            public void windowClosing( WindowEvent e )
            {  System.exit(0); }
            }
        );
        
        f.setSize( frameWidth, frameHeight );
        f.getContentPane().add( new CS324_HW2() );
        f.setVisible( true );
        
    }
    
    
    
    /* This is our plot function */
    @Override   
    public void paintComponent(Graphics g)
    {
        // separate the frame into quadrants
        g.drawLine(0, frameHeight/2, frameWidth, frameHeight/2);
        g.drawLine(frameWidth/2, 0 , frameWidth/2, frameHeight);
        
        // Write down My name and class at second quadrant
        g.drawString("Tao Zhang", 50, 50);
        g.drawString("CS 324", 50,70);
        g.drawString("Programming Assignment #2", 50, 90);
        g.drawString("Feb 14th, 2014", 50, 110);
        
        // First function at first quadrant, where is on right-top corner
        SetViewport(frameWidth/2,frameHeight/2,frameWidth/2,frameHeight/2);
        SetWindow(0, -10, 10, 20);
            
        MoveTo(0, 4*Math.exp(0)*Math.cos(0));
        double i;
        for( i = .01; i < 10; i+=.01){
            DrawTo(g, i, 4*Math.exp(-.25*i)*Math.cos(4*i));
        }
        
        // Second function at third quadrant, left-bot corner
        SetViewport(0,frameHeight, frameWidth/2,frameHeight/2);
        SetWindow(-10, -10, 20, 20);
        
        MoveTo(-9, 2/(.5-Math.sin(-9/2)));
        for(i = -8.99; i < 9; i+=.01)
        {
            DrawTo(g, i, 2/(.5-Math.sin(i/2)));
        }
        
        /* Third function at forth quadrant, right-bot corner
         * since y = cx√(bx-a) or y = -cx√(bx-a)
         * so we have to draw two curves for each 
         */
        SetViewport(frameWidth/2, frameHeight, frameWidth/2, frameHeight/2);
        SetWindow(0,-50,15,100);
        
        double a = 0.5;
        double c = 1.0;
        double b;

        // b = 0.5, y = cx√(bx-a)
        b = 0.5;
        MoveTo(1,c*10*Math.sqrt(b*1-a));
        for(i = 1.1; i < 15; i+=.1){
            DrawTo(g, i, c*i*Math.sqrt(b*i-a));
        }
        // b = 0.5, y = -cx√(bx-a)
        MoveTo(1,-c*10*Math.sqrt(b*1-a));
        for(i = 1.1; i < 15; i+=.1){
            DrawTo(g, i, -c*i*Math.sqrt(b*i-a));
        }  
        // b = 1.0, y = cx√(bx-a)
        b = 1.0;
        MoveTo(1,c*10*Math.sqrt(b*1-a));
        for(i = 1.1; i < 15; i+=.1){
            DrawTo(g, i, c*i*Math.sqrt(b*i-a));
        }
        // b = 1.0, y = -cx√(bx-a)
        MoveTo(1,-c*1*Math.sqrt(b*1-a));
        for(i = 1.1; i < 15; i+=.1){
            DrawTo(g, i, -c*i*Math.sqrt(b*i-a));
        }  
        // b = 2.0, y = cx√(bx-a)
        b = 2.0;
        MoveTo(1,c*1*Math.sqrt(b*1-a));
        for(i = 1.1; i < 15; i+=.1){
            DrawTo(g, i, c*i*Math.sqrt(b*i-a));
        }
        // b = 2.0, y = -cx√(bx-a)
        MoveTo(1,-c*1*Math.sqrt(b*1-a));
        for(i = 1.1; i < 15; i+=.1){
            DrawTo(g, i, -c*i*Math.sqrt(b*i-a));
        } 
    }
    
    
    
    /* This function sets the size of the Viewport 
     * and the starting position on frame
     * The start position is located at left-bot of viewport
     */
    public void SetViewport(double left, double bottom, double width, double height)
    {
        xVmin = 0;
        xVmax = width;
        yVmin = 0;
        yVmax = height; 
        
        xS = left;
        yS = bottom;
    }
     
    
    
    /* This function just set the size of the window
     * and the start position at left-bot corner
     */
    public void SetWindow(double x, double y, double Wwidth, double Wheight)
    {
        xWmin = x;
        xWmax = x+Wwidth;
        yWmin = y;
        yWmax = y+Wheight;
    }

    
    
    /* This function transfer the point (x,y) from window to viewport */
    public void WindowToViewport(double xW, double yW)
    {
        xV = (xW - xWmin)*(xVmax - xVmin)/(xWmax - xWmin) + xVmin;
        yV = (yW - yWmin)*(yVmax - yVmin)/(yWmax - yWmin) + yVmin;
    }
    
    
    
    /* This function transfers the point from viewport to frame */
    public void ViewportToFrame()
    {
        xF = xS + xV;
        yF = yS - yV;
    }
    
    
    
    /* This function transfer the point (x,y) from window to frame */
    public void MoveTo(double x, double y)
    {
        WindowToViewport(x , y);
        ViewportToFrame();
    }
    
    
    
    /* This store the last point and move to new point, 
     * then draw a line between old point and new point
     */
    public void DrawTo(Graphics g, double x, double y)
    {
        double xF_S = xF;
        double yF_S = yF;
        MoveTo(x,y);
        if(xV >= xVmin && xV <= xVmax && 
                yV <= yVmax && yV >= yVmin){ // in case not exceed the viewport
            g.drawLine((int) (xF_S),(int) (yF_S),(int) (xF), (int) (yF));
        }
    }
}
