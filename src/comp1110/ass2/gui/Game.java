package comp1110.ass2.gui;

import comp1110.ass2.GameState;
import comp1110.ass2.Player;
import comp1110.ass2.WarringStatesGame;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Game extends Application {
    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;
    private Stage primaryStage;
    private HBox topControl = new HBox();
    private VBox scorePane= new VBox();
    private GridPane mainBody = new GridPane();
    private GameState gameState = null;

    static String createBoard() {
        List<String> positionList = new ArrayList<>();
        for (int i = 'A'; i <= 'Z'; i++) {
            positionList.add(String.valueOf((char) i));
        }
        for (int i = 0; i < 10; i++) {
            positionList.add(String.valueOf(i));
        }

        Collections.shuffle(positionList);
        int count = 0;
        String output = "";
        for (String po : positionList) {
            output += Cards.values()[count].name().toLowerCase() + po;
            count += 1;
        }
        return output;
    }

    // FIXME Task 9: Implement a basic playable Warring States game in JavaFX
        // input numbers of players
        // start a game
        // input numbers of player
        // whether need a robot
        // robot level
        // click event
        // check whether valid move
        // update the board and flags
        // next player's move

    public void inputPlayer() { // preparation stage to store necessary information
        HBox numPlayer = new HBox(); // number of player box
        Label numPlayerLabel = new Label("Num of Player: ");
        ChoiceBox numPlayerText = new ChoiceBox(FXCollections.observableArrayList("2", "3", "4"));
        numPlayerText.getSelectionModel().selectFirst(); // set default value
        numPlayer.getChildren().addAll(numPlayerLabel, numPlayerText);
        numPlayer.setAlignment(Pos.BASELINE_CENTER);

        HBox needAI = new HBox(); // whether need AI box
        Label needAILabel = new Label("Do you need one AI?");
        CheckBox needAICheck = new CheckBox();
        needAI.getChildren().addAll(needAILabel, needAICheck);
        needAI.setAlignment(Pos.BASELINE_CENTER);

        HBox AILevel = new HBox(); // AI level box
        Label AILevelLabel = new Label("AI Level: ");
        ChoiceBox choiceBox = new ChoiceBox(FXCollections.observableArrayList("Easy", "Hard"));
        choiceBox.getSelectionModel().selectFirst(); // set default value for select box so that is will not have null exception
        AILevel.getChildren().addAll(AILevelLabel, choiceBox);
        AILevel.setAlignment(Pos.BASELINE_CENTER);

        Button nextButton = new Button("Next");
        nextButton.setOnAction((e)->{
            // clear the previous element of the layout
            scorePane.getChildren().clear();
            topControl.getChildren().clear();
            mainBody.getChildren().clear();


            int numOfPlayer = Integer.valueOf((String) numPlayerText.getValue());
            boolean whetherneedAI = needAICheck.isSelected();
            boolean whetherSmartAI = true;
            if (choiceBox.getValue().equals("Easy")) {
                whetherSmartAI = false;
            }
            gameState = new GameState(createBoard(), GameState.initPlayers(numOfPlayer, whetherneedAI, whetherSmartAI), 1, numOfPlayer, whetherneedAI, whetherSmartAI); // setup the initial gameState
            gameBody();
        });

        VBox root = new VBox(); // combine all box and button together
        root.getChildren().addAll(numPlayer, needAI, AILevel, nextButton);
        root.setSpacing(25);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);

        primaryStage.setScene(scene);
    }

    public void statusBox() { // status box for checking the status
        Stage stage = new Stage();
        Label label = new Label(gameState.toString());
        label.setWrapText(true);
        Button button = new Button("OK");
        button.setOnAction((e) -> {
            stage.close();
        });
        VBox root = new VBox(label,button);
        root.setAlignment(Pos.CENTER);
        root.setMargin(label, new Insets(15, 15, 15, 15));
        Scene scene = new Scene(root, 500, 500);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void gameBody() {
        BorderPane root = new BorderPane(mainBody, topControl, scorePane, null, null);
        Button restart = new Button("Restart"); // setup top control panel
        restart.setOnAction((e)->{
            inputPlayer();
        });

        Button previousMov = new Button("<- Previous");
        previousMov.setOnMousePressed((e)->{
            makePlacement(gameState.previousPlacement);
        });
        previousMov.setOnMouseReleased((e) -> {
            makePlacement(gameState.boardPlacement);
        });

        Button statusCheck = new Button("Status");
        statusCheck.setOnAction((e)->{
            statusBox();
        });

        topControl.getChildren().addAll(restart, previousMov, statusCheck);

        makePlacement(gameState.boardPlacement); // setup body panel

        for (int i = 0; i < gameState.numOfPlayer; i++) { // setup score panel
            VBox playerBox = new VBox();
            Label label = new Label(gameState.players.get(i).name);
            label.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 20));
            VBox currentCard = new VBox();
            VBox currentFlag = new VBox();
            Label currentCardLabel = new Label("Supporters");
            Label currentFlagLabel = new Label("Alliance country");
            ImageView currentCardImage = new ImageView(new Image(getClass().getResourceAsStream("assets/A7.png")));
            ImageView currentFlagImage = new ImageView(new Image(getClass().getResourceAsStream("assets/A7.png")));
            currentCardImage.fitHeightProperty().bind(primaryStage.heightProperty().divide(8));
            currentCardImage.setPreserveRatio(true);
            currentFlagImage.fitHeightProperty().bind(primaryStage.heightProperty().divide(8));
            currentFlagImage.setPreserveRatio(true);
            currentCard.getChildren().addAll(currentCardLabel, currentCardImage);
            currentFlag.getChildren().addAll(currentFlagLabel, currentFlagImage);
            HBox imageBox = new HBox(currentCard, currentFlag);
            HBox.setMargin(currentCard, new Insets(0, 20, 0, 0));
            playerBox.getChildren().addAll(label, imageBox);
            scorePane.getChildren().add(playerBox);
            scorePane.setMargin(playerBox, new Insets(10, 0, 10, 0));
        }


        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);
        primaryStage.setScene(scene);
    }

    void makePlacement(String placement) {
        if (!placement.equals("")) {
            mainBody.getChildren().clear();
            mainBody.setPadding(new Insets(20, 10, 20, 10)); // set padding for the pane

            //check whether the placement is a valid placement

            String emptyindex = getEmptySpace(placement);
            for (int i = 0; i < emptyindex.length(); i++) {  // fill up the empty space for the pane
                ImageView image = new ImageView(new Image(getClass().getResourceAsStream("assets/empty.png")));
                String index = getIndex(emptyindex.charAt(i));
                GridPane.setConstraints(image, index.charAt(0), index.charAt(1));
                GridPane.setMargin(image, new Insets(10, 0, 0, 10)); // set margins for each image
                image.fitHeightProperty().bind(primaryStage.heightProperty().divide(7.5)); // resize the image to fit the height of the stage
                image.setPreserveRatio(true); // preserve ratio
                mainBody.getChildren().add(image);
            }

            for (int i = 0; i < placement.length(); i += 3) {    // setup the imageView of the character
                String kingdom = String.valueOf(placement.substring(i,i+2));
                String kingdomname = kingdom.toUpperCase();
                ImageView image = null;
                for (Cards characters : Cards.values()) {
                    if (kingdomname.equals(characters.name())) {
                        image = characters.imageView;
                    }
                }
                String index = getIndex(placement.charAt(i + 2));
                GridPane.setConstraints(image, index.charAt(0), index.charAt(1));  // set the index of the character
                GridPane.setMargin(image, new Insets(10, 0, 0, 10)); // set margins for each image
                image.fitHeightProperty().bind(primaryStage.heightProperty().divide(7.5)); // resize the image to fit the height of the stage
                image.setPreserveRatio(true); // preserve ratio
                mainBody.getChildren().add(image);
            }
        }
    }

    String getEmptySpace(String placement) {
        String fullIndex = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String existingIndex = "";
        String emptyIndex = "";
        for (int i = 0; i < placement.length(); i += 3) {
            existingIndex += placement.charAt(i + 2);
        }
        for (int i = 0; i < fullIndex.length(); i++) {
            if (existingIndex.indexOf(fullIndex.charAt(i)) == -1) {
                emptyIndex += fullIndex.charAt(i);
            }
        }
        return emptyIndex;
    }

    String getIndex(char index) { // get index string from the third char of the character card placement string
        String firstrow = "4YSMGA";
        String secondrow = "5ZTNHB";
        String thirdrow = "60UOIC";
        String fourthrow = "71VPJD";
        String fifthrow = "82WQKE";
        String sixthrow = "93XRLF";

        for (int i = 0; i < 6; i++) {
            if (index == firstrow.charAt(i)) {
                return i + "0";
            }
            if (index == secondrow.charAt(i)) {
                return i + "1";
            }
            if (index == thirdrow.charAt(i)) {
                return i + "2";
            }
            if (index == fourthrow.charAt(i)) {
                return i + "3";
            }
            if (index == fifthrow.charAt(i)) {
                return i + "4";
            }
            if (index == sixthrow.charAt(i)) {
                return i + "5";
            }
        }
        return "error";
    }

    // FIXME Task 11: Allow players of your Warring States game to play against your simple agent
    public char simpleMove (){ // may directly use task 10's method with updating the board
        return WarringStatesGame.generateMove(gameState.boardPlacement); //simple move
    }
        // simply return a move

    // FIXME Task 12: Integrate a more advanced opponent into your game

    // get a value system

    // use ab pruning to find highest value move
    public double alphaBetaPruning (GameState node, int depth, double alpha, double beta, int playerTurn){
        //use alpha beta pruning to get intelligent move
        double value = 0;
        List<GameState> childNode = new ArrayList<>();
        GameState currentState = node;
        ArrayList<Character> allPossibleMove = WarringStatesGame.generateAllLegalMove(currentState.boardPlacement);
        for (Character move : allPossibleMove) {
            childNode.add(new GameState(WarringStatesGame.updateBoard(currentState.boardPlacement, String.valueOf(move)),
                    updatePlayers(currentState, move), (currentState.playerturn + 1) % gameState.numOfPlayer, gameState.numOfPlayer, currentState.boardPlacement)); // get all possible child game state
        }
        if (depth == 0) {
            return getHeuristicValue(currentState, gameState.numOfPlayer); //reach the leaf and return the heuristic value, assume AI is the last number of player
        }
        if (playerTurn == gameState.numOfPlayer) { // check whether it is the AI's turn
            value = -9999;
            for (GameState cnode : childNode) {
                value = Math.max(value, alphaBetaPruning(cnode, depth - 1, alpha, beta, (playerTurn + 1) % gameState.numOfPlayer));
                alpha = Math.max(value, alpha);
                if (alpha >= beta) {
                    break; //pruning
                }
            }
            return value;


        } else { //if it is not AI's turn, assume all human player is trying to play against AI
            value = 9999;
            for (GameState cnode : childNode) {
                value = Math.min(value, alphaBetaPruning(cnode, depth - 1, alpha, beta, (playerTurn + 1) % gameState.numOfPlayer));
                beta = Math.min(value, beta);
                if (alpha >= beta) {
                    break; //pruning
                }
            }
            return value;
        }
    }

    public char smartMove() {
        List<GameState> childNode = new ArrayList<>();
        ArrayList<Character> allPossibleMove = WarringStatesGame.generateAllLegalMove(gameState.boardPlacement);
        for (Character move : allPossibleMove) { // get the next level of nodes for alpha-beta pruning
            childNode.add(new GameState(WarringStatesGame.updateBoard(gameState.boardPlacement, String.valueOf(move)),
                    updatePlayers(gameState, move), (gameState.playerturn + 1) % gameState.numOfPlayer, gameState.numOfPlayer, gameState.boardPlacement));
        }
        List<Double> alphabetaScore = new ArrayList<>();
        for (GameState child : childNode) {
            alphabetaScore.add(alphaBetaPruning(child, 5, -9999, 9999, 1)); // the init alphabeta pruning method's playerTurn is 1.
        }
        double max = -9999;
        int count = 0;
        for (int i = 0; i < alphabetaScore.size(); i++) { // find the maximum score for AI
            if (alphabetaScore.get(i) > max) {
                max = alphabetaScore.get(i);
                count = i;
            }
        }
        return allPossibleMove.get(count); // return the corresponding move for AI
    }

    public double getHeuristicValue(GameState gameState, int playerTurn) {
        // calculate the heuristic value of the current state using its score
        return 0;
    }

    public ArrayList<Player> updatePlayers(GameState gameState, char move) {

        return null;
    }

    @Override
    public void start(Stage primaryStage) throws Exception { //setup start stage
        this.primaryStage = primaryStage;
        ImageView image = new ImageView(new Image(getClass().getResourceAsStream("assets/startimage.png")));
        StackPane root = new StackPane();
        primaryStage.setTitle("Start game");

        root.getChildren().add(image);
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(2000), root); // add fade animation
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);


        Button startButton = new Button();
        startButton.setText("Start");
        StackPane.setAlignment(startButton,Pos.BOTTOM_CENTER);
        Timeline loadToPreparation = new Timeline(new KeyFrame(Duration.millis(2000), (e) -> { // create timeline so as to wait for animation end
            inputPlayer();
        }));

        startButton.setOnAction((e)->{ // click start button to go to the preparation stage
            fadeTransition.play();
            loadToPreparation.play(); // wait for animation to end
        });
        root.getChildren().add(startButton);

        image.fitHeightProperty().bind(root.heightProperty().divide(1.2));
        image.setPreserveRatio(true);
        Scene primaryscene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);
        this.primaryStage.setScene(primaryscene);
        this.primaryStage.show();
    }


}



