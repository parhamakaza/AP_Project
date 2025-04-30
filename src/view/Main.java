package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import controler.MenuController;
import javafx.stage.StageStyle;
import model.Computer;

public class Main extends Application {
    //private final SystemManager systems = new SystemManager();
    public static double stageWidth = 1920;
    public static double stageHeight =  1080;
    public static Stage theStage;
    public static void main(String[] args) {
        launch(args);
    }
    public static MediaPlayer musicPlayer;


    @Override
    public void start(Stage stage) {
        theStage = stage;
        stage.setTitle("Blue print hell");
        Image StageIcon = new Image("resources/Icon.jpg");
        stage.getIcons().add(StageIcon);


        stage.setWidth(stageWidth);
        stage.setHeight(stageHeight);
        stage.setResizable(false);


        //stage.initStyle(StageStyle.UNDECORATED);
        //Scene scene =  Menu.menuConfig();

        Menu.menuConfig();
        String musicUrl = Setting.class
                .getResource("/resources/music.mp3")
                .toExternalForm();
        Media media = new Media(musicUrl);
        MediaPlayer player = new MediaPlayer(media);
        player.setVolume(0.2);

        player.setCycleCount(MediaPlayer.INDEFINITE);

        //player.play();
        musicPlayer=player;

        //stage.setScene(scene);
        stage.setOnCloseRequest(event -> {event.consume();
            MenuController.exitButton();});
        stage.show();







    }

}