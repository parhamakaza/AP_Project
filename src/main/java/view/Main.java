package view;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import service.AudioManager;
import service.SceneManager;

public class Main extends Application {
    public static final double STAGE_WIDTH = 1920;
    public static final double STAGE_HEIGHT =  1080;

    public static void main(String[] args) {
        launch(args);
    }



    @Override
    public void start(Stage stage) {
        stage.setTitle("Blue print hell");
        Image StageIcon = new Image("/Icon.jpg");
        stage.getIcons().add(StageIcon);
        stage.setWidth(STAGE_WIDTH);
        stage.setHeight(STAGE_HEIGHT);
        stage.setResizable(false);


        //stage.initStyle(StageStyle.UNDECORATED);
        //Scene scene =  Menu.menuConfig();

        SceneManager.setPrimaryStage(stage);
        SceneManager.setAudioManager(new AudioManager());
        SceneManager.showMenuView();


        stage.setOnCloseRequest(event -> {event.consume();
        Menu.exitButton();});
        stage.show();
    }
}