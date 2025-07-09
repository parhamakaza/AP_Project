package view.packets;

import javafx.scene.paint.Color;
import model.packet.Packet;
import service.SceneManager;

public class SquarePacketView extends PacketView{

    public SquarePacketView(Packet packet) {
        super(packet);
    }

    @Override
    public void draw(){
        this.shape = this.createRectangleShape();
        shape.fillProperty().setValue(Color.PINK);
        shape.setLayoutX(packet.x);
        shape.setLayoutY(packet.y);
        SceneManager.addComponent(shape);
    }
}
