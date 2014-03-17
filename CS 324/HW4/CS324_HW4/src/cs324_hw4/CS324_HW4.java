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
        
        t.setFrame();
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
        f.getContentPane().add( new Drawing(frameWidth,frameHeight) );
        f.setVisible( true );
    }
    
}
