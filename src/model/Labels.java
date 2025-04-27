package model;

import javafx.scene.control.Label;

public class Labels {
    public static void styler1(Label label){
        label.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #002b36 0%, #003842 50%, #002b36 100%);" +
                "-fx-background-radius: 8;" +
                "-fx-padding: 8 16 8 16;" +
                "-fx-text-fill: #00ffff;" +
                "-fx-font-size: 50px;" +
                "-fx-font-weight: bold;" +
                "-fx-effect: dropshadow(one-pass-box, rgba(0,255,255,0.6), 4, 0.0, 0, 0);" +
                "-fx-alignment: center;" +
                "-fx-border-color: #0088aa;" +
                "-fx-border-width: 2;" +
                "-fx-border-radius: 8;");
    }
}
