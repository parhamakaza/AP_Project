package view;

import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import model.computer.Computer;

import service.SceneManager;

import static model.computer.Computer.HEIGHT;
import static model.computer.Computer.WIDTH;

public  class ComputerView implements Drawable  {
    private Computer computer;
    private Shape shape;
    private StackPane container;

    public Computer getComputer() {
        return computer;
    }

    public Shape getShape() {
        return shape;
    }

    public  ComputerView(){

    }




        @Override
        public void draw() { // Change #1: Method now returns a Parent

            // Create a container to hold both the rectangle and the label
            StackPane container = new StackPane();

            // Your original Rectangle code is perfect
            Rectangle module = new Rectangle(WIDTH, HEIGHT);
            // Note: X and Y are not needed here, as the StackPane handles the layout
            module.setArcWidth(10);
            module.setArcHeight(10);
            module.setFill(Color.web("#6e6e6e"));
            module.setStroke(Color.web("#4a4a4a"));
            module.setStrokeWidth(2);

            DropShadow glow = new DropShadow(1, Color.web("#00ffff"));
            glow.setSpread(0.2);
            module.setEffect(glow);

            // Create the Label to go on top
            Label serverLabel = new Label(computer.computerType.toString()); // Or get text from this.computer.getName()
            serverLabel.setTextFill(Color.GOLD);      // Make the text easy to see
            serverLabel.setFont(new Font("Arial Bold", 10));

            // Add both the rectangle and the label to the container.
            // StackPane will automatically center the label on top of the rectangle.
            container.getChildren().addAll(module, serverLabel);
            container.setMinWidth(WIDTH);
            container.setMaxWidth(WIDTH);
            container.setPrefWidth(WIDTH);

            container.setMinHeight(HEIGHT);
            container.setMaxHeight(HEIGHT);
            container.setPrefHeight(HEIGHT);
            // Change #2: Position the *entire container*, not just the rectangle

            module.setLayoutX(this.computer.x);
            module.setLayoutY(this.computer.y);
            container.setLayoutX(this.computer.x);
            container.setLayoutY(this.computer.y);
            this.shape = module;
            this.container = container;
            SceneManager.addComponent(container);



        }


    public ComputerView(Computer computer){
        this.computer = computer;
        draw();




    }
}
