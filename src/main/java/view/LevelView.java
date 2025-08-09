package view;

import controller.ComponentsController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import manager.LevelManager;
import model.computer.Computer;
import model.Level;
import saveAndLoad.Save;

import static controller.ComponentsController.TheComponentsController;
import static manager.LevelManager.theLevelManager;
import static service.SceneManager.showMenuView;

public class LevelView {

    // This constant defines the height of the top UI bar.
    // Use this to offset the Y-position of your game objects.
    public static final double HUD_HEIGHT = 65;

    private final Pane root; // The single pane for both the HUD and game components.
    private final Scene scene;
    public boolean[] isDragging = {false};

    // UI Components
    private final Button menuButton;
    private final Button startButton;
    private final Button shopButton;
    private final Slider timelineSlider;
    private final Label currentTimeLabel;
    private final Label packetLossLabel;
    private final Label wireLabel;
    private final Label coinsLabel;
    private final Level level;
    public Timeline timeline;

    public Level getLevel() {
        return level;
    }

    public LevelView(Level levelModel) {
        this.level = levelModel;
        this.root = new Pane();
        this.root.setStyle("-fx-background-color: #0d1b2a;");

        this.scene = new Scene(root, Main.STAGE_WIDTH, Main.STAGE_HEIGHT);

        scene.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.P) {
                theLevelManager.paused = !theLevelManager.paused;
                theLevelManager.pauseAndResume(theLevelManager.paused);
            }
        });


        menuButton = UI.createHUDButton("Menu");
        menuButton.setOnAction(e -> {
            timeline.stop();
            theLevelManager.stop();
            Save.stop();
            showMenuView();

        });

        startButton = UI.createHUDButton("Start");
        startButton.setOnAction(e -> theLevelManager.start());

        shopButton = UI.createHUDButton2("Shop");
        shopButton.setOnAction(e -> Shop.openShop(e));

        timelineSlider = new Slider(0, 60, levelModel.getTime()); // <-- MODIFIED
        UI.styleSlider(timelineSlider);
        timelineSlider.setPrefWidth(400);

        currentTimeLabel = UI.createHUDLabel("Time: 0.0s");
        packetLossLabel = UI.createHUDLabel("Packet Loss: 0.0%");
        wireLabel = UI.createHUDLabel("Wire Left: 0.0");
        coinsLabel = UI.createHUDLabel("Coins: 10");




        timelineSlider.setOnMousePressed(event -> isDragging[0] = true);
        timelineSlider.setOnMouseReleased(event -> {
            isDragging[0] = false;


        });

        timelineSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (isDragging[0]) {
                currentTimeLabel.setText("Time: " + String.format("%.1f", newVal));
            }
        });

        // --- Build the HUD and bind data ---
        buildHUD();
        bind(levelModel);
    }

    private void buildHUD() {
        // --- Left Section: Game Controls ---
        HBox leftControls = new HBox(15, menuButton, startButton, shopButton, coinsLabel);
        leftControls.setAlignment(Pos.CENTER_LEFT);

        // --- Center Section: Timeline ---
        VBox centerTimeline = new VBox(5, currentTimeLabel, timelineSlider);
        centerTimeline.setAlignment(Pos.CENTER);

        // --- Right Section: Game Stats ---
        VBox rightStats = new VBox(5, wireLabel, packetLossLabel);
        rightStats.setAlignment(Pos.CENTER_RIGHT);

        // --- Top HUD Container ---
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox topHud = new HBox(leftControls, spacer, centerTimeline, rightStats);
        topHud.setPadding(new Insets(10));
        topHud.setAlignment(Pos.CENTER);
        topHud.setStyle("-fx-background-color: #0d1b2a99; -fx-border-color: #00ffff55; -fx-border-width: 0 0 2 0;");

        // This makes the HUD stretch across the full width of the window.
        topHud.prefWidthProperty().bind(this.root.widthProperty());
        topHud.setMinHeight(HUD_HEIGHT);

        this.root.getChildren().add(topHud);
    }

    private void bind(Level lvl) {
        this.timeline = new Timeline();

        KeyFrame keyFrame = new KeyFrame(Duration.millis(100), event -> {
            boolean allComputersReady = true;

            for (Computer computer : lvl.comps) {
                boolean isReady = computer.compIsReady();
                Color glowColor = isReady ? Color.web("#00ffff") : Color.web("#FF0066");
                DropShadow glow = new DropShadow(10, glowColor);
                glow.setSpread(0.2);
                ComputerView computerView= TheComponentsController.getView(computer);
                computerView.getShape().setEffect(glow);
                computerView.label.setText(computer.computerType.toString() +"\n" + computer.packets.size());
                computer.ready = isReady;
                if (!isReady) {
                    allComputersReady = false;
                }
            }

                if (!isDragging[0]) {
                    currentTimeLabel.setText("Time: " + String.format("%.1f", lvl.getTime()) + "s"); // <-- MODIFIED
                    timelineSlider.setValue(lvl.getTime()); // <-- MODIFIED
                }





            startButton.setDisable(!allComputersReady);
            wireLabel.setText("Wire: " + String.format("%.1f", theLevelManager.updateWireLength()));

            try {
                double lossPercentage = (lvl.lostPackets / (double) lvl.generatedPackets) * 100;
                packetLossLabel.setText("Loss: " + String.format("%.1f%%", lossPercentage));
            } catch (Exception e) {
                packetLossLabel.setText("Loss: 0.0%");
            }

            coinsLabel.setText("Coins: " + lvl.coins);
        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    // --- Getters for the controller to attach event handlers ---
    public Scene getScene() {
        return scene;
    }

    public Pane getRoot() {
        return root;
    }
}