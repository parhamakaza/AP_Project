package view.packets;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import model.packet.Packet;

public class MassivePacketView extends PacketView{
    public MassivePacketView(Packet packet) {
        super(packet);
    }

    @Override
    public void draw() {
        double radius = 10.0;

        Polygon hexagon = new Polygon();

        hexagon.getPoints().addAll(
                0.0, -radius,                   // Top center point
                radius * 0.866, -radius * 0.5,  // Upper-right point
                radius * 0.866, radius * 0.5,   // Lower-right point
                0.0, radius,                    // Bottom center point
                -radius * 0.866, radius * 0.5,  // Lower-left point
                -radius * 0.866, -radius * 0.5  // Upper-left point
        );
        hexagon.setFill(Color.rgb(255, 255, 0, 0.2));
        hexagon.setStroke(Color.DARKBLUE);
        hexagon.setStrokeWidth(4);
        this.shape = hexagon;
        setInitialisesCoordination();

    }
}
