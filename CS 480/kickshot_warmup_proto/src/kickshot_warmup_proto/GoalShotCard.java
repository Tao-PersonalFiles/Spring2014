package kickshot_warmup_proto;

/**
 * @author Rob Kleffner
 */
public class GoalShotCard extends Card {
    public ShotDirection ItsDirection;
    
    public GoalShotCard(ShotDirection d) {
        ItsDirection = d;
    }
    
    @Override
    public boolean CanPlay() {
        // TODO: where can we play this?
        return false;
    }

    @Override
    public String ReasonCantPlay() {
        return "Not yet supported!";
    }
    
    @Override
    public String toString() {
        return "Goal Shot " + ((ItsDirection == ShotDirection.LEFT) ? "Left" : "Right") + " Card";
    }

    @Override
    public boolean DoCardAction(Game g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
