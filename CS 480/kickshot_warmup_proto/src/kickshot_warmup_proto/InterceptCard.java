package kickshot_warmup_proto;

/**
 * @author Rob Kleffner
 */
public class InterceptCard extends Card {
    @Override
    public boolean CanPlay() {
        return !ItsOwner.ItsOffenseFlag;
    }

    @Override
    public String ReasonCantPlay() {
        return "Can't play an intercept card while on offense!";
    }
    
    @Override
    public String toString() {
        return "Intercept Card";
    }

    @Override
    public boolean DoCardAction(Game g) {
        int die1 = g.RollDice();
        int die2 = g.RollDice();
        
        // turn over successful if neither dice rolled 1
        if (die1 == 1 || die2 == 1) {
            System.out.println(ItsOwner.ItsName + " attempted to intercept the ball, but failed!");
            return false;
        }
        
        g.Turnover();
        System.out.println(ItsOwner.ItsName + " intercepted the ball!");
        return true;
    }
}
