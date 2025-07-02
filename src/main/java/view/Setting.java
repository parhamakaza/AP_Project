package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import service.AudioManager;
import service.File;
import service.SceneManager;


public class Setting {
    private Scene scene;
    private static double volume = File.getFromMap("volume");
    private static Setting setting;

    public Scene getScene() {
        return scene;
    }

    private Setting(AudioManager audioManager){

        Pane root = new Pane();
        Scene scene = new Scene(root);
        Button button = UI.makeButton("Menu",200,100,200,80);
        UI.styler1(button);
        button.setOnAction(e ->SceneManager.showMenuView());
        Slider volumeSlider = new Slider(0, 1, volume);// min=0, max=1,

        volumeSlider.setPrefHeight(10);
        volumeSlider.setPrefWidth(500);
        audioManager.backgroundMusicPlayer.volumeProperty().bind(volumeSlider.valueProperty());
        audioManager.connectionPlayer.volumeProperty().bind(volumeSlider.valueProperty());
        audioManager.collisionPlayer.volumeProperty().bind(volumeSlider.valueProperty());
        volumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            volume = (double) newVal;
        });
        volumeSlider.setOnMouseReleased(event -> {
            // This code now runs only WHEN the mouse is released
            File.putToMap("volume", volumeSlider.getValue());
        });
        volume = volumeSlider.getValue();
        Label label = new Label("Music Volume");
        label.setPrefWidth(500);
        label.setPrefHeight(50);

        UI.styler1(label);

        UI.styleSlider(volumeSlider);

        VBox vbox = new VBox(10, label, volumeSlider);
        vbox.setLayoutX(Main.STAGE_WIDTH /2 - volumeSlider.getPrefWidth()/2 + 30);
        vbox.setLayoutY(Main.STAGE_HEIGHT /2  - (40 + volumeSlider.getPrefHeight() + label.getHeight()) );
        vbox.setAlignment(Pos.CENTER);

        root.getChildren().add(vbox);
        root.setStyle("-fx-background-color: #0d1b2a;");
        root.getChildren().add(button);
        this.scene =scene;
    }

    public static Setting getSetting(AudioManager audioManager){
        if(setting == null){
            setting = new Setting(audioManager);
        }
        return setting;
    }


    public static void menuButton(){
        SceneManager.showMenuView();
    }




}
