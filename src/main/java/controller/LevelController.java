package controller;

import manager.LevelManager;
import manager.LevelManager.*;
import model.Level;
import view.LevelView;

import static controller.ComponentsController.TheComponentsController;

public class LevelController {

    public static LevelView makeLevel(Level level) {
        LevelView levelView = new LevelView(level);
        LevelManager.theLevelManager =  new LevelManager(level);
        TheComponentsController.putView(level, levelView);
        return levelView;
    }

}
