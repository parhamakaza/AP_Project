package controller;

import model.Level;
import view.LevelView;

import java.util.HashMap;

public class LevelController {
    public static HashMap<Level, LevelView> levelViewMap = new HashMap<>();

    public static LevelView makeLevel(Level level) {
        LevelView levelView = new LevelView(level);
        levelViewMap.put(level, levelView);
        return levelView;
    }

}
