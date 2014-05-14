package kickshot_warmup_proto;

/**
 * @author Rob Kleffner
 */
public class PassCard extends Card {
    @Override
    public boolean CanPlay() {
        return ItsOwner.ItsOffenseFlag;
    }

    @Override
    public String ReasonCantPlay() {
        return "Can't play a pass card while on defense!";
    }
    
    @Override
    public String toString() {
        return "Pass Card";
    }

    @Override
    public boolean DoCardAction(Game g) {
        int die1 = g.RollDice();
        int die2 = g.RollDice();
        
        // if either dice is a one, it's a turnover
        if (die1 == 1 || die2 == 1) {
            System.out.println("Woops, " + ItsOwner.ItsName + " turned over the ball!");
            g.Turnover();
            return true;
        }
        
        int advance = 0;
        if (die1 == die2) {
            advance = (die1 + 1) * g.GetMovementDirection();
        } else {
            advance = g.GetMovementDirection() * ((die1 > die2) ? die1 : die2);
        }
        System.out.println(ItsOwner.ItsName + " passed the ball and gained " + advance + " spaces!");
        g.ItsChipPosition += advance;
        return false;
    }
}
