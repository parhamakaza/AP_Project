package manager;


import javafx.animation.AnimationTimer;

import javafx.scene.shape.QuadCurve;

import javafx.scene.shape.Shape;

import model.computer.Computer;
import model.computer.Server;
import model.computer.Transferer;
import model.packet.Packet;

import view.PacketView;


import java.util.ArrayList;
import java.util.List;


/**
 * This class manages the animation for a single packet on a path of curves.
 */

public class PacketAnimatorManager extends AnimationTimer {

    private static final double SPEED_PIXELS_PER_SECOND = 150.0;


    private record ArcLengthData(int curveIndex, double t, double cumulativeDistance) {}


    private PacketView packetView;

    private Packet packet;

    private final Shape shape;

    private final List<QuadCurve> path;

    private final double speed;

    private final List<ArcLengthData> lookupTable = new ArrayList<>();

    private double totalPathLength;

    private long lastUpdate = 0;

    private double distanceTraveled = 0;


    public PacketAnimatorManager(PacketView packetView, List<QuadCurve> path, double speed) {

        this.packetView = packetView;

        packetView.getPacket().wire.avaible = false;

        this.packet = packetView.getPacket();

        this.shape = packetView.getShape();

        this.path = path;

        this.speed = speed;

        buildLookupTable();

    }


    @Override

    public void handle(long now) {

        if (lastUpdate == 0) {

            lastUpdate = now;

            return;

        }


        double elapsedSeconds = (now - lastUpdate) / 1_000_000_000.0;

        lastUpdate = now;


        distanceTraveled += speed * elapsedSeconds;



        ArcLengthData targetData = getPathDataForDistance(distanceTraveled);




        Point2D position = evaluateCurve(targetData.curveIndex(), targetData.t());


        shape.setLayoutX(position.x);

        shape.setLayoutY(position.y - (double) Packet.SIZE / 2);

        bindToModule(shape);

        if(distanceTraveled >= totalPathLength){
            packet.wire.avaible = true;
            Computer computer = packet.wire.ePort.computer;
            if(computer instanceof Server){

                ServerManager.takePacket(packet);
            } else if (computer instanceof Transferer) {
               ((Transferer) computer).packets.add(packet);
            }
            stop();
        }

    }


    private void buildLookupTable() {

        final int stepsPerCurve = 1000;

        double cumulativeDistance = 0;


// Iterate through each curve in the path

        for (int i = 0; i < path.size(); i++) {

            QuadCurve currentCurve = path.get(i);


// Add the starting point of the current curve segment

            lookupTable.add(new ArcLengthData(i, 0, cumulativeDistance));

            Point2D prevPoint = evaluateCurve(i, 0);


            for (int j = 1; j <= stepsPerCurve; j++) {

                double t = (double) j / stepsPerCurve;

                Point2D currentPoint = evaluateCurve(i, t);

                cumulativeDistance += prevPoint.distance(currentPoint);

                lookupTable.add(new ArcLengthData(i, t, cumulativeDistance));

                prevPoint = currentPoint;

            }

        }

        totalPathLength = cumulativeDistance;

    }


    private ArcLengthData getPathDataForDistance(double distance) {

        if (distance <= 0) return lookupTable.get(0);

        if (distance >= totalPathLength) return lookupTable.get(lookupTable.size() - 1);


        ArcLengthData p1 = lookupTable.get(0);

        ArcLengthData p2 = null;


        for (int i = 1; i < lookupTable.size(); i++) {

            p2 = lookupTable.get(i);

            if (distance <= p2.cumulativeDistance()) break;

            p1 = p2;

        }


        double distanceBetween = p2.cumulativeDistance() - p1.cumulativeDistance();

        double fractionAlong = (distance - p1.cumulativeDistance()) / distanceBetween;


// Interpolate to find the precise t value

        double interpolatedT = p1.t() + (p2.t() - p1.t()) * fractionAlong;


// The curve index is the same as the starting point's index

        return new ArcLengthData(p1.curveIndex(), interpolatedT, distance);

    }


    private Point2D evaluateCurve(int curveIndex, double t) {

        QuadCurve c = path.get(curveIndex);

        double p0x = c.getStartX(), p0y = c.getStartY();

        double p1x = c.getControlX(), p1y = c.getControlY();

        double p2x = c.getEndX(), p2y = c.getEndY();


        double term1 = Math.pow(1 - t, 2);

        double term2 = 2 * (1 - t) * t;

        double term3 = Math.pow(t, 2);


        double x = term1 * p0x + term2 * p1x + term3 * p2x;

        double y = term1 * p0y + term2 * p1y + term3 * p2y;

        return new Point2D(x, y);

    }


    private record Point2D(double x, double y) {

        public double distance(Point2D other) {

            return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));

        }

    }


    private void bindToModule(Shape shape) {

        packet.x = shape.getLayoutX();

        packet.y = shape.getLayoutY();

    }

}