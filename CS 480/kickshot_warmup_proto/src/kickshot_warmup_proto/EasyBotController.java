package kickshot_warmup_proto;

/**
 * @author Rob Kleffner
 */
public class EasyBotController extends Controller {
    public EasyBotController(Player p) {
        super(p);
    }
    
    @Override
    public Move GetInput() {
        // just play the first playable card we come across!
        for (Card c : MyOwner.ItsHand) {
            if (c.CanPlay()) {
                return new Move(Move.MoveType.PlayCard, c);
            }
        }
        
        // TODO: if no playable cards, trade in
        return new Move(Move.MoveType.SkipTurn, null);
    }
}
