/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package character;

import implement_spells.Implement_Spells;

/**
 *
 * @author 张涛
 */
public class Character {
    public String  Name;
    // a character's magic power level may differ from his average MagicPL
    public int     MagicPL;
    public int     MagicPotential;
    public double  CurrentManna;
    
    Character(){}
    
    public Character(String a, int b, int c) {
        Name        = a;
        MagicPL     = b;
        MagicPotential  = c;
        CurrentManna = (double)MagicPotential;
    }
    
    public void CastSpell(Character c){
        Implement_Spells is = new Implement_Spells(c);
        is.getSpellBook();
    }
    
    public void CostManna(double mc){
        CurrentManna -= mc;
    }
}
