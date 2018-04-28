package comp1110.ass2;

import comp1110.ass2.gui.Game;

import java.util.ArrayList;

public class GameState {
    public String boardPlacement;
    public ArrayList<Player> players;
    public int playerturn;
    public int numOfPlayer;
    public boolean whetherAI;
    public boolean whetherSmartAI;
    public String previousPlacement = "";
    public String moveHistory = "";
    public String originalBoard = "";

    @Override
    public String toString() {
        if (whetherAI == true) {
            return "GameState\n" +
                    "boardPlacement =\n'" + boardPlacement + '\'' +
                    ", \n\n" + playerString() +
                    " \nWho's turn = No." + playerturn +
                    ", \nnumOfPlayer = " + numOfPlayer +
                    ", \nwhetherAI = " + whetherAI +
                    ", \nwhetherSmartAI=" + whetherSmartAI +
                    ", \npreviousPlacement='" + previousPlacement + '\'';
        } else {
            return "GameState\n" +
                    "boardPlacement =\n'" + boardPlacement + '\'' +
                    ", \n\n" + playerString() +
                    " \nWho's turn = No." + playerturn +
                    ", \nnumOfPlayer = " + numOfPlayer +
                    ", \nwhetherAI = " + whetherAI +
                    ", \npreviousPlacement='" + previousPlacement + '\'';
        }

    }

    public String playerString() {
        String output = "";
        for (int i = 0; i < players.size(); i++) {
            output += players.get(i).toString() + "\n";
        }
        return output;
    }


    public GameState(String boardPlacement, ArrayList<Player> players, int playerturn, int numOfPlayer, boolean whetherAI, boolean whetherSmartAI) {
        this.boardPlacement = boardPlacement;
        this.players = players;
        this.playerturn = playerturn;
        this.numOfPlayer = numOfPlayer;
        this.whetherAI = whetherAI;
        this.whetherSmartAI = whetherSmartAI;
        this.originalBoard = boardPlacement;
    }

    public GameState(GameState gameState) {
        this.boardPlacement = gameState.boardPlacement;
        this.players = new ArrayList<Player>(gameState.players);
        this.playerturn = gameState.playerturn;
        this.numOfPlayer = gameState.numOfPlayer;
        whetherAI = gameState.whetherAI;
        whetherSmartAI = gameState.whetherSmartAI;
        previousPlacement = gameState.boardPlacement;
        moveHistory = gameState.moveHistory;
        originalBoard = gameState.originalBoard;
    }

    public GameState(GameState oldone, char move) { // this method is for the updating board status for AB pruning
        String moveString = "" + move;
        this.boardPlacement = WarringStatesGame.updateBoard(oldone.boardPlacement,moveString);
        this.players = Game.updatePlayers(oldone, move);
        this.playerturn = oldone.playerturn % oldone.numOfPlayer + 1;
        this.numOfPlayer = oldone.numOfPlayer;
        whetherAI = oldone.whetherAI;
        whetherSmartAI = oldone.whetherSmartAI;
        previousPlacement = oldone.boardPlacement;
        moveHistory = oldone.moveHistory + move;
        originalBoard = oldone.originalBoard;
    }

    public static ArrayList<Player> initPlayers(int numOfPlayer, boolean whetherAI, boolean whetherSmartAI) {
        ArrayList<Player> output = new ArrayList<>();
        for (int i = 1; i <= numOfPlayer; i++) {
            if (i == numOfPlayer && whetherAI == true && whetherSmartAI == false) {
                output.add(new Player("AI", i));
                break;
            } else if (i == numOfPlayer && whetherAI == true && whetherSmartAI == true) {
                output.add(new Player("AISmart", i));
                break;
            }
            output.add(new Player("Player_" + i, i));
        }
        return output;
    }
}
