package controler;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import view.Levels;
import view.Setting;

public class MenuController {
    public static void exitButton(){
        Alert exitalert = new Alert(Alert.AlertType.CONFIRMATION);
        exitalert.setTitle("Exit");
        exitalert.setHeaderText("are you sure you want to exit");
        if (exitalert.showAndWait().get() == ButtonType.OK) {
            Platform.exit();
        }
    }

    public static void settingButton(){
        Setting.setSetting();
    }
    public static void levelsButton(){
        Levels.setLevels();
    }

}
