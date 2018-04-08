package comp1110.ass2;

import java.util.ArrayList;

public class GameState {
    public String boardPlacement;
    public ArrayList<Player> players;
    public int Playerturn;
    public int numOfPlayer;

    public GameState(String boardPlacement, ArrayList<Player> players, int playerturn, int numOfPlayer) {
        this.boardPlacement = boardPlacement;
        this.players = players;
        Playerturn = playerturn;
        this.numOfPlayer = numOfPlayer;
    }
}
