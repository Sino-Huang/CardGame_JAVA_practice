package comp1110.ass2;

import java.util.ArrayList;

public class GameState {
    public String boardPlacement;
    public ArrayList<Player> players;
    public int playerturn;
    public int numOfPlayer;
    public boolean whetherAI;
    public boolean whetherSmartAI;
    public String previousPlacement = "";

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
    }

    public GameState(String boardPlacement, ArrayList<Player> players, int playerturn, int numOfPlayer, String previous) { // this method is for the updating board status
        this.boardPlacement = boardPlacement;
        this.players = players;
        this.playerturn = playerturn;
        this.numOfPlayer = numOfPlayer;
        if (players.get(players.size() - 1).name.equals("AI")) {
            this.whetherAI = true;
            this.whetherSmartAI = false;
        } else if (players.get(players.size() - 1).name.equals("AISmart")) {
            this.whetherAI = true;
            this.whetherSmartAI = true;
        } else {
            this.whetherAI = false;
            this.whetherSmartAI = false;
        }
        this.previousPlacement = previous;
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
