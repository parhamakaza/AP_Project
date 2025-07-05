package view;


import javafx.event.Event;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.QuadCurve;
import manager.PacketAnimatorManager;
import model.port.SquarePort;
import model.port.TrianglePort;
import model.wire.Wire;
import model.wire.WireType;
import service.AudioManager;
import service.SceneManager;

import java.util.List;


public class WireView {
    private Wire wire;
    private List<QuadCurve> curves;

    public void setCurves(List<QuadCurve> curves) {
        this.curves = curves;
    }

    public List<QuadCurve> getCurves() {
        return curves;
    }

    public void setWire(Wire wire) {
        this.wire = wire;
    }

    public Wire getWire() {
        return wire;
    }











    public  WireView(Wire wire){
        Pane root = (Pane) SceneManager.getPrimaryStage().getScene().getRoot();
        this.wire = wire;

        Point2D p0 = new Point2D(wire.startX, wire.startY);  // Start Point
        Point2D p3 = new Point2D(wire.endX, wire.endY);

        // P1 = P0 + (1/3) * (P3 - P0)
        Point2D p1 = new Point2D(p0.getX() + (p3.getX() - p0.getX()) / 3, p0.getY() + (p3.getY() - p0.getY()) / 3);
        // P2 = P0 + (2/3) * (P3 - P0)
        Point2D p2 = new Point2D(p0.getX() + 2 * (p3.getX() - p0.getX()) / 3, p0.getY() + 2 * (p3.getY() - p0.getY()) / 3);

        // 3. Define the three curves using these four points.
        QuadCurve curve1 = new QuadCurve(p0.getX(), p0.getY(), 0, 0, p1.getX(), p1.getY());
        QuadCurve curve2 = new QuadCurve(p1.getX(), p1.getY(), 0, 0, p2.getX(), p2.getY());
        QuadCurve curve3 = new QuadCurve(p2.getX(), p2.getY(), 0, 0, p3.getX(), p3.getY());

         curves = List.of(curve1, curve2, curve3);

        // 4. Set the control point for each curve to its own midpoint to make it straight.
        setControlToMidpoint(curve1);
        setControlToMidpoint(curve2);
        setControlToMidpoint(curve3);

        // Style the curves
        curves.forEach(curve -> {
            curve.setStroke(Color.NAVY);
            curve.setStrokeWidth(4);
            curve.setFill(null);
            curve.setStrokeWidth(4);
            if (wire.sPort instanceof SquarePort) {
                curve.setStroke(Color.GREEN);
            } else if (wire.sPort instanceof TrianglePort) {
                curve.setStroke(Color.YELLOW);
            }
            curve.setOnMouseDragged(event -> {
                // 1. Get the specific curve that was the source of the drag event.
                QuadCurve draggedCurve = (QuadCurve) event.getSource();

                // 2. Get the current mouse coordinates.
                double controlX = event.getX();
                double controlY = event.getY();
                int i = curves.indexOf(draggedCurve);



                draggedCurve.setControlX(controlX);
                draggedCurve.setControlY(controlY);
                smoother(i ,draggedCurve);

            });
            curve.setOnMouseClicked(e -> wire.curved++);
            root.getChildren().add(curve);
        });




        AudioManager.playConnection();

    }
    private void setControlToMidpoint(QuadCurve curve) {
        double controlX = (curve.getStartX() + curve.getEndX()) / 2;
        double controlY = (curve.getStartY() + curve.getEndY()) / 2;
        curve.setControlX(controlX);
        curve.setControlY(controlY);
    }
    private double avarge(double a , double b){
        return (a + b) /2;
    }
    private void smoother(int n , QuadCurve draggedCurve ){
        switch (n) {
            case 0:
                // Code to execute if n is 0
            {
                QuadCurve curve = curves.get(n + 1);
                double x = avarge(curve.getControlX(), draggedCurve.getControlX());
                double y = avarge(curve.getControlY(), draggedCurve.getControlY());
                curve.setStartX(x);
                curve.setStartY(y);
                draggedCurve.setEndX(x);
                draggedCurve.setEndY(y);
            }
                break; // Important: prevents fall-through to the next case

            case 1:
                // Code to execute if n is 1
            {
                QuadCurve curve = curves.get(n + 1);
                double x = avarge(curve.getControlX(), draggedCurve.getControlX());
                double y = avarge(curve.getControlY(), draggedCurve.getControlY());
                curve.setStartX(x);
                curve.setStartY(y);
                draggedCurve.setEndX(x);
                draggedCurve.setEndY(y);
            }{
                QuadCurve curve = curves.get(n - 1 );
                double x = avarge(curve.getControlX(), draggedCurve.getControlX());
                double y = avarge(curve.getControlY(), draggedCurve.getControlY());
                curve.setEndX(x);
                curve.setEndY(y);
                draggedCurve.setStartX(x);
                draggedCurve.setStartY(y);

            }



                break;

            case 2:
                // Code to execute if n is 2
            {
                QuadCurve curve = curves.get(n - 1 );
                double x = avarge(curve.getControlX(), draggedCurve.getControlX());
                double y = avarge(curve.getControlY(), draggedCurve.getControlY());
                curve.setEndX(x);
                curve.setEndY(y);
                draggedCurve.setStartX(x);
                draggedCurve.setStartY(y);

            }
                break;

            default:
                // Code to execute if n is not 0, 1, or 2

                break;
        }

    }



}
