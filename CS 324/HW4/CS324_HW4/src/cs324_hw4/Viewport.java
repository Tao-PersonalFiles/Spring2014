/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cs324_hw4;

import java.awt.Graphics;

/**
 *
 * @author 张涛
 */
public class Viewport {
    // set the default coordinates (x,y) on frame
    public double xF = 0;
    public double yF = 0;
        /* set the start position (xS,yS) of viewport
         * which is the left-bot point of viewport
         */
    public double xS = 0;  
    public double yS = 0;  
        // These are default values for viewport
    public double xVmin = 0;
    public double xVmax = 0;
    public double yVmin = 0;
    public double yVmax = 0;
        // set the current point (xV,yV) on viewport
    public double xV = 0;
    public double yV = 0;
        // set the default values for window
    public double xWmin = 0;
    public double xWmax = 0;
    public double yWmin = 0;
    public double yWmax = 0;
    
    public Viewport(){
        
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
    public void SetWindow(double xmin, double ymin, double xmax, double ymax)
    {
        xWmin = xmin;
        xWmax = xmax;
        yWmin = ymin;
        yWmax = ymax;
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
        
        g.drawLine((int) (xF_S),(int) (yF_S),(int) (xF), (int) (yF));
       
        
    }
}
