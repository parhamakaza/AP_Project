package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import model.Buttons;
import controller.MenuController;
import model.Labels;
import service.SceneManager;


public class Menu {
    private static Menu menu;
    private static Scene scene;

    public static Scene getScene() {
        return scene;
    }

    private  Menu(){
            Pane root = new Pane();
            Scene scene = new Scene(root);
            Label label = new Label("Blue Print Hell");
            label.setPrefWidth(470);
            label.setLayoutX(Main.STAGEWIDTH /2 - 200);
            label.setLayoutY(100);
            Labels.styler1(label);
            Button startButton = Buttons.makeButton("start", Main.STAGEWIDTH /2,320);
            Buttons.styler1(startButton);
            startButton.setOnAction(e -> {
                Level1.startLevel1();
            });
            Button levelButton = Buttons.makeButton("Levels", Main.STAGEWIDTH /2,475);
            Buttons.styler1(levelButton);
            levelButton.setOnAction(e -> {
               SceneManager.showLevelsMenuView();
            });
            Button settingButton = Buttons.makeButton("Setting", Main.STAGEWIDTH /2,630 );
            Buttons.styler1(settingButton);
            settingButton.setOnAction(e -> {
                SceneManager.showSettingsView();
            });

            Button exitButton = Buttons.makeButton("Exit", Main.STAGEWIDTH /2,785 );
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


            this.scene = scene;
        }

    public static Menu getMenu() {
        if (menu == null) {
            menu = new Menu();
        }
        return menu;
    }






}
