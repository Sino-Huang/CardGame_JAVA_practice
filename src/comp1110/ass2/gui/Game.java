package comp1110.ass2.gui;

import comp1110.ass2.Player;
import javafx.application.Application;
import javafx.stage.Stage;

public class Game extends Application {
    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;

    // FIXME Task 9: Implement a basic playable Warring States game in JavaFX
    public void inputPlayer(){ // input numbers of players
        // start a game
        // input numbers of player
        // whether need a robot
        // robot level
        // click event
        // check whether valid move
        // update the board and flags
        // next player's move
    }

    // FIXME Task 11: Allow players of your Warring States game to play against your simple agent
    public char simpleMove (){ // may directly use task 10's method
        return '\0';
    }
        // simply return a move

    // FIXME Task 12: Integrate a more advanced opponent into your game

    // get a value system


    // use ab pruning to find highest value move
    public char alphaBetaPruning (int node, int depth, int alpha, int beta, Player maximizingPlayer){ //use alpha beta pruning to get intelligent move
        return '\0';
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}

