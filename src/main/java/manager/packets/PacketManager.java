package manager.packets;


import javafx.animation.AnimationTimer;

import javafx.scene.shape.QuadCurve;

import javafx.scene.shape.Shape;

import manager.ComponentsManager;
import manager.LevelManager;
import model.computer.*;
import model.packet.Packet;

import model.wire.Wire;
import service.SceneManager;
import view.packets.PacketView;


import java.util.ArrayList;
import java.util.List;

import static controller.ComponentsController.*;
import static manager.ComponentsManager.TheComponentsManager;

import static manager.LevelManager.theLevelManager;
import static manager.packets.PacketState.*;



/**
 * This class manages the animation for a single packet on a path of curves.
 */

public abstract class PacketManager extends AnimationTimer  {

    protected static final double STANDARDSPEED = 80.0;

    protected record ArcLengthData(int curveIndex, double t, double cumulativeDistance) {}


    protected PacketView packetView;

    protected Packet packet;

    protected final Shape shape;

    protected final List<QuadCurve> path;

    protected double speed = STANDARDSPEED;

    protected final List<ArcLengthData> lookupTable = new ArrayList<>();

    protected double totalPathLength;

    protected long lastUpdate = 0;

    protected Wire wire;

    protected double distanceTraveled = 0;

    protected PacketState currentState = FORWARD;





    public PacketManager(Packet packet, Wire wire) {

        this.packetView = TheComponentsController.packetViewMap.get(packet);

        this.wire = wire;
        wire.avaible = false;

        this.packet = packet;

        this.shape = packetView.getShape() ;

        shape.setLayoutX(packet.x);
        shape.setLayoutY(packet.y);

        this.path = TheComponentsController.wireViewMap.get(wire).getCurves();

        SceneManager.addComponent(shape);

        buildLookupTable();

    }


    @Override
    public void handle(long now) {
        packet.wire.avaible = false;
        if (lastUpdate == 0 || theLevelManager.paused) {

            lastUpdate = now;

            return;

        }


        double elapsedSeconds = (now - lastUpdate) / 1_000_000_000.0;



        lastUpdate = now;


        switch (currentState) {
            case FORWARD:
                distanceTraveled += speed * elapsedSeconds;

                    if(distanceTraveled >= totalPathLength){
                        Computer computer =  packet.wire.ePort.computer;
                        if(computer.disable){
                            currentState = RETURNING;
                        }else {
                            stop();
                            packetMovementEnds(computer);
                        }
                    }

                break;

            case RETURNING:
                distanceTraveled -= speed * elapsedSeconds;


                if (distanceTraveled <= 0) {
                    distanceTraveled = 0;

                    currentState = FORWARD;

                }
                break;
        }

        ArcLengthData targetData = getPathDataForDistance(distanceTraveled);

        Point2D position = evaluateCurve(targetData.curveIndex(), targetData.t());




        packet.x = position.x + packet.deflectedX;
        packet.y = position.y + packet.deflectedY;
        shape.setLayoutX(packet.x);
        shape.setLayoutY(packet.y);



    }

    protected void packetMovementEnds(Computer computer){
        packet.wire.avaible = true;
        SceneManager.removeComponent(shape);
        TheComponentsManager.computerManagerMap.get(computer).takePacket(packet);
    }


    protected double buildLookupTable() {

        final int stepsPerCurve = 1000;

        double cumulativeDistance = 0;


// Iterate through each curve in the path

        for (int i = 0; i < path.size(); i++) {



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
        return totalPathLength;

    }


    protected ArcLengthData getPathDataForDistance(double distance) {

        if (distance <= 0) return lookupTable.get(0);

        if (distance >= totalPathLength) return lookupTable.get(lookupTable.size() - 1);


        ArcLengthData p1 = lookupTable.getFirst();

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


    protected Point2D evaluateCurve(int curveIndex, double t) {

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


    protected record Point2D(double x, double y) {

        public double distance(Point2D other) {

            return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));

        }

    }

    public static void returnPacket(Packet packet) {
        TheComponentsManager.packetManagerMap.get(packet).currentState = RETURNING;
    }

    public static void changeDirection(Packet packet){
        PacketState packetState = TheComponentsManager.packetManagerMap.get(packet).currentState;
        switch (packetState){
            case RETURNING ->  TheComponentsManager.packetManagerMap.get(packet).currentState = FORWARD;


            case FORWARD ->  TheComponentsManager.packetManagerMap.get(packet).currentState = RETURNING;
        }

    }


}