package src;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws Exception {
        checkValidInput(args);
        UI screen = new UI();

        while(true) {
            boolean successfulRound = false;
            int computerMove = GameLogic.makeComputerMove(args);
            System.out.print("HMAC: ");
            System.out.println(KeyGen.getHexHmac());
        
            while(!successfulRound) {
                System.out.print(screen.getMainMenu(args));
                Scanner sc = new Scanner(System.in);
                String userInput = sc.next();
                switch(userInput) {
                    case "?":
                        screen.printRulesTable(args);
                        break;
                    case "0":
                        sc.close();
                        System.exit(0);
                    default:
                        if(isNumeric(userInput))
                            if(Integer.parseInt(userInput) > 0 && Integer.parseInt(userInput) <= args.length) {
                                int userMove = Integer.parseInt(userInput)-1;
                                System.out.print(String.format("Your move: %s\n", args[userMove]));
                                System.out.print(String.format("Computer move: %s\n", args[computerMove]));
                                System.out.println(GameLogic.getRoundResults(userMove, computerMove, args.length));
                                System.out.print("HMAC key: ");
                                System.out.println(KeyGen.getHexKey());
                                System.out.println("=============================");
                                successfulRound = true;
                            }
                }
            }
        }
    }

    public static void checkValidInput(String[] availableMoves) {
        if(availableMoves.length < 3) {
            System.out.println(String.format("Got wrong number of moves. =>3 expected but %d were given.", availableMoves.length));
            System.exit(0);
        }

        if(availableMoves.length % 2 != 1) {
            System.out.println("Got even number of moves. Odd number of moves expected.");
            System.exit(0);
        }
        
        Set<String> bufSet = new HashSet<>();
        for(String t : availableMoves) {
            if(!bufSet.add(t)) {
                System.out.println("Got duplicates of moves. Unique moves expected.");
                System.exit(0);
            }
        }
    }

    public static boolean isNumeric(String str) {
        return str != null && str.matches("[0-9.]+");
    }
}