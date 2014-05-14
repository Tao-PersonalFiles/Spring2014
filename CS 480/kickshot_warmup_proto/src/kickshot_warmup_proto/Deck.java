package kickshot_warmup_proto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rob Kleffner
 */
public class Deck {
    public List<Card> ItsCards;
    
    public Deck() {
        ItsCards = new ArrayList<>();
    }
    
    public Card Draw() {
        return ItsCards.remove(0);
    }
    
    /**
     * Generates a shuffled deck of cards. For KickShot Warm Up, each deck will
     * start with 27 cards: 10 pass cards, 3 goal shot left (and right), 5 intercept,
     * and 3 goal shot left (and right).
     */
    public void Regenerate(Game g) {
        ItsCards.clear();
        
        // we create a list with all the cards in a boring order
        List<Card> temp = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            temp.add(new PassCard());
        }
        for (int i = 0; i < 5; i++) {
            temp.add(new InterceptCard());
        }
        for (int i = 0; i < 3; i++) {
            temp.add(new GoalShotCard(ShotDirection.LEFT));
        }
        for (int i = 0; i < 3; i++) {
            temp.add(new GoalShotCard(ShotDirection.RIGHT));
        }
        for (int i = 0; i < 3; i++) {
            temp.add(new GoalBlockCard(ShotDirection.LEFT));
        }
        for (int i = 0; i < 3; i++) {
            temp.add(new GoalBlockCard(ShotDirection.RIGHT));
        }
        
        // now we shuffle the list by plucking a card from a random
        // position in the list and appending to the new list
        while (!temp.isEmpty()) {
            int index = Game.RandGen.nextInt(temp.size());
            ItsCards.add(temp.remove(index));
        }
    }
    
    @Override
    public String toString() {
        String l = "Deck: <";
        for (Card c : ItsCards) {
            l += c + "::";
        }
        l += ">";
        return l;
    }
}
