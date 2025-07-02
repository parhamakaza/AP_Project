package view;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import model.*;
import service.SceneManager;



public class PortView {
    private Shape shape;

    public Port getPort() {
        return port;
    }

    private Port port;

    public Shape getShape() {
        return shape;
    }

    private Shape draw(Port port) {
        if (port instanceof SquarePort) {
            this.shape = createRectangleAsPolygon();
        } else if (port instanceof TrianglePort) {
            this.shape = createTriangleAsPolygon();
        }

        if (port.portType.equals(PortType.OUTPUT)) {
            port.x = port.computer.x + (Gsystem.WIDTH);
            shape.setLayoutX(port.x);

        } else if (port.portType.equals(PortType.INPUT)) {
            port.x = port.computer.x;
            shape.setLayoutX(port.x);
        }


        switch (port.portNum) {

            case 1:
                port.y = 15 + port.computer.y;
                break;

            case 2:
                port.y = 65 + port.computer.y;
                break;

            case 3:
                port.y = 115 + port.computer.y;
                break;

        }

        shape.setLayoutY(port.y);
        shape.setOnMouseEntered(event -> shape.setOpacity(0.5));
        shape.setOnMouseExited(event -> shape.setOpacity(1));
        return shape;

    }



    public PortView(Port port) {
        this.port = port;
        Pane pane = (Pane) SceneManager.getPrimaryStage().getScene().getRoot();
        this.shape = this.draw(port);
        shape.toFront();
        pane.getChildren().add(shape);
    }

    public static Shape createRectangleAsPolygon() {
        // The width and height are now defined inside the method.
        double width = Port.SIZE;
        double height = Port.SIZE;

        // Create a new Polygon object.
        Polygon rectangle = new Polygon();

        // Calculate the coordinates for the four corners of the rectangle.
        double topLeftX = -width / 2;
        double topLeftY = 0;
        double topRightX = width / 2;
        double topRightY = 0;
        double bottomRightX = width / 2;
        double bottomRightY = height;
        double bottomLeftX = -width / 2;
        double bottomLeftY = height;

        // Add all the calculated points to the polygon's list of vertices.
        rectangle.getPoints().addAll(
                topLeftX, topLeftY,
                topRightX, topRightY,
                bottomRightX, bottomRightY,
                bottomLeftX, bottomLeftY
        );
        rectangle.setFill(Color.rgb(255, 255, 0, 0.2));


// Add a thick, black border
        rectangle.setStroke(Color.GREEN);
        rectangle.setStrokeWidth(4);


        return rectangle;
    }

    public static Shape createTriangleAsPolygon() {
        Polygon triangle = new Polygon();
        triangle.getPoints().addAll(
                0.0, 0.0,    // Peak at (0,0)
                10.0, 20.0,// Bottom right point (slanted)
                -10.0, 20.0       // Bottom left point
        );

        triangle.setFill(Color.rgb(255, 255, 0, 0.2)); // semi-transparent yellow fill
        triangle.setStroke(Color.YELLOW);
        triangle.setStrokeWidth(4);
        return triangle;
    }

}

