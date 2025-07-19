package view.packets;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import model.packet.BitPacket;
import model.packet.Packet;

public class BitPacketView extends PacketView{
    public BitPacketView(Packet packet) {
        super(packet);
    }

    @Override
    public void draw() {
        double radius = 10.0;


        Polygon pentagon = new Polygon();

        pentagon.getPoints().addAll(
                0.0, -radius,                   // Top center point
                radius * 0.951, -radius * 0.309,  // Upper-right point
                radius * 0.588, radius * 0.809,   // Lower-right point
                -radius * 0.588, radius * 0.809,  // Lower-left point
                -radius * 0.951, -radius * 0.309   // Upper-left point
        );

        pentagon.setFill(Color.rgb(255, 255, 0, 0.2));
        if(packet.isVpn()){
            pentagon.setStroke(Color.LIGHTBLUE);
        }else {
            pentagon.setStroke(Color.DARKBLUE);
        }
        pentagon.setStrokeWidth(4);
        this.shape = pentagon;
        setInitialisesCoordination();

    }
}
