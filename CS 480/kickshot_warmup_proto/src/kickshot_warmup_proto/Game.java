package kickshot_warmup_proto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * @author Rob Kleffner
 */
public class Game {
    public static Random RandGen = new Random();
    public Player ItsHumanPlayer;
    public Player ItsBotPlayer;
    public int ItsChipPosition;
    
    private BufferedReader _reader;
    private Player _offense;
    private Player _defense;
    private int _direction;
    
    public Game() {
        _reader = new BufferedReader(new InputStreamReader(System.in));
    }
    
    public void PlayGame() {
        boolean playAgain = true;
        while (playAgain) {
            Half(true);
            Half(false);
            playAgain = QueryPlayAgain();
        }
    }
    
    public void Half(boolean first) {
        SetupHalf(first);
        KickOff();
        boolean offenseTurn = false;
        while (ItsHumanPlayer.ItsDeck.ItsCards.size() > 0 &&
               ItsBotPlayer.ItsDeck.ItsCards.size() > 0) {
            offenseTurn = Turn(offenseTurn);
        }
        offenseTurn = Turn(offenseTurn);
        offenseTurn = Turn(offenseTurn);
    }
    
    public Player GetOffensivePlayer() {
        return _offense;
    }
    
    public Player GetDefensePlayer() {
        return _defense;
    }
    
    public void Turnover() {
        Player temp = _defense;
        _defense = _offense;
        _offense = temp;
        
        _offense.ItsOffenseFlag = true;
        _defense.ItsOffenseFlag = false;
        
        // change movement
        _direction = -_direction;
    }
    
    private boolean Turn(boolean offenseTurn) {
        Player current = offenseTurn ? _offense : _defense;
        Move m = current.ItsController.GetInput();
        switch (m.ItsMoveType) {
            case SkipTurn:
                System.out.println(current.ItsName + " skipped a turn.");
                return !offenseTurn;
            case PlayCard:
                boolean result = !offenseTurn;
                if (m.ItsCard.DoCardAction(this))
                    result = offenseTurn;
                current.ItsHand.remove(m.ItsCard);
                current.DrawCard();
                return result;
            case TradeCard:
                return !offenseTurn;
        }
        return !offenseTurn;
    }
    
    private void SetupHalf(boolean first) {
        ItsChipPosition = 0;
        
        if (first) {
            ItsHumanPlayer = new Player("You");
            Controller human = new HumanController(ItsHumanPlayer);
            ItsHumanPlayer.ItsController = human;

            ItsBotPlayer = new Player("AI");
            Controller bot = new EasyBotController(ItsBotPlayer);
            ItsBotPlayer.ItsController = bot;
        }
        
        ItsHumanPlayer.ItsDeck.Regenerate(this);
        ItsHumanPlayer.DealHand();
        ItsBotPlayer.ItsDeck.Regenerate(this);
        ItsBotPlayer.DealHand();
        
        ItsHumanPlayer.PrintHand();
        ItsBotPlayer.PrintHand();
        DecideKickOff();
    }
    
    private void DecideKickOff() {
        int die1 = RollDice();
        int die2 = RollDice();
        // die 1 is the human player's roll
        if (die1 > die2) {
            _offense = ItsHumanPlayer;
            _defense = ItsBotPlayer;
            _direction = 1;
            System.out.println("You are starting on offense.");
        } else {
            _offense = ItsBotPlayer;
            _defense = ItsHumanPlayer;
            _direction = -1;
            System.out.println("You are starting on defense.");
        }
        _offense.ItsOffenseFlag = true;
        _defense.ItsOffenseFlag = false;
    }
    
    private void KickOff() {
        int die1 = RollDice();
        int die2 = RollDice();
        
        int move = (die1 == die2) ? die1 + 1 : (die1 > die2) ? die1 : die2;
        ItsChipPosition += move * _direction;
        System.out.println(_offense.ItsName + " kicked off for " + move + " spaces.");
    }
    
    private boolean QueryPlayAgain() {
        System.out.println("Would you like to play again? [y/n]");
        try {
            String input = _reader.readLine();
            return "y".equals(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public int RollDice() {
        return RandGen.nextInt(6);
    }
    
    public int GetMovementDirection() {
        return _direction;
    }
}
