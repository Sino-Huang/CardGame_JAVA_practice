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
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.*;


public class Game extends Application {
    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;
    private Stage primaryStage;
    private HBox topControl = new HBox();
    private VBox scorePane= new VBox();
    private GridPane mainBody = new GridPane();
    private GameState gameState = null;
    private String originalBoard = "";
    private String moveHistory = "";

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
            String newBoard = createBoard();
            this.originalBoard = newBoard;
            gameState = new GameState(newBoard, GameState.initPlayers(numOfPlayer, whetherneedAI, whetherSmartAI), 1, numOfPlayer, whetherneedAI, whetherSmartAI); // setup the initial gameState
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
        stage.setTitle("Status Box");
        stage.showAndWait();
    }

    public void warningBox() {
        Stage stage = new Stage();
        Label label = new Label("Your move is illegal! Please Try Again");
        label.setWrapText(true);
        Button button = new Button("OK");
        button.setOnAction(event -> {
            stage.close();
        });
        VBox root = new VBox(label, button);
        root.setAlignment(Pos.CENTER);
        root.setMargin(label, new Insets(15, 15, 15, 15));
        Scene scene = new Scene(root, 300, 300);
        stage.setScene(scene);
        stage.setTitle("Warning");
        stage.showAndWait();
    }

    public void endBox() {
        Stage stage = new Stage();
        double maxScore = -9999;
        Player winner = null;
        for (Player p : gameState.players) {
            if (p.score > maxScore) {
                maxScore = p.score;
                winner = p;
            }
        }
        Label label = new Label("The game is end! Winner is: " + winner.name);
        Button restart = new Button("Restart");
        restart.setOnAction(event -> {
            stage.close();
            inputPlayer();
        });
        Button close = new Button("Close");
        close.setOnAction(event -> {
            stage.close();
            primaryStage.close();
        });
        HBox buttonGroup = new HBox(restart, close);
        buttonGroup.setAlignment(Pos.CENTER);
        buttonGroup.getChildren().forEach(node -> {
            buttonGroup.setMargin(node, new Insets(5, 5, 5, 5));
        });

        VBox root = new VBox(label, buttonGroup);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 300, 300);
        stage.setTitle("End of Game");
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void helpBox() {
        Stage stage = new Stage();
        stage.setTitle("Help");
        Label label = new Label("The game is played by two to four players, using 36 cards.\n" +
                "There are 35 cards representing characters from the Seven Kingdoms, and one card representing the diplomat Zhang Yi.\n" +
                "Each kingdom has a different number of character cards:Qin: eight cards\n" +
                "Qi: seven cards\n" +
                "Chu: six cards\n" +
                "Zhao: five cards\n" +
                "Han: four cards\n" +
                "Wei: three cards\n" +
                "Yan: two cards\n" + "To start the game, the 36 cards are shuffled and laid out in a 6x6 grid. The flags of the seven kingdoms are laid next to the grid." +
                " Players take turns to collect characters to their side, by moving Zhang Yi around the grid." +
                " On her turn, a player CLICK YOUR DESTINATION." +
                " Zhang Yi then moves in the chosen direction to the location of the furthest away character from that kingdom, and collects that character card." +
                " If Zhang Yi passes other characters from the same kingdom while moving, he collects those characters as well." +
                " Each player may move Zhang Yi only once per turn." +
                " At the end of her turn, if the player holds an equal or greater number of characters from a kingdom than any of her opponents, she takes the flag of that kingdom." +
                " (If another player already holds the flag, she takes the flag from that player.)" +
                " The game ends when Zhang Yi cannot move, that is, when there are no cards in any direction (North, East, South, or West) from Zhang Yi." +
                " The player who holds the greatest number of flags at the end of the game wins." +
                " If two or more players hold the same number of flags, the player who holds the flag of the kingdom with the greatest number of characters wins.");

        label.setWrapText(true);

        Button button = new Button("OK");
        button.setOnAction(event -> {
            stage.close();
        });
        VBox group = new VBox(label, button);
        group.setAlignment(Pos.CENTER);
        group.setMargin(label, new Insets(15, 15, 15, 15));
        Scene scene = new Scene(group, 700, 500);
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

        Button helpButton = new Button("Help");
        helpButton.setOnAction(event -> {
            helpBox();
        });

        topControl.getChildren().addAll(restart, previousMov, statusCheck, helpButton);

        makePlacement(gameState.boardPlacement); // setup body panel

        //setup click event
        mainBody.getChildren().forEach((n)->{
            n.setOnMouseClicked((e)->{
                int col = GridPane.getColumnIndex(n);
                int row = GridPane.getRowIndex(n);
                char destination = getPosition(col, row);
                if (WarringStatesGame.isMoveLegal(gameState.boardPlacement, destination)) {
                    this.moveHistory += "" + destination;
                    gameState.previousPlacement = gameState.boardPlacement;
                    gameState.boardPlacement = WarringStatesGame.updateBoard(gameState.boardPlacement, String.valueOf(destination));
                    makePlacement(gameState.boardPlacement); // update the board; playersTurn; previousPlacement
                    //update the PlayerInfo
                    gameState.players = updatePlayers(gameState, destination);
                    //update the Score panel
                    updateScorePanel();
                    gameState.playerturn = gameState.playerturn % gameState.numOfPlayer + 1;

                    //edit the font of the score panel so as to indicate who's turn
                    //remove last playerbox's style
                    scorePane.getChildren().forEach((node)->{
                        node.setStyle("");
                    });
                    //set the new playerbox's style
                    scorePane.getChildren().get(gameState.playerturn - 1).setStyle("-fx-background-color: lightgray");
                    if (WarringStatesGame.generateAllLegalMove(gameState.boardPlacement).size() == 0) {
                        endBox();
                    }

                } else {
                    warningBox();
                }

            });
        });

        for (int i = 0; i < gameState.numOfPlayer; i++) { // setup score panel
            VBox playerBox = new VBox();
            Label label = new Label(gameState.players.get(i).name);
            label.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 20));
            VBox currentCard = new VBox();
            VBox currentFlag = new VBox();
            Label currentCardLabel = new Label("Supporters");
            Label currentFlagLabel = new Label("Alliance country");
            GridPane currentCardImageGrid = new GridPane();
            ImageView currentCardImage = new ImageView(new Image(getClass().getResourceAsStream("assets/initStatus.png")));
            currentCardImageGrid.getChildren().add(currentCardImage);
            GridPane currentFlagImageGrid = new GridPane();
            ImageView currentFlagImage = new ImageView(new Image(getClass().getResourceAsStream("assets/initStatus.png")));
            currentFlagImageGrid.getChildren().add(currentFlagImage);
            currentCardImage.fitWidthProperty().bind(primaryStage.widthProperty().divide(8));
            currentCardImage.setPreserveRatio(true);
            currentFlagImage.fitWidthProperty().bind(primaryStage.widthProperty().divide(8));
            currentFlagImage.setPreserveRatio(true);
            currentCard.getChildren().addAll(currentCardLabel, currentCardImageGrid);
            currentFlag.getChildren().addAll(currentFlagLabel, currentFlagImageGrid);
            HBox imageBox = new HBox(currentCard, currentFlag);
            HBox.setMargin(currentCard, new Insets(0, 20, 0, 0));
            playerBox.getChildren().addAll(label, imageBox);
            scorePane.getChildren().add(playerBox);
            scorePane.setMargin(playerBox, new Insets(10, 20, 10, 0));
            scorePane.getChildren().get(gameState.playerturn - 1).setStyle("-fx-background-color: lightgray");
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
                GridPane.setConstraints(image, Integer.valueOf(index.substring(0,1)), Integer.valueOf(index.substring(1)));
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
                GridPane.setConstraints(image, Integer.valueOf(index.substring(0,1)) , Integer.valueOf(index.substring(1)) );  // set the index of the character
                GridPane.setMargin(image, new Insets(10, 0, 0, 10)); // set margins for each image
                image.fitHeightProperty().bind(primaryStage.heightProperty().divide(7.5)); // resize the image to fit the height of the stage
                image.setPreserveRatio(true); // preserve ratio
                mainBody.getChildren().add(image);
            }
        }
    }

    String getEmptySpace(String placement) {
        String fullIndex = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuffer full = new StringBuffer("ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");

        String existingIndex = "";
        String emptyIndex = "";
        for (int i = 0; i < placement.length(); i += 3) {
            full.deleteCharAt(full.indexOf(placement.substring(i + 2, i + 3)));
        }

        return full.toString();
    }

    char getPosition(int col, int row) {
        List<String> position = new ArrayList<>();
        position.add("4YSMGA");
        position.add("5ZTNHB");
        position.add("60UOIC");
        position.add("71VPJD");
        position.add("82WQKE");
        position.add("93XRLF");

        return position.get(row).charAt(col);

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
                return String.valueOf(i) + "0";
            }
            if (index == secondrow.charAt(i)) {
                return String.valueOf(i) + "1";
            }
            if (index == thirdrow.charAt(i)) {
                return String.valueOf(i) + "2";
            }
            if (index == fourthrow.charAt(i)) {
                return String.valueOf(i) + "3";
            }
            if (index == fifthrow.charAt(i)) {
                return String.valueOf(i) + "4";
            }
            if (index == sixthrow.charAt(i)) {
                return String.valueOf(i) + "5";
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
                    updatePlayers(currentState, move), (currentState.playerturn % gameState.numOfPlayer + 1), gameState.numOfPlayer, currentState.boardPlacement)); // get all possible child game state
        }
        if (depth == 0) {
            return getHeuristicValue(currentState, gameState.numOfPlayer); //reach the leaf and return the heuristic value, assume AI is the last number of player
        }
        if (playerTurn == gameState.numOfPlayer) { // check whether it is the AI's turn
            value = -9999;
            for (GameState cnode : childNode) {
                value = Math.max(value, alphaBetaPruning(cnode, depth - 1, alpha, beta, (playerTurn % gameState.numOfPlayer +1) ));
                alpha = Math.max(value, alpha);
                if (alpha >= beta) {
                    break; //pruning
                }
            }
            return value;


        } else { //if it is not AI's turn, assume all human player is trying to play against AI
            value = 9999;
            for (GameState cnode : childNode) {
                value = Math.min(value, alphaBetaPruning(cnode, depth - 1, alpha, beta, (playerTurn % gameState.numOfPlayer + 1)));
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
                    updatePlayers(gameState, move), (gameState.playerturn  % gameState.numOfPlayer + 1), gameState.numOfPlayer, gameState.boardPlacement));
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
        //TODO
        // calculate the heuristic value of the current state using its score
        return 0;
    }

    public static Flags getFlag(int index) {
        Flags output = null;
        switch (index) {
            case 0:
                output = Flags.A;
                break;
            case 1:
                output = Flags.B;
                break;
            case 2:
                output = Flags.C;
                break;
            case 3:
                output = Flags.D;
                break;
            case 4:
                output = Flags.E;
                break;
            case 5:
                output = Flags.F;
                break;
            case 6:
                output = Flags.G;
                break;
        }
        return output;
    }
    //update the gameState.players information using task 7,8 function
    public ArrayList<Player> updatePlayers(GameState gameState, char move) {
        ArrayList<Player> output = new ArrayList<>(gameState.players);

        String supporters = WarringStatesGame.getSupporters(originalBoard, moveHistory, gameState.numOfPlayer, gameState.playerturn - 1);
        String[] supporterList = supporters.split("(?<=\\G.{2})");
        HashSet<Cards> cards = new HashSet<>();
        for (Cards n : Cards.values()) {
            for (int i = 0; i < supporterList.length; i++) {
                if (supporterList[i].toUpperCase().equals(n.name())) {
                    cards.add(n);
                }
            }
        }
        //update the card of the specific player
        output.get(gameState.playerturn - 1).cards = cards;

        //update the flag of all players
        int[] flags = WarringStatesGame.getFlags(originalBoard, moveHistory, gameState.numOfPlayer);
        for (int i = 0; i < output.size(); i++) {
            HashSet<Flags> flagSet = new HashSet<>();
            for (int j = 0; j < flags.length; j++) {
                if (flags[j] == i) {
                    flagSet.add(getFlag(j));
                }
            }
            output.get(i).flags = flagSet;
        }

        return output;
    }

    public void updateScorePanel() {
        gameState.players.forEach(n -> {
            updateSingleScorePanel(n);
        });
    }

    public void updateSingleScorePanel(Player player) {
        Node targetNode = scorePane.getChildren().get(player.position - 1);
        if (targetNode instanceof VBox) {
            Node imageBox = ((VBox) targetNode).getChildren().get(1);
            if (imageBox instanceof HBox) {
                Node currentCard = ((HBox) imageBox).getChildren().get(0);
                Node currentFlag = ((HBox) imageBox).getChildren().get(1);
                if (currentCard instanceof VBox && currentFlag instanceof VBox) {
                    Node cardImageGrid = ((VBox) currentCard).getChildren().get(1);
                    Node flagImageGrid = ((VBox) currentFlag).getChildren().get(1);
                    if (cardImageGrid instanceof GridPane && flagImageGrid instanceof GridPane) {
                        updateCardAndFlagImage((GridPane)cardImageGrid, player, true);
                        updateCardAndFlagImage((GridPane)flagImageGrid, player, false);
                    }
                }
            }
        }
    }

    public void updateCardAndFlagImage(GridPane gridPane , Player player, boolean card) {
        gridPane.setMinSize(116,116);
        HashSet targetSet = null;
        if (card) {
            targetSet = player.cards;
        } else {
            targetSet = player.flags;
        }
        if (targetSet.size() != 0) {
            gridPane.getChildren().clear();
            targetSet.forEach(o -> {
                ImageView imageView = null;
                if (o instanceof Cards) {
                    imageView = ((Cards) o).imageView;
                    imageView.setOnMouseClicked(null);
                }
                if (o instanceof Flags) {
                    imageView = ((Flags) o).imageView;
                    imageView.setPreserveRatio(true);
                    imageView.setOnMouseClicked(null);
                }
                gridPane.getChildren().add(imageView);
                gridPane.setMargin(imageView, new Insets(1, 1, 1, 1));
            });

            //set coordinate of card and flags
            int col = 0;
            int row = 0;
            for (Node node : gridPane.getChildren()) {
                GridPane.setConstraints(node, col, row);
                if (card) {
                    if (col == 3) {
                        col = 0;
                        row += 1;
                    } else {
                        col += 1;
                    }
                } else {
                    row += 1;
                }
                // set size of card and flags differently
                if (node instanceof ImageView && card) {
                    ((ImageView) node).fitHeightProperty().bind(primaryStage.widthProperty().divide(30));
                }
                if (node instanceof ImageView && !card) {
                    ((ImageView) node).fitHeightProperty().bind(primaryStage.widthProperty().divide(50));
                }
            }
        }

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



