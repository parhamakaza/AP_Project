package view.packets;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.packet.Packet;
import service.SceneManager;

public class ConfidentialPacketView extends PacketView{
    public ConfidentialPacketView(Packet packet) {
        super(packet);
    }

    @Override
    public void draw() {

        Circle circle = new Circle();
        circle.setCenterX(0.0);
        circle.setCenterY(0.0);
        circle.setRadius(10);
        circle.setFill(Color.BLUE);
        this.shape = circle;
        //shape.fillProperty().setValue(Color.PINK);
        setShapInitialCordination();

    }
}
