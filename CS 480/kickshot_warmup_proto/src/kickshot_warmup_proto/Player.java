package kickshot_warmup_proto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rob Kleffner
 */
public class Player {
    public Controller ItsController;
    public int ItsScore;
    public Deck ItsDeck;
    public List<Card> ItsHand;
    public String ItsName;
    public boolean ItsOffenseFlag;
    
    public Player(String name) {
        ItsScore = 0;
        ItsDeck = new Deck();
        ItsHand = new ArrayList<>();
        ItsName = name;
    }
    
    public void DealHand() {
        for (int i = 0; i < 6; i++) {
            DrawCard();
        }
    }
    
    public void PrintHand() {
        for (int i = 0; i < ItsHand.size(); i++) {
            System.out.println((i + 1) + ": " + ItsHand.get(i));
        }
    }
    
    public void DrawCard() {
        Card c = ItsDeck.Draw();
        c.ItsOwner = this;
        ItsHand.add(c);
    }
}
