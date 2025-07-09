package manager;

import controller.PortController;
import controller.WireController;
import javafx.geometry.Bounds;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.Shape;
import model.port.Port;
import model.wire.Wire;
import service.SceneManager;
import view.port.PortView;
import view.WireView;

public class WireManager {
    private static Line currentLine = new Line();
    private static Port firstPort;

    public static void professionalWiring(PortView portView){
        Shape shape = portView.getShape();
        Port port = portView.getPort();
        Pane pane = SceneManager.getCurrentPane();




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
                pane.getChildren().add(currentLine);
            } else {
                Wire wire = port.wire;
                WireView wireView = WireController.wireMap.get(wire);
                for (QuadCurve qc : WireController.wireMap.get(wire).getCurves()) {
                    pane.getChildren().remove(qc);
                }

                wireView.setCurves(null);
                wire.sPort.wire = null;
                wire.ePort.wire = null;
                LevelManager.lvl.wireLength = LevelManager.lvl.wireLength + wire.length;
            }
        });

        shape.setOnMouseDragged((MouseEvent e) -> {
            if (currentLine != null) {
                currentLine.setEndX(e.getSceneX());
                currentLine.setEndY(e.getSceneY());
                double dx = currentLine.getEndX() - currentLine.getStartX();
                double dy = currentLine.getEndY() - currentLine.getStartY();
                double length = Math.sqrt(dx * dx + dy * dy);
                if(length > LevelManager.lvl.wireLength){
                    currentLine.setStroke(Color.RED);
                }else {
                    currentLine.setStroke(Color.WHITE);
                }

            }
        });

        shape.setOnMouseReleased((MouseEvent e3) -> {
            if (currentLine != null){
                Port p = checkIsIndise(currentLine.getEndX(), currentLine.getEndY());
                try {

                    WireController.makeWire(new Wire(firstPort, p));



                } catch (Exception ex) {

                    try {
                        WireController.makeWire(new Wire(p , firstPort));

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

    private static Port checkIsIndise(double x , double y){
            for (PortView p : PortController.portMap.values()){
                Bounds bounds = p.getShape().localToScene(p.getShape().getBoundsInLocal());
                if(bounds.contains(x, y)){

                    return  p.getPort();
                }
            }


        return null;




    }


    public static double lengthcounter(Wire wire){
        double x1 = wire.startX;
        double y1 = wire.startY;
        double x2 = wire.endX;
        double y2 = wire.endY;
        double l = Math.abs(x1 - x2);
        double h = Math.abs(y2 - y1);

        return Math.sqrt((l * l) + (h * h));


    }
}
