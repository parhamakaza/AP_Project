package service;

import controller.PacketContoller;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.*;

import static view.LevelsMenu.getLevelsMenu;

public class SceneManager {
    public static void setPrimaryStage(Stage primaryStage) {
        SceneManager.primaryStage = primaryStage;
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    private static Stage primaryStage;
    private static AudioManager audioManager;

    public static void setAudioManager(AudioManager audioManager) {
        SceneManager.audioManager = audioManager;
    }

    public static void showMenuView() {
        Menu menu = Menu.getMenu();
        primaryStage.setScene(menu.getScene());
    }

    public static void showLevelsMenuView() {
        LevelsMenu levelsMenu = getLevelsMenu();
        primaryStage.setScene(levelsMenu.getScene());
    }

    public static void showSettingsView() {
        Setting setting = Setting.getSetting(audioManager);
        primaryStage.setScene(setting.getScene());
    }
    public static void goToLevel(Scene scene){
        primaryStage.setScene(scene);
    }
    public static Pane getCurrentPane(){
        return (Pane) SceneManager.primaryStage.getScene().getRoot();
    }



}