package kickshot_warmup_proto;

/**
 * @author Rob Kleffner
 */
public class DirectFreeKickCard extends Card {
    @Override
    public boolean CanPlay() {
        return false;
    }

    @Override
    public String ReasonCantPlay() {
        return "Not yet supported!";
    }
    
    @Override
    public String toString() {
        return "Direct Free Kick Card";
    }

    @Override
    public boolean DoCardAction(Game g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
