package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import saveAndLoad.Load;
import service.SceneManager;

public class LevelsMenu {
    private static LevelsMenu levelsMenu;
    private final Scene scene;

    public LevelsMenu(){

        Pane root = new Pane();
        Scene scene = new Scene(root);
        root.setStyle("-fx-background-color: #0d1b2a;");
        Label label = new Label("Choose a level");
        label.setPrefWidth(470);
        label.setLayoutX(Main.STAGE_WIDTH /2 - 200);
        label.setLayoutY(100);
        UI.styler1(label);
        Button menuButton = UI.makeButton("Menu",200,100,200,80);
        UI.styler1(menuButton);
        menuButton.setOnAction(e -> {SceneManager.showMenuView();

        });


        Button lvl1 = UI.makeButton("Level1", Main.STAGE_WIDTH /2,320);
        UI.styler1(lvl1);
        lvl1.setOnAction(e ->Level1.startLevel1());
        Button lvl2 = UI.makeButton("Level2", Main.STAGE_WIDTH /2,475);
        UI.styler1(lvl2);
        lvl2.setOnAction(e -> Load.loadGameData());

        root.getChildren().add(lvl1);
        root.getChildren().add(lvl2);
        root.getChildren().add(menuButton);

        root.getChildren().add(label);
        this.scene = scene;
    }

    public Scene getScene() {
        return scene;
    }

    public static LevelsMenu getLevelsMenu(){
        if(levelsMenu == null){
            levelsMenu = new LevelsMenu();
        }
        return levelsMenu;
    }




}
