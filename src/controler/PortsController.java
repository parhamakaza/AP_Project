package controler;

import javafx.animation.FadeTransition;
import javafx.beans.property.DoubleProperty;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import model.*;
import model.System;

public class PortsController {
    public static void drawSquarePort(SquarePort port , Pane pane ){

        Rectangle square = new Rectangle(SquarePort.SIZE, SquarePort.SIZE);
        square.setFill(Color.LIMEGREEN);
        square.setStroke(Color.DARKGREEN);
        square.setStrokeWidth(3);

        // Glow effect
       /* DropShadow glow = new DropShadow();
        glow.setColor(Color.LIMEGREEN);
        glow.setOffsetX(0);
        glow.setOffsetY(0);
        glow.setRadius(20);
        square.setEffect(glow);*/



        //Xpositon
        if(port.portType.equals(PortType.OUTPUT)){
            port.x = port.system.x+ (System.WIDTH- (SquarePort.SIZE) /2 );
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





        // Fade Transition for hover effect
        /*
        FadeTransition fadeOut = new FadeTransition(Duration.millis(200), square);
        fadeOut.setToValue(0.6); // 60% opacity when hovered

        FadeTransition fadeIn = new FadeTransition(Duration.millis(200), square);
        fadeIn.setToValue(1.0); // back to full opacity when mouse exits
        */
        // Mouse events
        square.setOnMouseEntered(event -> square.setOpacity(0.8));
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
        triangle.setFill(Color.rgb(255, 255, 0, 0.2)); // semi-transparent yellow fill
        triangle.setStroke(Color.YELLOW); // strong yellow outline
        triangle.setStrokeWidth(4); // thicker line for blueprint effect

        // Optionally add dashed stroke for more "blueprint" feeling
        //triangle.getStrokeDashArray().addAll(10.0, 5.0);

        if(port.portType.equals(PortType.OUTPUT)){
            port.x = port.system.x+ (System.WIDTH );
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
        triangle.setOnMouseEntered(event -> triangle.setOpacity(0.8));
        triangle.setOnMouseExited(event -> triangle.setOpacity(1));



        pane.getChildren().add(triangle);
        triangle.toFront();


    }




}
