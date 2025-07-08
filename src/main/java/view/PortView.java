package view;

import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import model.computer.Computer;
import model.port.Port;
import model.port.PortType;
import model.port.SquarePort;
import model.port.TrianglePort;
import service.SceneManager;



public class PortView implements Drawable{
    private Shape shape;

    public Port getPort() {
        return port;
    }

    private Port port;

    public Shape getShape() {
        return shape;
    }

    @Override
    public void draw() {
        Port port = this.getPort();
        if (port instanceof SquarePort) {
            this.shape = Drawable.createRectangleAsPolygon();
        } else if (port instanceof TrianglePort) {
            this.shape = Drawable.createTriangleAsPolygon();
        }

        if (port.portType.equals(PortType.OUTPUT)) {
            port.x = port.computer.x + (Computer.WIDTH);
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


    }



    public PortView(Port port) {
        this.port = port;
        Pane root = SceneManager.getCurrentPane();
        draw();
        shape.toFront();
        root.getChildren().add(shape);
    }

}

