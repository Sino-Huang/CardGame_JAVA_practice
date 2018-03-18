package comp1110.ass2.gui;

import comp1110.ass2.WarringStatesGame;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * A very simple viewer for card layouts in the Warring States game.
 * <p>
 * NOTE: This class is separate from your main game class.  This
 * class does not play a game, it just illustrates various card placements.
 */
public class Viewer extends Application {

    private static final int VIEWER_WIDTH = 933;
    private static final int VIEWER_HEIGHT = 700;
    private static final String testplacement = "a0Aa1Ba2Ca3Da4Ea5Fa6Ga7Hb0Ib1Jb2Kb3Lb4Mb5Nb6Oc0Pc1Qc2Rc3Sc4Tc5Ud0Vd1Wd2Xd3Yd4Ze01e12e23e34f05f16f27g08g19Z90";

    private static final String URI_BASE = "assets/";

    private final Group root = new Group();
    private final Group controls = new Group();
    private GridPane gridPane = new GridPane();
    TextField textField;

    /**
     * Draw a placement in the window, removing any previously drawn one
     *
     * @param placement A valid placement string
     */
    void makePlacement(String placement) {
        // FIXME Task 4: implement the simple placement viewer
        placement = testplacement; //for testing
        gridPane.setPadding(new Insets(2, 2, 2, 2));
        gridPane.setVgap(3); // individual cells
        gridPane.setHgap(3);

        //check whether the placement is a valid placement

        for (int i = 0; i < placement.length(); i += 3) {
            String kingdom = String.valueOf(placement.substring(i,i+2));
            String kingdomname = kingdom.toUpperCase();
            ImageView image = null;
            for (Characters characters : Characters.values()) {
                if (kingdomname.equals(characters.name())) {
                    image = characters.imageView;
                }
            }
            String index = getIndex(placement.charAt(i + 2));
            GridPane.setConstraints(image, index.charAt(0), index.charAt(1));
            gridPane.getChildren().add(image);
        }

    }

    String getIndex(char index) {
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

    /**
     * Create a basic text field for input and a refresh button.
     */
    private void makeControls() {
        Label label1 = new Label("Placement:");
        textField = new TextField();
        textField.setPrefWidth(300);
        Button button = new Button("Refresh");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                makePlacement(textField.getText());
                textField.clear();
            }
        });
        HBox hb = new HBox();
        hb.getChildren().addAll(label1, textField, button);
        hb.setSpacing(10);
        hb.setLayoutX(130);
        hb.setLayoutY(VIEWER_HEIGHT - 50);
        controls.getChildren().add(hb);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Warring States Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);

        root.getChildren().addAll(controls,gridPane);

        makeControls();

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
