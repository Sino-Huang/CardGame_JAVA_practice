package comp1110.ass2;

import java.util.ArrayList;

public class GameState {
    public String boardPlacement;
    public ArrayList<Player> players;
    public int Playerturn;
    public int numOfPlayer;
    public boolean whetherAI;
    public boolean whetherSmartAI;

    public GameState(String boardPlacement, ArrayList<Player> players, int playerturn, int numOfPlayer, boolean whetherAI, boolean whetherSmartAI) {
        this.boardPlacement = boardPlacement;
        this.players = players;
        Playerturn = playerturn;
        this.numOfPlayer = numOfPlayer;
        this.whetherAI = whetherAI;
        this.whetherSmartAI = whetherSmartAI;
    }

    public GameState(String boardPlacement, ArrayList<Player> players, int playerturn, int numOfPlayer) {
        this.boardPlacement = boardPlacement;
        this.players = players;
        Playerturn = playerturn;
        this.numOfPlayer = numOfPlayer;
        this.whetherAI = true;
        this.whetherSmartAI = true;
    }
}
