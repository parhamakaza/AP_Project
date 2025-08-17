package view;

import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import model.computer.Computer;

import model.port.Port;
import model.port.PortType;
import model.wire.Wire;
import service.SceneManager;

import static controller.ComponentsController.TheComponentsController;
import static manager.LevelManager.lvl;
import static model.computer.Computer.HEIGHT;
import static model.computer.Computer.WIDTH;
public class ComputerView implements Drawable {

    private static final double BOUNDARY_MAX= 50;

    private Computer computer;
    private Shape shape;
    private StackPane container;
    public Label label;


    // --- NEW: Member variables to store drag offset ---
    private double dragOffsetX;
    private double dragOffsetY;

    // --- Constants for clarity ---



    public Computer getComputer() {

        return computer;
    }

    public Shape getShape() {
        return shape;
    }


    @Override
    public void draw() {
        // Create a container to hold both the rectangle and the label
        StackPane container = new StackPane();

        // Your original Rectangle code is perfect
        Rectangle module = new Rectangle(WIDTH, HEIGHT);
        module.setArcWidth(10);
        module.setArcHeight(10);
        module.setFill(Color.web("#6e6e6e"));
        module.setStroke(Color.web("#4a4a4a"));
        module.setStrokeWidth(2);

        // Create the Label to go on top
        String labelText = computer.computerType.toString() + "\n" + computer.packets.size();
        Label computerLabel = new Label(labelText);
        computerLabel.setTextFill(Color.GOLD);
        computerLabel.setFont(new Font("Arial Bold", 10));
        this.label = computerLabel;

        // Add both the rectangle and the label to the container.
        container.getChildren().addAll(module, computerLabel);
        container.setMinWidth(WIDTH);
        container.setMaxWidth(WIDTH);
        container.setPrefWidth(WIDTH);
        container.setMinHeight(HEIGHT);
        container.setMaxHeight(HEIGHT);
        container.setPrefHeight(HEIGHT);

        // Position the entire container
        container.setLayoutX(this.computer.x);
        container.setLayoutY(this.computer.y);

        this.shape = module;
        this.container = container; // Assign to member variable

        setIndicator();
        makeDraggable(); // <-- NEW: Call the method to add mouse events

        SceneManager.addComponent(container);
    }


    private void makeDraggable() {

        container.setOnMousePressed(event -> {
            if(lvl.getShop().isSisyphus()){
            dragOffsetX = event.getSceneX() - container.getLayoutX();
            dragOffsetY = event.getSceneY() - container.getLayoutY();
            container.setCursor(Cursor.MOVE);
            container.toFront();
            }// Bring to front when dragging
        });


        container.setOnMouseDragged(event -> {
            if(lvl.getShop().isSisyphus()) {
                try {
                    double newX = event.getSceneX() - dragOffsetX;
                    double newY = event.getSceneY() - dragOffsetY;
                    if (Math.abs(newX - computer.getInitialX()) > BOUNDARY_MAX || Math.abs(newY - computer.getInitialY()) > BOUNDARY_MAX) {
                        throw new IllegalStateException("out of radius");
                    }


                    container.setLayoutX(newX);
                    container.setLayoutY(newY);


                    this.computer.x = newX;
                    this.computer.y = newY;

                    for (Port port : computer.ports) {

                        TheComponentsController.getView(port).setPortCordination();
                        if (port.wire != null) {
                            //calculatePortWireLength(port);
                            WireView.linkToPort(port);
                        }
                    }
                } catch (IllegalStateException e) {
                    System.out.println(e.getMessage());

                }
            }

        });


        container.setOnMouseReleased(event -> {
            lvl.getShop().setSisyphus(false);
            container.setCursor(Cursor.HAND);
        });


        container.setOnMouseEntered(event -> {
            container.setCursor(Cursor.HAND);
        });
    }

    private void calculatePortWireLength(Port port){
        WireView wireView = TheComponentsController.getView(port.wire);
        if(port.portType == PortType.OUTPUT){
            outPutWireLength(wireView.getCurves().getFirst());
        }else if(port.portType == PortType.INPUT){
            inPutWireLength(wireView.getCurves().getFirst());
        }


    }
    private void outPutWireLength(QuadCurve quadCurve){


    }
    private void inPutWireLength(QuadCurve quadCurve){

    }


    public void setIndicator() {
        DropShadow glow;
        if (computer.compIsReady()) {
            glow = new DropShadow(10, Color.web("#00ffff"));
        } else {
            glow = new DropShadow(10, Color.web("#FF0066"));
        }
        glow.setSpread(0.2);
        shape.setEffect(glow);
    }

    public static void setIndicator(Computer computer) {
        ComputerView computerView = TheComponentsController.getView(computer);
        computerView.setIndicator();
    }

    public ComputerView(Computer computer) {
        this.computer = computer;
        draw();
    }


}