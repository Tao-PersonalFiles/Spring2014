/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package magicsearch;
import java.util.*;

import java.io.*;
/**
 *
 * @author 张涛
 */
public class MagicSearch {

    /**
     * @param args the command line arguments
     */
    
    int PowerLevel;
    
    String Name;
    String Type;
    String MC;
    String Range;
    
    public static void main(String[] args) {
        MagicSearch ms = new MagicSearch();
        
        ms.getResults();
    }
    
    public void getResults(){
        getPL();
        generateResults();
    }
    
    public void getPL(){
        System.out.println("Please enter the PowerLevel(1~7) that you wish to read: ");
        Scanner in = new Scanner(System.in);
        PowerLevel = in.nextInt();
        if(PowerLevel > 7 || PowerLevel <1){
            getPL();
        }
    }
    
    public void generateResults(){
        int c = PowerLevel;
        System.out.println("Here is the list of spells for PL " + c);
        
        String Heading1 = "Name(and Resistability)";
        String Heading2 = "Type";
        String Heading3 = "Mana Cost";
        String Heading4 = "Range";
        
        System.out.printf("%-15s %15s %15s %15s %n", Heading1, Heading2, Heading3, Heading4);
        PrintPL(c);
    }
    
    public void PrintPL(int c){
        String line;
        StringTokenizer strTok = new StringTokenizer(line,",");
        switch(c){
            case 1: ;
                    break;
                
        }
        System.out.printf("%-15s %15s %15s %15s %n", Name, Type, MC, Range);
        
        
    }
    
    
    
}
