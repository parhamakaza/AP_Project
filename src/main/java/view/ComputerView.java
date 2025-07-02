package view;

import controller.SystemController;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import model.Computer;

import service.SceneManager;

import static model.Computer.HEIGHT;
import static model.Computer.WIDTH;

public class ComputerView {
    private Computer computer;

    public Computer getComputer() {
        return computer;
    }

    public Shape getShape() {
        return shape;
    }

    private Shape shape;


    public  ComputerView(Computer computer){
        this.computer = computer;
        Pane root = (Pane) SceneManager. getPrimaryStage().getScene().getRoot();
        double x = computer.x;
        double y = computer.y;

        Rectangle module = new Rectangle(WIDTH , HEIGHT);
        module.setX(0);
        module.setY(0);
        module.setLayoutX(x);
        module.setLayoutY(y);
        this.shape = module;


        module.setArcWidth(10);   // rounded corners
        module.setArcHeight(10);

        module.setFill(Color.web("#6e6e6e"));   // mid‑gray fill
        module.setStroke(Color.web("#4a4a4a")); // slightly darker border
        module.setStrokeWidth(2);

        DropShadow glow = new DropShadow(10, Color.web("#00ffff"));
        glow.setSpread(0.2);
        module.setEffect(glow);

        root.getChildren().add(module);
        module.toBack();


    }
}
