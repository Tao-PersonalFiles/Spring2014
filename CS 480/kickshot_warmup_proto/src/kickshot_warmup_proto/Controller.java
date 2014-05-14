package kickshot_warmup_proto;

/**
 * @author Rob Kleffner
 */
public abstract class Controller {
    protected Player MyOwner;
    
    public Controller(Player p) {
        MyOwner = p;
    }
    
    public abstract Move GetInput();
}
