package view.port;

import model.packet.Packet;
import model.port.Port;
import view.packets.PacketView;

public class MaticPortView extends PortView {

    public MaticPortView(Port port) {
        super(port);
    }

    @Override
    public void draw(){
        this.shape = this.createMaticShape();
        setPortCordination();
    }
}
