package view.packets;

import javafx.scene.paint.Color;
import model.packet.Packet;

public class TrianglePacketView extends PacketView{

    public TrianglePacketView(Packet packet) {
        super(packet);
    }

    @Override
    public void draw(){
        this.shape = this.createTriangleShape();
        shape.fillProperty().setValue(Color.PINK);
    setInitialisesCoordination();
    }
}
