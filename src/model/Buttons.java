package model;

import javafx.scene.control.Button;

import java.awt.*;

public class Buttons {

    public static Button makeButton (String s ,double w, double h, double lx, double ly){
        Button button = makeButton(s , lx , ly);
        button.setPrefWidth(w);
        button.setPrefHeight(h);


        return button;
    }

    public static Button makeButton (String s, double lx, double ly){
        Button button = new Button();
        button.setText(s);
        double h = 150;
        button.setPrefHeight(h);
        double w = 400 ;
        button.setPrefWidth(470);

        button.setLayoutX(lx - w/2);
        button.setLayoutY(ly - h/2);


        return button;
    }

    public static void styler1(Button button){
        String style = "-fx-background-color: transparent;"+
                "-fx-border-color: #00b4d8; /* cyan-ish glow */"+
                "-fx-border-width: 2;"+
                "-fx-text-fill: #00b4d8;"+
                "-fx-font-size: 16px;"+
                "-fx-font-weight: bold;"+
                "-fx-padding: 10 20 10 20;"+
                "-fx-background-radius: 5;"+
                "-fx-border-radius: 5;"+
                "-fx-effect: dropshadow(gaussian, #00b4d8, 5, 0.5, 0, 0));";

        button.setStyle(style);
        button.setOnMouseEntered(e -> {
            button.setStyle("-fx-background-color: #00b4d810; ");
        });
        button.setOnMouseExited(e -> {
            button.setStyle(style);
        });

    }

}
