package comp1110.ass2.gui;

import comp1110.ass2.Player;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.HashMap;


public class Game extends Application {
    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;
    private Stage primaryStage;
    private HBox topControl = new HBox();
    private VBox scorePane= new VBox();
    private GridPane mainBody = new GridPane();
    private BorderPane root = new BorderPane(mainBody, topControl, scorePane, null, null);


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
            preparation();
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

    void preparation() { // preparation stage to store necessary information
        HashMap<String, Integer> output = new HashMap<>();
        HBox numPlayer = new HBox(); // number of player box
        Label numPlayerLabel = new Label("Num of Player: ");
        TextField numPlayerText = new TextField();
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
        AILevel.getChildren().addAll(AILevelLabel, choiceBox);
        AILevel.setAlignment(Pos.BASELINE_CENTER);

        Button nextButton = new Button("Next");

        VBox root = new VBox(); // combine all box and button together
        root.getChildren().addAll(numPlayer, needAI, AILevel, nextButton);
        root.setSpacing(25);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);

        primaryStage.setScene(scene);
    }
}



