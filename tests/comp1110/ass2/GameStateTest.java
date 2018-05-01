package comp1110.ass2;

import comp1110.ass2.gui.Cards;
import comp1110.ass2.gui.Flags;
import comp1110.ass2.gui.Game;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class GameStateTest {
    @Test
    public void deepCopyTest() {
        // init a GameState
        GameState gameSample;
        gameSample = new GameState(Game.createBoard(), GameState.initPlayers(4, false, false), 1, 4, false, false); // setup the initial gameState

        //Use generateMove to get a suitable next move for the simulation
        char nextMove = WarringStatesGame.generateMove(gameSample.boardPlacement);

        //update gamestate and store it into a new GameState object
        GameState newGameState = new GameState(gameSample, nextMove);
        //ensure that the updated GameState do not share the 'Collections' using its hashCode() value
        assertFalse("The hashcode for the old gameState's players arraylist should not be equal to the new one's", gameSample.players == newGameState.players);
        assertFalse("The hashcode for the old gameState should not be equal to the new one", gameSample == newGameState);
    }

    @Test
    public void updatePlayerTurnTest() {
        //init the gameState
        GameState gameSample;
        gameSample = new GameState(Game.createBoard(), GameState.initPlayers(4, false, false), 1, 4, false, false); // setup the initial gameState

        //Use generateMove to get a suitable next move for the simulation
        char nextMove = WarringStatesGame.generateMove(gameSample.boardPlacement);
        //update gamestate and store it into a new GameState object
        GameState newGameState = new GameState(gameSample, nextMove);
        // check the gameState's playerturn is updated correctly
        assertTrue("The new GameState's player'Turn should be updated", newGameState.playerturn == gameSample.playerturn % gameSample.numOfPlayer + 1);
        // repeating update and then check
        char nextMove2 = WarringStatesGame.generateMove(newGameState.boardPlacement);
        GameState newGS2 = new GameState(newGameState, nextMove2);
        assertTrue("The new GameState's player'Turn should be updated", newGS2.playerturn == newGameState.playerturn % gameSample.numOfPlayer + 1);
    }

    @Test
    public void updatePlayersInformationTest() {
        GameState gameSample;
        // using a recorded sampleBoard
        String sampleBoard = "b5Wa7Yf2Zd1Ue1Fb0Xb33d28c09c4Vc3Qe3Ha2Jg0Ad35b64d0Lb4Nb10g16b27c1Ta1Dc5Ed4Of1Ce2Ic2Ka0Ba32a6Pe0Rf0Ma4Ga5Sz91";
        gameSample = new GameState(sampleBoard, GameState.initPlayers(4, false, false), 1, 4, false, false); // setup the initial gameState
        GameState newGameState = new GameState(gameSample, 'D');
        // check whether 'D' is in the All legal move collection
        assertTrue(WarringStatesGame.generateAllLegalMove("b5Wa7Yf2Zd1Ue1Fb0Xb33d28c09c4Vc3Qe3Ha2Jg0Ad35b64d0Lb4Nb10g16b27c1Ta1Dc5Ed4Of1Ce2Ic2Ka0Ba32a6Pe0Rf0Ma4Ga5Sz91").contains('D'));

        //udpate the player list
        Game.updatePlayers(newGameState, 'D');

        assertTrue("Player 1 should take 3 Qin card for the move", newGameState.players.get(0).cards.size() == 3);
        assertTrue("Player 1 should have Qin flag", newGameState.players.get(0).flags.contains(Flags.A));
    }
}
