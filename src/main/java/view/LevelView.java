
package view;
import controller.ComputerController;
import manager.GameLoopManager;
import manager.LevelManager;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import model.computer.Computer;
import model.Level;

import static service.SceneManager.showMenuView;

public class LevelView {
    private final BorderPane root; // Changed from Pane to BorderPane
    private final Scene scene;
    private final Pane gamePane; // A dedicated pane for the game elements (computers, packets)
    public boolean[] isDragging = {false};

    // UI Components for the HUD
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
        this.root = new BorderPane();
        this.root.setStyle("-fx-background-color: #0d1b2a;");

        // The gamePane is where computers, wires, and packets will be drawn.
        // It sits in the center of the BorderPane, underneath the HUD.
        this.gamePane = new Pane();
        this.root.setCenter(gamePane);

        this.scene = new Scene(root, Main.STAGE_WIDTH, Main.STAGE_HEIGHT);

        scene.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.P) {
                GameLoopManager.paused = !GameLoopManager.paused;
                GameLoopManager.pauseAndResume(GameLoopManager.paused);
            }
        });

        // --- Create all UI components ---
        menuButton = UI.createHUDButton("Menu");
        menuButton.setOnAction(e->{showMenuView();
        GameLoopManager.pauseAndResume(true);
        timeline.stop();
        LevelManager.reset();
        });

        startButton = UI.createHUDButton("Start");
        startButton.setOnAction(e-> GameLoopManager.start());
        shopButton = UI.createHUDButton("Shop");
        shopButton.setOnAction(e-> Shop.openShop(e));




        timelineSlider = new Slider(0, 20, 0);
        UI.styleSlider(timelineSlider);
        timelineSlider.setPrefWidth(400);


        currentTimeLabel = UI.createHUDLabel("Time: 0.0s");
        packetLossLabel = UI.createHUDLabel("Packet Loss: 0.0%");
        wireLabel = UI.createHUDLabel("Wire Left: 0.0");
        coinsLabel = UI.createHUDLabel("Coins: 10");


        timelineSlider.setMajorTickUnit(5);
        timelineSlider.setMinorTickCount(4);
        timelineSlider.setShowTickMarks(true);
        timelineSlider.setShowTickLabels(true);
        timelineSlider.setBlockIncrement(1);
        timelineSlider.setLayoutX(860);
        timelineSlider.setLayoutY(120);
        timelineSlider.setScaleX(1.5);
        timelineSlider.setScaleY(1.2);


        timelineSlider.setOnMousePressed(event -> isDragging[0] = true);
        timelineSlider.setOnMouseReleased(event -> isDragging[0] = false);


        // Handle slider drag
        timelineSlider.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
            int value = (int)timelineSlider.getValue();
            //currentTime = value;
            currentTimeLabel.setText("Time: " + value);
        });

        timelineSlider.setOnMouseReleased(e -> {
            //LevelManager.resetMyLevel();
            int value = (int) timelineSlider.getValue();
            //LevelsController.travelInTime(value);
        });
        // --- Build the HUD layout ---
        bind(levelModel);
        buildHUD();

        // --- Bind UI components to the model's data ---
        //bindToModel(levelModel);
    }

    private void buildHUD() {
        // --- Left Section: Game Controls ---
        HBox leftControls = new HBox(15, menuButton, startButton, shopButton, coinsLabel);
        leftControls.setAlignment(Pos.CENTER_LEFT);
        leftControls.setPadding(new Insets(10, 20, 10, 20));

        // --- Center Section: Timeline ---
        VBox centerTimeline = new VBox(5, currentTimeLabel, timelineSlider);
        centerTimeline.setAlignment(Pos.CENTER);
        centerTimeline.setPadding(new Insets(10, 20, 10, 20));

        // --- Right Section: Game Stats ---
        VBox rightStats = new VBox(5, wireLabel, packetLossLabel);
        rightStats.setAlignment(Pos.CENTER_RIGHT);
        rightStats.setPadding(new Insets(10, 20, 10, 20));

        // --- Top HUD Container ---
        // An HBox to hold the left, center, and right sections.
        HBox topHud = new HBox();
        topHud.setStyle("-fx-background-color: #0d1b2a99; -fx-border-color: #00ffff55; -fx-border-width: 0 0 2 0;");

        // Use a spacer region to push the left and right content to the edges
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        topHud.getChildren().addAll(leftControls, spacer, centerTimeline, rightStats);

        // Add the completed HUD to the top of the BorderPane
        this.root.setTop(topHud);
    }


    private void bind(Level lvl) {
        Timeline timeline = new Timeline();
        this.timeline = timeline;


        KeyFrame keyFrame = new KeyFrame(Duration.millis(100), event -> {

            boolean a = true;

            DropShadow glow;
            for (Computer i : lvl.comps) {
                boolean b = i.compIsReady();
                if (b) {
                    glow = new DropShadow(10, Color.web("#00ffff")); //cyan
                    glow.setSpread(0.2);
                    ComputerController.computerMap.get(i).getShape().setEffect(glow);
                    i.ready = true;
                } else {
                    glow = new DropShadow(10, Color.web("#FF0066")); // red
                    glow.setSpread(0.2);
                    ComputerController.computerMap.get(i).getShape().setEffect(glow);

                    i.ready = false;
                }
                a = a & b;
            }

            startButton.setDisable(!a);
            currentTimeLabel.setText(String.valueOf(lvl.currentTime));
            wireLabel.setText(String.valueOf(lvl.wireLength));

            try {
                packetLossLabel.setText((lvl.lostPackets / lvl.generatedPackets) * 100 + "%");
            } catch (ArithmeticException e) {

            }


        coinsLabel.setText(String.valueOf(lvl.coins));
        });


        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();


    }

    // --- Getters for the controller to attach event handlers ---
    public Scene getScene() { return scene; }
    public Button getMenuButton() { return menuButton; }
    public Button getStartButton() { return startButton; }
    public Button getShopButton() { return shopButton; }
    public Slider getTimelineSlider() { return timelineSlider; }

    // The controller will add game elements to this pane, not the root.
    public Pane getGamePane() { return gamePane; }
}
