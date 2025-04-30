package controler;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import model.*;
import view.*;

import java.lang.System;


public class WireContoroller {

    public static void drawWires(Wire wire , Pane root){
        double x1 = wire.startX;
        double y1 = wire.startY;
        double x2 = wire.endX;
        double y2 = wire.endY;
        Line line = new Line(x1, y1, x2, y2);
        line.setStrokeWidth(3);
        if(wire.type.equals(WireType.SQUARE)){
            line.setStroke(Color.GREEN);
        } else {
            line.setStroke(Color.YELLOW);
        }

        root.getChildren().add(line);
        line.toFront();


    }


}
