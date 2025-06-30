package service;

import javafx.stage.Stage;
import view.*;

import static view.LevelsMenu.getLevelsMenu;

public class SceneManager {
    public static void setPrimaryStage(Stage primaryStage) {
        SceneManager.primaryStage = primaryStage;
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




}