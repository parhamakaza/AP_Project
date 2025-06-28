package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Buttons;
import model.Labels;

import static controler.LevelsController.collisionPlayer;
import static controler.LevelsController.connectionPlayer;

public class Setting {
    private static double volume = 0.2;


    public static void setSetting(){
        Pane root = new Pane();
        Scene settingScene = new Scene(root);
        Button button = Buttons.makeButton("Menu",200,100,200,80);
                Buttons.styler1(button);

        button.setOnAction(e -> menuButton());



        Slider volumeSlider = new Slider(0, 1, volume);// min=0, max=1,

        volumeSlider.setPrefHeight(10);
        volumeSlider.setPrefWidth(500);

        volumeSlider.setLayoutX(Main.stageWidth / 2 - volumeSlider.getPrefWidth()/2);
        volumeSlider.setLayoutY(Main.stageHeight /2 - volumeSlider.getPrefHeight()/2 );
        Main.backGroundMusicPlayer.volumeProperty().bind(volumeSlider.valueProperty());
        connectionPlayer.volumeProperty().bind(volumeSlider.valueProperty());
        collisionPlayer.volumeProperty().bind(volumeSlider.valueProperty());
        volumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
                        volume = (double) newVal;
                });
        volume = volumeSlider.getValue();
        Label label = new Label("Music Volume");

        HBox container = new HBox(label);
        container.setAlignment(Pos.CENTER);
        container.prefWidth(500);
        label.setPrefWidth(500);
        label.setPrefHeight(50);
        //container.setLayoutX(Main.stageWidth/2 - label.getPrefWidth());
       // container.setLayoutY(100);
        Labels.styler1(label);


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

        vbox.setLayoutX(Main.stageWidth/2 - volumeSlider.getPrefWidth()/2 + 30);
        vbox.setLayoutY(Main.stageHeight/2  - (40 + volumeSlider.getPrefHeight() + label.getHeight()) );
        vbox.setAlignment(Pos.CENTER);

        root.getChildren().add(vbox);



        root.setStyle("-fx-background-color: #0d1b2a;");
        root.getChildren().add(button);

        Main.theStage.setScene(settingScene);

    }
    public static void menuButton(){
        Menu.menuConfig();

    }




}
