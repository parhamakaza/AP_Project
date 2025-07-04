package view;

import controller.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import manager.LevelManager;
import manager.ServerManager;
import model.computer.Transferer;
import model.packet.Packet;
import model.packet.SquarePacket;
import model.packet.TrianglePacket;
import model.port.Port;
import model.port.PortType;
import service.SceneManager;

import java.util.HashSet;
import java.util.Set;

import static controller.PacketContoller.killPacket;
import static controller.PacketContoller.packetMap;

public class PacketView {
    public static  final double FRAME_DURATION = 16;
    public static Set<Timeline> packetTimelines = new HashSet<>();
    private Packet packet;
    private Shape shape;
    Timeline timeline = new Timeline();



    public  static  void sendPacket(Port sPort , Packet packet){
        if(sPort.portType.equals(PortType.OUTPUT)) {
            packet.sPort = sPort;
            packet.wire = sPort.wire;
            packet.ePort = sPort.wire.ePort;
            movePacket(packet);
        }
    }

    public Shape getShape() {
        return shape;
    }

    public Packet getPacket() {
        return packet;
    }

    public void setPacket(Packet packet) {
        this.packet = packet;
    }

    private Shape draw(){
        Packet packet1 = this.packet;
        if(packet1 instanceof SquarePacket){
            this.shape = PortView.createRectangleAsPolygon();
        }else if(packet1 instanceof TrianglePacket){
            this.shape = PortView.createTriangleAsPolygon();
        }
        shape.setLayoutX(packet1.x);
        shape.setLayoutY(packet1.y);
        ((Pane)SceneManager.getPrimaryStage().getScene().getRoot()).getChildren().add(shape);
        return shape;
    }

    public PacketView(Packet packet){
        this.packet = packet;
        this.draw();

    }



    public void buildAndStartKeyFrame(Packet packet) {
        Shape shape = packetMap.get(packet).getShape();
        packetTimelines.add(timeline);

        KeyFrame keyFrame = new KeyFrame(Duration.millis(PacketView.FRAME_DURATION) , event -> {

                packet.sPort.wire.avaible = false;

                // Calculate speed with multiplier
                double currentSpeed = 100;

                double movePerFrame = currentSpeed * (PacketView.FRAME_DURATION /1000) * LevelManager.gameSpeed ;

                // Move
                packet.currentX[0] += packet.unitX[0] * movePerFrame;
                packet.currentY[0] += packet.unitY[0] * movePerFrame;

                shape.setLayoutX(packet.currentX[0]);
                shape.setLayoutY(packet.currentY[0]);
                packet.x = packet.currentX[0];
                packet.y = packet.currentY[0];
                double traveled = Math.sqrt((packet.currentX[0] - packet.x1) * (packet.currentX[0] -packet. x1) + (packet.currentY[0] -packet. y1) * (packet.currentY[0] - packet.y1));
                Shape intersection = Shape.intersect(shape, (WireController.wireMap.get(packet.sPort.wire)).getLine());

                if (traveled >= packet.distance) {

                    packet.sPort.wire.avaible = true;


                    ((Pane)SceneManager.getPrimaryStage().getScene().getRoot()).getChildren().remove(shape);
                    timeline.stop();


                    try {
                        ((Transferer) packet.ePort.computer).packets.add(packet);
                    } catch (ClassCastException e) {
                        ServerManager.takePacket(packet, LevelManager.lvl);
                    }
                    return;
                }

                if (!(intersection.getBoundsInLocal().getWidth() > 0 && intersection.getBoundsInLocal().getHeight() > 0)) {
                    killPacket(packet);
                }

        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();




    }
    public static void movePacket(Packet packet){
        PacketView packetView= PacketContoller.makePacket(packet);
        packet.x1 = packet.sPort.x;
        packet.y1 = packet.sPort.y;
        packet.x2 = packet.ePort.x;
        packet.y2 = packet.ePort.y;

        packet.sPort.wire.avaible = false;

        double dx = packet.x2 - packet.x1;
        double dy = packet.y2 - packet.y1;
        packet.distance = Math.sqrt(dx * dx + dy * dy);

        double unitX = dx / packet.distance;
        packet.uniitX = unitX;
        packet.unitX[0] = unitX;
        double unitY = dy / packet.distance;
        packet.uniitY = unitY;
        packet.unitY[0] = unitY;


        packet.currentX = new double[]{packet.x1};
        packet.currentY = new double[]{packet.y1};

        packetView.buildAndStartKeyFrame(packet);




    }
}
