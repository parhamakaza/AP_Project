package view;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import model.Buttons;
import controler.MenuController;
import model.Labels;

public class Menu {
        public static void menuConfig(){
            Pane root = new Pane();
            Scene scene = new Scene(root );
            Label label = new Label("Blue Print Hell");
            label.setPrefWidth(470);
            label.setLayoutX(Main.stageWidth/2 - 200);
            label.setLayoutY(100);
            Labels.styler1(label);
            Button startButton = Buttons.makeButton("start", Main.stageWidth/2,320);
            Buttons.styler1(startButton);
            startButton.setOnAction(e -> {
                Level1.startLevel1();
            });
            Button levelButton = Buttons.makeButton("Levels", Main.stageWidth/2,475);
            Buttons.styler1(levelButton);
            levelButton.setOnAction(e -> {
                MenuController.levelsButton();
            });
            Button settingButton = Buttons.makeButton("Setting", Main.stageWidth/2,630 );
            Buttons.styler1(settingButton);
             settingButton.setOnAction(e -> {
                MenuController.settingButton();
            });
            Button exitButton = Buttons.makeButton("Exit", Main.stageWidth/2,785 );
            Buttons.styler1(exitButton);
            exitButton.setOnAction(e -> {
                MenuController.exitButton();
            });






            root.getChildren().add(startButton);
            root.getChildren().add(levelButton);
            root.getChildren().add(settingButton);
            root.getChildren().add(exitButton);
            root.getChildren().add(label);


            root.setStyle("-fx-background-color: #0d1b2a;");



            Main.theStage.setScene(scene);

        }





}
