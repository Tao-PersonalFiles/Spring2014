package kickshot_warmup_proto;

/**
 * @author Rob Kleffner
 */
public abstract class Card {
    public Player ItsOwner;
    
    public abstract boolean CanPlay();
    public abstract String ReasonCantPlay();
    public abstract boolean DoCardAction(Game g);
}
