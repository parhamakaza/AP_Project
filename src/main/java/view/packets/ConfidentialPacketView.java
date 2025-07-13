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
        double radius = 10.0;


        Circle circle = new Circle();
        circle.setCenterX(0.0);
        circle.setCenterY(radius);
        circle.setRadius(radius);
        circle.setFill(Color.BLUE);
        this.shape = circle;
        //shape.fillProperty().setValue(Color.PINK);
        setShapInitialCordination();

    }
}
