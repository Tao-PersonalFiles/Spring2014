package kickshot_warmup_proto;

/**
 * @author Rob Kleffner
 */
public class Move {
    public enum MoveType {
        Error,
        SkipTurn,
        PlayCard,
        TradeCard
    }
    
    public MoveType ItsMoveType;
    public Card ItsCard;
    
    public Move(MoveType m, Card c) {
        ItsMoveType = m;
        ItsCard = c;
    }
}
