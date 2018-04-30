package comp1110.ass2.gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public enum Flags {
    A,B,C,D,E,F,G;

    public final ImageView imageView;

    Flags() {
        this.imageView = new ImageView(new Image(getClass().getResourceAsStream("assets/" + this.name() + ".PNG")));
    }
}
