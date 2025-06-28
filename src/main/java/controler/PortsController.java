package controler;

import javafx.event.Event;
import javafx.geometry.Bounds;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import model.*;
import model.Gsystem;

import static controler.WireContoroller.professionalWiring;


public class PortsController {
    public static void drawSquarePort(SquarePort port , Pane pane ){

        Rectangle square = new Rectangle(SquarePort.SIZE, SquarePort.SIZE);
        square.setFill(Color.LIMEGREEN);
        square.setStroke(Color.DARKGREEN);
        square.setStrokeWidth(3);
        port.shape = square;




        //Xpositon
        if(port.portType.equals(PortType.OUTPUT)){
            port.x = port.system.x+ (Gsystem.WIDTH- (SquarePort.SIZE) /2 );
            square.setLayoutX(port.x);

        }else if(port.portType.equals(PortType.INPUT)){
            port.x =port.system.x - (SquarePort.SIZE) /2;
            square.setLayoutX(port.x);
        }



        switch(port.portNum) {
            case 1:
               port.y = 15 + port.system.y;
                break;
            case 2:
                port.y = 65+ port.system.y;

                break;
            case 3:
                port.y = 115+ port.system.y;
                break;


        }
        square.setLayoutY(port.y);
        professionalWiring(port);






        // Mouse events
        square.setOnMouseEntered(event -> square.setOpacity(0.5));
        square.setOnMouseExited(event -> square.setOpacity(1));


        pane.getChildren().add(square);
        square.toFront();

    }
    public static void drawTrianglePort(TrianglePort port , Pane pane){
        Polygon triangle = new Polygon();
        triangle.getPoints().addAll(
                0.0, 0.0,    // Peak at (0,0)
                10.0,20.0,// Bottom right point (slanted)
                -10.0,20.0       // Bottom left point
        );
        port.shape = triangle;
        triangle.setFill(Color.rgb(255, 255, 0, 0.2)); // semi-transparent yellow fill
        triangle.setStroke(Color.YELLOW);
        triangle.setStrokeWidth(4);

        if(port.portType.equals(PortType.OUTPUT)){
            port.x = port.system.x+ (Gsystem.WIDTH );
            triangle.setLayoutX(port.x);

        }else if(port.portType.equals(PortType.INPUT)){
            port.x =port.system.x ;
            triangle.setLayoutX(port.x);
        }



        switch(port.portNum) {

            case 1:
                port.y = 15 + port.system.y;
                break;

            case 2:
                port.y = 65+ port.system.y;
                break;

            case 3:
                port.y = 115+ port.system.y;
                break;

        }

        triangle.setLayoutY(port.y);
        triangle.setOnMouseEntered(event -> triangle.setOpacity(0.5));
        triangle.setOnMouseExited(event -> triangle.setOpacity(1));
        professionalWiring(port);


        pane.getChildren().add(triangle);
        triangle.toFront();


    }







}
