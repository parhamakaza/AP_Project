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

public class Menu {
        public static void menuConfig(){
            Pane root = new Pane();
            Scene scene = new Scene(root );
            Label label = new Label("Blue Print Hell");
            label.setPrefWidth(470);
            label.setLayoutX(Main.stageWidth/2 - 200);
            label.setLayoutY(100);
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
            Button startButton = Buttons.makeButton("start", Main.stageWidth/2,320);
            Buttons.styler1(startButton);
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
