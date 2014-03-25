/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package implement_spells;

import implement_spells.Spell_Details.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author 张涛
 */
public class Spell {
    String  Name;
    int     Level;
    double  ManaCost;
    
    public JButton spellbutton;
    private JFrame spellFrame;
    private JPanel controlPanel;
    
    Spell(String n, int lv, double mc) {
        Name  = n;
        Level = lv;
        ManaCost  = mc;   
        spellbutton = new JButton(n);
        
        spellbutton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                showSpellDetails();
                showButtonDemo();
            }
        });
    }
    
    public void showSpellDetails(){
        spellFrame = new JFrame(Name);
        spellFrame.setSize(300,500);
        
        spellFrame.addWindowListener( new WindowAdapter() {
            @Override
            public void windowClosing( WindowEvent e )
            {  //System.exit(0); 

            }
        });
        
        spellFrame.getContentPane().add( new Spell_Details(Name));
        spellFrame.setVisible( true );
    }
    
    public void showButtonDemo(){
        JButton cast_button = new JButton("Cast");
        JButton cancel_button = new JButton("Cancel");
        
        cast_button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                //JFrame spell_cast_window = new JFrame(Name);
                
            }
        });
        
        cancel_button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                spellFrame.dispose();
            }
        });
        
        JPanel selection = new JPanel();// panel for cast and cancel buttons
        selection.setLayout(new FlowLayout());
        selection.add(cast_button);
        selection.add(cancel_button);
        
        spellFrame.setLayout(new BorderLayout());
        spellFrame.add(selection, BorderLayout.SOUTH);
        spellFrame.setVisible(true);
    }
    
    /*
    public void prepareSelectionGUI(){
        selectF = new JFrame("Selection");
        selectF.setSize(100,150);
        //selectF.setLayout(new GridLayout(3,1));
        selectF.addWindowListener(new WindowAdapter() {
           @Override
           public void windowClosing(WindowEvent windowEvent){
                //System.exit(0);
           }        
        }); 
        
        controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(3,1));
        
        selectF.add(controlPanel);
        selectF.setVisible(true);
    }
    
    public void showButtonDemo(){
        JButton detailButton = new JButton("Show Details");
        JButton castButton = new JButton("Cast spell");
        JButton cancelButton = new JButton("Cancel");
        
        detailButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                //=========================
                JFrame spell_detail_window = new JFrame("Spell Details");
                spell_detail_window.setLayout(new BorderLayout());
                int sframe_width  = 300;
                int sframe_height = 500;
        
                spell_detail_window.addWindowListener( new WindowAdapter() {
                    @Override
                    public void windowClosing( WindowEvent e )
                    {  //System.exit(0); 
                        
                    }
                });
                
                spell_detail_window.setSize(sframe_width, sframe_height);
                spell_detail_window.getContentPane().add( new Spell_Details(Name) );
                
                JPanel selection = new JPanel();
                selection.setLayout(new FlowLayout());
                JButton cast_button = new JButton("Cast");
                selection.add(cast_button);
                selection.add(new JButton("Cancel"));
                spell_detail_window.add(selection, BorderLayout.SOUTH);
                
                spell_detail_window.setVisible( true );
                
            }
        });
        
        castButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                //JFrame spell_cast_window = new JFrame(Name);
                
            }
        });
        
        cancelButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                selectF.dispose();
            }
        });
        
        controlPanel.add(detailButton);
        controlPanel.add(castButton);
        controlPanel.add(cancelButton);
        
        selectF.setVisible(true);
    }
    */
}
