package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import model.Buttons;

public class Levels {
    public static void setLevels(){
        Pane root = new Pane();
        Scene scene = new Scene(root);
        root.setStyle("-fx-background-color: #0d1b2a;");
        Label label = new Label("Choose a level");
        label.setPrefWidth(470);
        label.setLayoutX(Main.stageWidth/2 - 200);
        label.setLayoutY(100);
        label.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #002b36 0%, #003842 50%, #002b36 100%);" +
                "-fx-background-radius: 8;" +
                "-fx-padding: 8 16 8 16;" +
                "-fx-text-fill: #00ffff;" +
                "-fx-font-size: 50px;" +
                "-fx-font-weight: bold;" +
                "-fx-effect: dropshadow(one-pass-box, rgba(0,255,255,0.6), 4, 0.0, 0, 0);" +
                "-fx-alignment: center;" +
                "-fx-border-color: #0088aa;" +
                "-fx-border-width: 2;" +
                "-fx-border-radius: 8;");
        Button lvl1 = Buttons.makeButton("Level1", Main.stageWidth/2,320);
        Buttons.styler1(lvl1);
        Button lvl2 = Buttons.makeButton("Level2", Main.stageWidth/2,475);
        Buttons.styler1(lvl2);

        root.getChildren().add(lvl1);
        root.getChildren().add(lvl2);
        root.getChildren().add(label);
        Main.theStage.setScene(scene);

    }


}
