package model;

import controler.LevelsController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import view.Main;
import view.Menu;

import java.util.ArrayList;


public class Level {
    public  int generatedPackets = 0;
    public  int lostPackets = 0;
    public IntegerProperty coins = new SimpleIntegerProperty(10);
    public Pane root;
    public Button menuButton = Buttons.makeButton("Menu",200,100,200,80);
    public Button startButton = Buttons.makeButton("Start",200,100,200,185);
    public Button shopButton = Buttons.makeButton("Shop",200,100,405,80);
    public ArrayList<Computer> comps = new ArrayList<>();
    public DoubleProperty wireLength = new SimpleDoubleProperty();
    public  Port firstport = null;
    Label wirelabel = new Label();
    Label coinslabel = new Label();
    public ArrayList<Packet> packets = new ArrayList<>();


    {
        startButton.setDisable(true);
        shopButton.setDisable(true);
        shopButton.setOnAction(e -> {
            LevelsController.pauseLvl(LevelsController.lvl);
            LevelsController.paused = true;
            Shop.openShop(e);
        });


        root = new Pane();
        wirelabel.textProperty().bind(
                Bindings.concat("Wire left: ", this.wireLength.asString("%.2f"))
        );
        coinslabel.textProperty().bind(
                Bindings.concat("Coins : ", this.coins.asString())
        );

        Labels.styler1(wirelabel);
        Labels.styler1(coinslabel);

        wirelabel.setWrapText(true);
        wirelabel.setScaleX(0.8);  // scales width
        wirelabel.setScaleY(0.8);
        wirelabel.setLayoutX(Main.stageWidth / 2  - 200);
        coinslabel.setLayoutY(80);
        coinslabel.setScaleX(0.8);  // scales width
        coinslabel.setScaleY(0.8);
        coinslabel.setLayoutX(Main.stageWidth / 2  - 180);

        Buttons.styler1(menuButton);
        Buttons.styler1(startButton);
        Buttons.styler1(shopButton);
        root.setStyle("-fx-background-color: #0d1b2a;");
        root.getChildren().addAll(menuButton,startButton, wirelabel , coinslabel , shopButton);



    }
    public Scene scene = new Scene(root);
    {
        scene.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.P) {
                LevelsController.paused = !LevelsController.paused;
                if(LevelsController.paused == false){
                    LevelsController.resumelvl(LevelsController.lvl);
                }
                if(LevelsController.paused == true){
                        LevelsController.pauseLvl(LevelsController.lvl);
                }

            }
        });
    }
    {
        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.millis(1000), event -> {

            for(Computer i : this.comps){
                this.startButton.setDisable(false);
                DropShadow glow = new DropShadow(10, Color.web("#00ffff"));
                glow.setSpread(0.2);
                i.shape.setEffect(glow);
                for(Port p : i.ports){
                    if(p.wire == null){
                        this.startButton.setDisable(true);
                        glow = new DropShadow(10, Color.web("#FF0066"));
                        glow.setSpread(0.2);
                        i.shape.setEffect(glow);
                    }
                }
            }

        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

}
