import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    public static double stageWidth = 1920;
    public static double stageHeight =  1080;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Blue print hell");
        Image StageIcon = new Image("resources/Icon.jpg");
        stage.getIcons().add(StageIcon);


        stage.setWidth(stageWidth);
        stage.setHeight(stageHeight);
        stage.setResizable(false);

        Scene scene =  Menu.menuConfig();

        stage.setScene(scene);
        stage.setOnCloseRequest(event -> {event.consume();
            exit(stage);});
        stage.show();







    }
    public void exit(Stage stage){
        Alert exitalert = new Alert(Alert.AlertType.CONFIRMATION);
        exitalert.setTitle("Exit");
        exitalert.setHeaderText("are you sure you want to exit");
        if (exitalert.showAndWait().get() == ButtonType.OK){
            stage.close();
        }
    }
}