package view.packets;

import javafx.scene.shape.Shape;
import model.packet.Packet;
import view.Drawable;

public abstract class PacketView implements Drawable {


    protected Packet packet;
    protected Shape shape;

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public Packet getPacket() {
        return packet;
    }

    public void setPacket(Packet packet) {
        this.packet = packet;
    }

    public PacketView(Packet packet){
        this.packet = packet;
        this.draw();
    }


}
