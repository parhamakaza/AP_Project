package view.packets;

import javafx.scene.paint.Color;
import model.packet.Packet;
import service.SceneManager;

public class MaticPacketView extends PacketView {

    public MaticPacketView(Packet packet) {
        super(packet);
    }

    @Override
    public void draw() {
        this.shape = this.createMaticShape();
        shape.fillProperty().setValue(Color.PINK);
   setShapInitialCordination();
    }

}
