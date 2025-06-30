package view;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import controller.MenuController;
import service.AudioManager;
import service.SceneManager;

public class Main extends Application {
    public static final double STAGEWIDTH = 1920;
    public static final double stageheight =  1080;

    public static void main(String[] args) {
        launch(args);
    }
    public static MediaPlayer backGroundMusicPlayer;


    @Override
    public void start(Stage stage) {
        stage.setTitle("Blue print hell");
        Image StageIcon = new Image("/Icon.jpg");
        stage.getIcons().add(StageIcon);
        stage.setWidth(STAGEWIDTH);
        stage.setHeight(stageheight);
        stage.setResizable(false);


        //stage.initStyle(StageStyle.UNDECORATED);
        //Scene scene =  Menu.menuConfig();

        SceneManager.setPrimaryStage(stage);
        SceneManager.setAudioManager(new AudioManager());
        SceneManager.showMenuView();


        stage.setOnCloseRequest(event -> {event.consume();
        MenuController.exitButton();});
        stage.show();
    }
}