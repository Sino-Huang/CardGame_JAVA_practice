package comp1110.ass2.gui;

import comp1110.ass2.Player;
import javafx.application.Application;
import javafx.stage.Stage;

public class Game extends Application {
    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;

    // FIXME Task 9: Implement a basic playable Warring States game in JavaFX
    public void inputPlayer(){ // input numbers of players

    }

    // FIXME Task 11: Allow players of your Warring States game to play against your simple agent
    public char simpleMove (){ // may directly use task 10's method
        return '\0';
    }

    // FIXME Task 12: Integrate a more advanced opponent into your game
    public char alphaBetaPruning (int node, int depth, int alpha, int beta, Player maximizingPlayer){ //use alpha beta pruning to get intelligent move
        return '\0';
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}

