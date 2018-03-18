package comp1110.ass2.gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public enum Characters {
    A0,A1,A2,A3,A4,A5,A6,A7,B0,B1,B2,B3,B4,B5,B6,C0,C1,C2,C3,C4,C5,D0,D1,D2,D3,D4,E0,E1,E2,E3,F0,F1,F2,G0,G1,Z9;
    public final ImageView imageView;

    Characters() {
        this.imageView = new ImageView(new Image(getClass().getResourceAsStream("assets/" + this.name() + ".png"), 90, 90, true, true));
    }
}
