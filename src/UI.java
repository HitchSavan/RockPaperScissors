package src;

import com.bethecoder.ascii_table.ASCIITable;

public class UI {
    public enum matchResults {
        DRAW,
        WIN,
        LOSE
    }

    public String getMainMenu(String[] availableMoves) {
        StringBuilder mainMenuText = new StringBuilder();
        mainMenuText.append("Available moves:\n");
        for(int i = 0; i < availableMoves.length; i++) {
            mainMenuText.append(String.format("%d - %s\n", i+1, availableMoves[i]));
        }
        mainMenuText.append("0 - exit\n? - help\nEnter your move: ");
        
        return mainMenuText.toString();
    }

    public void printRulesTable(String[] availableMoves) {
        String [] header = new String[availableMoves.length+1];
        header[0] = "PC\\User";
        for(int i=0; i < availableMoves.length; i++) {
            header[i+1] = availableMoves[i];
        }
        
		String[][] rows = new String[availableMoves.length][availableMoves.length+1];
        for(int i=0; i < availableMoves.length; i++) {
            rows[i][0] = availableMoves[i];
            for(int j=0; j < availableMoves.length; j++) {
                rows[i][j+1] = GameLogic.computeRoundResults(j, i, availableMoves.length).toString();
            }
        }
        ASCIITable.getInstance().printTable(header, rows, ASCIITable.ALIGN_LEFT);
    }
}
