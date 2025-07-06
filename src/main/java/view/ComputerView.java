package view;

import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import model.computer.Computer;

import service.SceneManager;

import static model.computer.Computer.HEIGHT;
import static model.computer.Computer.WIDTH;

public class ComputerView implements Drawable{
    private Computer computer;

    public Computer getComputer() {
        return computer;
    }

    public Shape getShape() {
        return shape;
    }

    private Shape shape;

    @Override
    public Shape draw() {

        Rectangle module = new Rectangle(WIDTH , HEIGHT);
        module.setX(0);
        module.setY(0);
        module.setLayoutX(this.computer.x);
        module.setLayoutY(this.computer.y);
        module.setArcWidth(10);   // rounded corners
        module.setArcHeight(10);

        module.setFill(Color.web("#6e6e6e"));   // mid‑gray fill
        module.setStroke(Color.web("#4a4a4a")); // slightly darker border
        module.setStrokeWidth(2);

        DropShadow glow = new DropShadow(10, Color.web("#00ffff"));
        glow.setSpread(0.2);
        module.setEffect(glow);
        return module;
    }

    public  ComputerView(Computer computer){
        this.computer = computer;
        Pane root = (Pane) SceneManager. getPrimaryStage().getScene().getRoot();


        this.shape = draw();

        root.getChildren().add(shape);
        shape.toBack();


    }
}
