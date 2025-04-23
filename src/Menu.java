import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Menu {
        public static Scene menuConfig(){
            Pane root = new Pane();
            Scene scene = new Scene(root );
            Button startButton = Buttons.makeButton("start",Main.stageWidth/2,320);
            Buttons.styler1(startButton);
            Button levelButton = Buttons.makeButton("Levels",Main.stageWidth/2,475);
            Buttons.styler1(levelButton);
            Button settingButton = Buttons.makeButton("Setting",Main.stageWidth/2,630 );
            Buttons.styler1(settingButton);
            Button exitButton = Buttons.makeButton("Exit",Main.stageWidth/2,785 );
            Buttons.styler1(exitButton);
            exitButton.setOnAction(e -> {
                exit();
            });





            root.getChildren().add(startButton);
            root.getChildren().add(levelButton);
            root.getChildren().add(settingButton);
            root.getChildren().add(exitButton);

            root.setStyle("-fx-background-color: #0d1b2a;");



            return scene;


        }
        private static void exit(){
                Alert exitalert = new Alert(Alert.AlertType.CONFIRMATION);
                exitalert.setTitle("Exit");
                exitalert.setHeaderText("are you sure you want to exit");
                if (exitalert.showAndWait().get() == ButtonType.OK) {
                    Platform.exit();
                }
        }





}
