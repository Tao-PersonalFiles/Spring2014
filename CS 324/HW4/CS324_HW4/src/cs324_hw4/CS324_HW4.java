/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cs324_hw4;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;


/**
 *
 * @author 张涛
 */
public class CS324_HW4 {

    Matrix m = new Matrix();
    
    // Set the width and the height of frame
    static int frameWidth = 500;
    static int frameHeight = 500;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        CS324_HW4 t = new CS324_HW4();
        //t.testMatrix();
        //t.testCamera();
        //t.testModeling();
        t.setFrame();
    }
    
    public void testMatrix(){
        double [][] m1 = m.makeMatrix(4,4);
        double [][] m2 = m.makeMatrix(4,4);
        double [][] m3 = m.makeMatrix(4, 4);
        double [][] v = m.makeMatrix(1, 4);
        double [][] tv = m.makeMatrix(1, 4);
        
        m1[1][0] = 2;
        m2[0][1] = 3;
        m2[2][1] = 4;
        v[0][0] = 1;
        v[0][1] = 2;
        v[0][2] = 3;
        v[0][3] = 4;
        
        m.MultiplyMatrix( m1, m2, m3);
        m.printMatrix(m1);
        System.out.println();
        m.printMatrix(m2);
        System.out.println();
        m.printMatrix(m3);
        
        System.out.println();
        m.printMatrix(v);
        
        System.out.println(v.length);
        
        m.MultiplyMatrix(v, m3, tv);
        m.printMatrix(tv);
    }
    
    public void testCamera(){
        CameraTransform ct = new CameraTransform(1,0,1,30,45,0,20);
        double [][] t = ct.CAMERA;
        
        m.printMatrix(t);
    }
    
    public void testModeling(){
        
    }
    
    public void setFrame(){
        JFrame f = new JFrame();
        
        //  Exit application when the window is closed
        f.addWindowListener( new WindowAdapter() {
            @Override
            public void windowClosing( WindowEvent e )
            {  System.exit(0); }
            }
        );
        
        f.setSize( frameWidth, frameHeight );
        f.getContentPane().add( new Frame(frameWidth,frameHeight) );
        f.setVisible( true );
    }
    
}
