package view;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import saveAndLoad.Load;
import service.SceneManager;

import java.util.Optional;


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
                showConfirmationDialog();
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


    public void showConfirmationDialog() {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Load Game");

        // ... (rest of the styling and button creation code is the same)
        ButtonType buttonTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-background-color: #001a21;");
        Label headerLabel = new Label("Continue previous game?");
        UI.styler1(headerLabel); // Using your UI class
        dialogPane.setHeader(headerLabel);

        Button yesButton = (Button) dialogPane.lookupButton(buttonTypeYes);
        Button noButton = (Button) dialogPane.lookupButton(buttonTypeNo);
        UI.styler1(yesButton);
        UI.styler1(noButton);
        // --- End of styling ---


        // Show the dialog and get the result
        Optional<ButtonType> result = alert.showAndWait();

        // ✅ This is the key change: execute the provided actions
        if (result.isPresent() && result.get() == buttonTypeYes) {
            Load.loadGameData();
        } else {
            Level1.startLevel1() ;
        }
    }



}
