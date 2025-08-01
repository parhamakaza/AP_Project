package view;


import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.QuadCurve;
import manager.packets.PacketManager;
import model.wire.Wire;
import service.AudioManager;
import service.SceneManager;

import java.util.ArrayList;
import java.util.List;

import static manager.GameManager.lvl;


public class WireView {
    private Wire wire;
    private List<QuadCurve> curves;
    private final List<Point2D> initialCurvesControllers = new ArrayList<>();

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

    public WireView(Wire wire) {
        this.wire = wire;

        Point2D p0 = new Point2D(wire.startX, wire.startY);  // Start Point
        Point2D p3 = new Point2D(wire.endX, wire.endY);
        Point2D p1 = new Point2D(p0.getX() + (p3.getX() - p0.getX()) / 3, p0.getY() + (p3.getY() - p0.getY()) / 3);
        Point2D p2 = new Point2D(p0.getX() + 2 * (p3.getX() - p0.getX()) / 3, p0.getY() + 2 * (p3.getY() - p0.getY()) / 3);


        QuadCurve curve1 = new QuadCurve(p0.getX(), p0.getY(), 0, 0, p1.getX(), p1.getY());
        QuadCurve curve2 = new QuadCurve(p1.getX(), p1.getY(), 0, 0, p2.getX(), p2.getY());
        QuadCurve curve3 = new QuadCurve(p2.getX(), p2.getY(), 0, 0, p3.getX(), p3.getY());

        curves = List.of(curve1, curve2, curve3);

        initialCurvesControllers.addAll(List.of(setControlToMidpoint(curve1), setControlToMidpoint(curve2), setControlToMidpoint(curve3)));

        // Style the curves
        curves.forEach(curve -> {
            paintCurve(curve);
            curve.setOnMousePressed(mouseEvent -> {
                if(!wire.isCurved()){
                    lvl.coins--;
                    wire.setCurved();
                }
                System.out.println("s");
            });

            curve.setOnMouseDragged(event -> {
                // 1. Get the specific curve that was the source of the drag event.
                QuadCurve draggedCurve = (QuadCurve) event.getSource();
                int i = curves.indexOf(draggedCurve);

                wire.length = PacketManager.calculateWireLength(curves);

                // 2. Get the current mouse coordinates.
                double controlX = event.getX();
                double controlY = event.getY();
                if (distance(new Point2D(controlX, controlY), initialCurvesControllers.get(i)) <= 120) {


                    draggedCurve.setControlX(controlX);
                    draggedCurve.setControlY(controlY);
                    smoother(i, draggedCurve);
                }

            });
            SceneManager.addComponent(curve);
        });


        AudioManager.playConnection();

    }

    private Point2D setControlToMidpoint(QuadCurve curve) {
        double controlX = average(curve.getStartX() , curve.getEndX());
        double controlY = average(curve.getStartY() , curve.getEndY());
        curve.setControlX(controlX);
        curve.setControlY(controlY);
        return new Point2D(controlX, controlY);
    }

    private double average(double a, double b) {
        return (a + b) / 2;
    }

    private void smoother(int n, QuadCurve draggedCurve) {
        switch (n) {
            case 0 -> smoothAfter(n, draggedCurve);
            case 1 -> {
                smoothBefore(n, draggedCurve);
                smoothAfter(n, draggedCurve);
            }
            case 2 -> smoothBefore(n, draggedCurve);
        }

    }

    private void smoothBefore(int n, QuadCurve draggedCurve) {
        QuadCurve curve = curves.get(n - 1);
        double x = average(curve.getControlX(), draggedCurve.getControlX());
        double y = average(curve.getControlY(), draggedCurve.getControlY());
        curve.setEndX(x);
        curve.setEndY(y);
        draggedCurve.setStartX(x);
        draggedCurve.setStartY(y);

    }

    private void smoothAfter(int n, QuadCurve draggedCurve) {
        QuadCurve curve = curves.get(n + 1);
        double x = average(curve.getControlX(), draggedCurve.getControlX());
        double y = average(curve.getControlY(), draggedCurve.getControlY());
        curve.setStartX(x);
        curve.setStartY(y);
        draggedCurve.setEndX(x);
        draggedCurve.setEndY(y);
    }

    private double distance(Point2D p1, Point2D p2) {
        return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
    }

    private void paintCurve(QuadCurve curve) {

        curve.setStrokeWidth(4);
        curve.setFill(null);

        switch (wire.type) {
            case SQUARE   -> curve.setStroke(Color.GREEN);
            case TRIANGLE -> curve.setStroke(Color.YELLOW);
            case MATIC    -> curve.setStroke(Color.GRAY);
            default       -> curve.setStroke(Color.WHITE);
        }

    }

}
