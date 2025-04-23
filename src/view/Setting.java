package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import model.Buttons;

public class Setting {

    public static void setSetting(){
        Pane root = new Pane();
        Scene settingScene = new Scene(root);
        Button button = Buttons.makeButton("Menu",200,100,200,80);
                Buttons.styler1(button);

        button.setOnAction(e -> menuButton());



        Slider volumeSlider = new Slider(0, 1, 0);   // min=0, max=1, initial=0.5
        volumeSlider.setPrefHeight(10);
        volumeSlider.setPrefWidth(500);

        volumeSlider.setLayoutX(Main.stageWidth / 2 - volumeSlider.getPrefWidth()/2);
        volumeSlider.setLayoutY(Main.stageHeight /2 - volumeSlider.getPrefHeight()/2 );
        Main.musicPlayer.volumeProperty().bind(volumeSlider.valueProperty());
        Label label = new Label("Music Vloume");

        HBox container = new HBox(label);
        container.setAlignment(Pos.CENTER);
        container.prefWidth(500);
        label.setPrefWidth(500);
        label.setPrefHeight(50);
        //container.setLayoutX(Main.stageWidth/2 - label.getPrefWidth());
       // container.setLayoutY(100);
        label.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #002b36 0%, #003842 50%, #002b36 100%);" +
                "-fx-background-radius: 8;" +
                "-fx-padding: 8 16 8 16;" +
                "-fx-text-fill: #00ffff;" +
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;" +
                "-fx-effect: dropshadow(one-pass-box, rgba(0,255,255,0.6), 4, 0.0, 0, 0);" +
                "-fx-alignment: center;" +
                "-fx-border-color: #0088aa;" +
                "-fx-border-width: 2;" +
                "-fx-border-radius: 8;");






        volumeSlider.setStyle("-fx-background-color: linear-gradient(to right, #002b36 0%, #003842 25%, #002b36 75%, #003842 100%); " +
                "-fx-background-insets: 0; " +
                "-fx-background-radius: 4; " +
                "-fx-padding: 4 0 4 0; " +
                "-fx-control-inner-background: transparent; " +        // ensure track shows through
                "-fx-accent: #00ffff; " +                              // the default fill color
                "-fx-focus-color: rgba(0,255,255,0.9,1); " +           // glow when focused
                "-fx-faint-focus-color: rgba(0,255,255,0.4,0.5);"
        );


        VBox vbox = new VBox(10, label, volumeSlider);

        vbox.setLayoutX(Main.stageWidth/2 - volumeSlider.getPrefWidth()/2);
        vbox.setLayoutY(Main.stageHeight/2  - (10 + volumeSlider.getPrefHeight() + label.getHeight()) /2);
        vbox.setAlignment(Pos.CENTER);

        root.getChildren().add(vbox);



        root.setStyle("-fx-background-color: #0d1b2a;");
        root.getChildren().add(button);
        //root.getChildren().add(volumeSlider);
        //root.getChildren().add(label);
        //root.getChildren().add(container);

        Main.theStage.setScene(settingScene);

    }
    public static void menuButton(){
        Menu.menuConfig();


    }




}
