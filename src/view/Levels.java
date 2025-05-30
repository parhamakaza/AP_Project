package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import model.Buttons;
import model.Labels;

import static view.Setting.menuButton;

public class Levels {

    public static void setLevels(){
        Pane root = new Pane();
        Scene scene = new Scene(root);
        root.setStyle("-fx-background-color: #0d1b2a;");
        Label label = new Label("Choose a level");
        label.setPrefWidth(470);
        label.setLayoutX(Main.stageWidth/2 - 200);
        label.setLayoutY(100);
        Labels.styler1(label);
        Button menuButton = Buttons.makeButton("Menu",200,100,200,80);
        Buttons.styler1(menuButton);

        menuButton.setOnAction(e -> menuButton());
        Button lvl1 = Buttons.makeButton("Level1", Main.stageWidth/2,320);
        Buttons.styler1(lvl1);
        lvl1.setOnAction(e ->Level1.startLevel1());
        Button lvl2 = Buttons.makeButton("Level2", Main.stageWidth/2,475);
        Buttons.styler1(lvl2);
        lvl2.setOnAction(e ->Level2.startLevel2());

        root.getChildren().add(lvl1);
        root.getChildren().add(lvl2);
        root.getChildren().add(menuButton);

        root.getChildren().add(label);
        Main.theStage.setScene(scene);

    }


}
