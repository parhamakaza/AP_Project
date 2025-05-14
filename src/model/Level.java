package model;

import controler.LevelsController;
import controler.PortsController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import view.Main;
import view.Menu;

import java.util.ArrayList;


public class Level {
    public  double time = 0;
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
    //public Slider timelineSlider = new Slider(0, 30, 0);
    public Label currentTimeLabel = new Label("Time: 0.0s");
    public Label packetLossLabel = new Label("Packet loss :");
    {

        packetLossLabel.setLayoutX(1200);
        packetLossLabel.setLayoutY(100);
        packetLossLabel.setWrapText(true);

        packetLossLabel.setScaleX(0.7);
        packetLossLabel.setScaleX(0.5);

        Labels.styler1(packetLossLabel);
    }
    Label wirelabel = new Label();
    Label coinslabel = new Label();
    public ArrayList<Packet> packets = new ArrayList<>();
    public boolean[] isDragging = {false};

    {
        /*timelineSlider.setMajorTickUnit(5);
        timelineSlider.setMinorTickCount(4);
        timelineSlider.setShowTickMarks(true);
        timelineSlider.setShowTickLabels(true);
        timelineSlider.setBlockIncrement(1);
        timelineSlider.setLayoutX(900);
        timelineSlider.setLayoutY(100);*/
        currentTimeLabel.setLayoutX(800);
        Labels.styler1(currentTimeLabel);

        /*timelineSlider.setOnMousePressed(event -> isDragging[0] = true);
        timelineSlider.setOnMouseReleased(event -> isDragging[0] = false);


        // Handle slider drag
        timelineSlider.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
            double value = timelineSlider.getValue();
            time = value;
            currentTimeLabel.setText(String.format("Time: %.1fs", value));
        });

        // Optional: also update time on click
        timelineSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            currentTimeLabel.setText(String.format("Time: %.1fs", newVal.doubleValue()));
        });*/




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
        wirelabel.setLayoutX(1200);
        wirelabel.setScaleX(0.8);  // scales width
        wirelabel.setScaleY(0.8);
        coinslabel.setLayoutY(103);
        coinslabel.setLayoutX(173);
        coinslabel.setScaleX(0.75);  // scales width
        coinslabel.setScaleY(0.8);

        Buttons.styler1(menuButton);
        Buttons.styler1(startButton);
        Buttons.styler1(shopButton);
        root.setStyle("-fx-background-color: #0d1b2a;");
        root.getChildren().addAll(menuButton,startButton, wirelabel , coinslabel , shopButton ,currentTimeLabel , packetLossLabel);



    }
    public Scene scene = new Scene(root);
    {
        scene.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.P) {
                LevelsController.paused = !LevelsController.paused;
                if(!LevelsController.paused){
                    LevelsController.resumelvl(LevelsController.lvl);
                }
                if(LevelsController.paused ){
                        LevelsController.pauseLvl(LevelsController.lvl);
                }

            }
        });
    }
    {
        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.millis(1000), event -> {

            boolean a = true;

            DropShadow glow = new DropShadow();
            for(Computer i : this.comps){
                boolean b = LevelsController.compIsReady(i);
                if( b ){
                    glow = new DropShadow(10, Color.web("#00ffff")); //cyan
                    glow.setSpread(0.2);
                    i.shape.setEffect(glow);
                    i.ready = true;
                }else{
                    glow = new DropShadow(10, Color.web("#FF0066")); // red
                    glow.setSpread(0.2);
                    i.shape.setEffect(glow);
                    i.ready = false;
                }
                a = a & b;
            }
            if(a){
                startButton.setDisable(false);
            }else {
                startButton.setDisable(true);
            }


        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }



}
