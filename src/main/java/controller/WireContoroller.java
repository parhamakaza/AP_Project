package controller;

import javafx.event.Event;
import javafx.geometry.Bounds;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import model.*;
import service.AudioManager;

import java.lang.System;


public class WireContoroller {
    private static Line currentLine = new Line();
    private static Port firstPort;


    public static Line drawWires(Wire wire , Pane root){
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
        AudioManager.playConnection();

        return line;
    }

    public static void professionalWiring(Port port){
        Shape shape = port.shape;



        shape.setOnMousePressed((MouseEvent e) -> {
            if(port.wire == null) {
                firstPort = port;
                currentLine = new Line();
                currentLine.setStrokeWidth(3);
                currentLine.setStroke(Color.WHITE);
                currentLine.setStartX(port.centerX());
                currentLine.setStartY(port.centerY());
                currentLine.setEndX(e.getSceneX());
                currentLine.setEndY(e.getSceneY());
                ((Pane) shape.getParent()).getChildren().add(currentLine);
            }else{
                Wire wire = port.wire;
                ((Pane) port.wire.line.getParent()).getChildren().remove(port.wire.line);
                port.wire.line = null;
                wire.sPort.wire =null;
                wire.ePort.wire= null;
                LevelsController.lvl.wireLength.set(LevelsController.lvl.wireLength.get() + wire.length);

            }
        });

        shape.setOnMouseDragged((MouseEvent e) -> {
            if (currentLine != null) {
                currentLine.setEndX(e.getSceneX());
                currentLine.setEndY(e.getSceneY());
                double dx = currentLine.getEndX() - currentLine.getStartX();
                double dy = currentLine.getEndY() - currentLine.getStartY();
                double length = Math.sqrt(dx * dx + dy * dy);
                if(length > LevelsController.lvl.wireLength.get()){
                    currentLine.setStroke(Color.RED);
                }else {
                    currentLine.setStroke(Color.WHITE);
                }

            }
        });

        shape.setOnMouseReleased((MouseEvent e3) -> {

            if (currentLine != null){
                Port p = checkIsIndise(currentLine.getEndX(), currentLine.getEndY(), e3);
                Wire w1 = null;
                try {

                    w1 = new Wire(firstPort, p);
                    w1.line = WireContoroller.drawWires(w1, port.system.root);

                } catch (Exception ex) {

                    try {
                        w1 = new Wire(p , firstPort);
                    w1.line = WireContoroller.drawWires(w1, port.system.root);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }

            }



            ((Pane) shape.getParent()).getChildren().remove(currentLine);
            currentLine = null;
            firstPort = null;

        });



    }
    private static Port checkIsIndise(double x , double y , Event mouseEvent){

        for(Computer i : LevelsController.lvl.comps){
            for (Port p : i.ports){
                Bounds bounds = p.shape.localToScene(p.shape.getBoundsInLocal());
                if(bounds.contains(x, y)){

                    return  p;
                }
            }

        }
        return null;




    }


}
