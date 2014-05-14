package kickshot_warmup_proto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Rob Kleffner
 */
public class HumanController extends Controller {
    private BufferedReader _reader;
    
    public HumanController(Player p) {
        super(p);
        _reader = new BufferedReader(new InputStreamReader(System.in));
    }
    
    @Override
    public Move GetInput() {
        String input;
        try {
            PrintOptions();
            while ((input = _reader.readLine()) != null) {
                switch (input) {
                    case "1":
                        // TODO: play card here
                        Move move = PlayCard();
                        if (move != null)
                            return move;
                        else
                            PrintOptions();
                        break;
                    case "2":
                        // TODO: trade here
                        //return new Move(Move.MoveType.TradeCard, null);
                        System.out.println("Sorry! This doesn't work just yet.");
                        PrintOptions();
                        break;
                    case "3":
                        return new Move(Move.MoveType.SkipTurn, null);
                    case "4":
                        System.exit(0);
                    default:
                        System.out.println("That's not an option! Try again.");
                        PrintOptions();
                        break;
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return new Move(Move.MoveType.Error, null);
    }
    
    private void PrintOptions() {
        System.out.println("What would you like to do?");
        System.out.println("1. Play a card");
        System.out.println("2. Trade a card");
        System.out.println("3. Skip turn");
        System.out.println("4. Quit");
    }
    
    private Move PlayCard() {
        System.out.println("Which card would you like to play?");
        MyOwner.PrintHand();
        System.out.println((MyOwner.ItsHand.size() + 1) + ": Cancel");
        String input;
        try {
            while ((input = _reader.readLine()) != null) {
                int inputNum = Integer.parseInt(input);
                if (inputNum <= MyOwner.ItsHand.size() && inputNum > 0) {
                    Card c = MyOwner.ItsHand.get(inputNum - 1);
                    if (c.CanPlay()) {
                        return new Move(Move.MoveType.PlayCard, c);
                    } else {
                        System.out.println(c.ReasonCantPlay());
                        MyOwner.PrintHand();
                        System.out.println((MyOwner.ItsHand.size() + 1) + ": Cancel");
                    }
                } else if (inputNum == MyOwner.ItsHand.size() + 1) {
                    return null;
                } else {
                    System.out.println("That's not a valid option!");
                    MyOwner.PrintHand();
                    System.out.println((MyOwner.ItsHand.size() + 1) + ": Cancel");
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Move(Move.MoveType.PlayCard, null);
    }
}
