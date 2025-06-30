package controller;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;


public class MenuController {
    public static void exitButton(){
        Alert exitalert = new Alert(Alert.AlertType.CONFIRMATION);
        exitalert.setTitle("Exit");
        exitalert.setHeaderText("are you sure you want to exit");
        if (exitalert.showAndWait().get() == ButtonType.OK) {
            Platform.exit();
        }
    }

}
