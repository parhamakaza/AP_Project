package view;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

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
            label.setLayoutX(Main.STAGE_WIDTH /2 - 200);
            label.setLayoutY(100);
            UI.styler1(label);
            Button startButton = UI.makeButton("start", Main.STAGE_WIDTH /2,320);
            UI.styler1(startButton);
            startButton.setOnAction(e -> {
                Level1.startLevel1();
            });
            Button levelButton = UI.makeButton("Levels", Main.STAGE_WIDTH /2,475);
            UI.styler1(levelButton);
            levelButton.setOnAction(e -> {
               SceneManager.showLevelsMenuView();
            });
            Button settingButton = UI.makeButton("Setting", Main.STAGE_WIDTH /2,630 );
            UI.styler1(settingButton);
            settingButton.setOnAction(e -> {
                SceneManager.showSettingsView();
            });

            Button exitButton = UI.makeButton("Exit", Main.STAGE_WIDTH /2,785 );
            UI.styler1(exitButton);
            exitButton.setOnAction(e -> {
              exitButton();
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
    public static void exitButton(){
        Alert exitalert = new Alert(Alert.AlertType.CONFIRMATION);
        exitalert.setTitle("Exit");
        exitalert.setHeaderText("are you sure you want to exit");
        if (exitalert.showAndWait().get() == ButtonType.OK) {
            Platform.exit();
        }
    }






}
