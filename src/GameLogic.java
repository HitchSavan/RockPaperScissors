package src;

import java.security.SecureRandom;
import src.UI.matchResults;

public class GameLogic {

    public static int makeComputerMove(String[] availableMoves) {
        KeyGen.generateKey();
        SecureRandom random = new SecureRandom();
        int computerMove = random.nextInt(availableMoves.length);
        KeyGen.generateHmac256(KeyGen.getHexKey().getBytes(), (availableMoves[computerMove]).getBytes());
        return computerMove;
    }
    
    public static String getRoundResults(int userMove, int computerMove, int numberOfMoves) {
        switch(computeRoundResults(userMove, computerMove, numberOfMoves)) {
            case DRAW:
                return "It's draw!";
            case LOSE:
                return "You lose!";
            case WIN:
                return "You win!";
            default:
                return "I don't know, what's happened";
        }
    }

    public static matchResults computeRoundResults(int userMove, int computerMove, int numberOfMoves) {
        int difference = userMove - computerMove;
        if(difference == 0)
            return matchResults.DRAW;
        if((difference < 0 && difference >= -numberOfMoves/2) || (difference > numberOfMoves/2)) {
            return matchResults.LOSE;
        } else {
            return matchResults.WIN;
        }   
    }
}
