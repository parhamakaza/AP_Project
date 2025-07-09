package view.port;

import model.port.Port;

public class TrianglePortView extends  PortView {
    public TrianglePortView(Port port) {
        super(port);
    }
    public void draw(){
        this.shape = this.createTriangleAsPolygon();
        setPortCordination();
    }
}
