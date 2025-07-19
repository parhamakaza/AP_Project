package view.packets;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import model.packet.Packet;

public class BitPacketView extends PacketView{
    public BitPacketView(Packet packet) {
        super(packet);
    }

    @Override
    public void draw() {
        double radius = 10.0;

// Create a new Polygon object
        Polygon pentagon = new Polygon();

// Add the 5 points for a regular pentagon centered at (0,0).
// The points are calculated using sines and cosines of 72-degree increments.
        pentagon.getPoints().addAll(
                0.0, -radius,                   // Top center point
                radius * 0.951, -radius * 0.309,  // Upper-right point
                radius * 0.588, radius * 0.809,   // Lower-right point
                -radius * 0.588, radius * 0.809,  // Lower-left point
                -radius * 0.951, -radius * 0.309   // Upper-left point
        );

        pentagon.setFill(Color.rgb(255, 255, 0, 0.2));
        pentagon.setStroke(Color.DARKBLUE);
        pentagon.setStrokeWidth(4);
        this.shape = pentagon;
        setInitialisesCoordination();
    }
}
