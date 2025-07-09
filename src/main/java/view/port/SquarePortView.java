package view.port;

import model.port.Port;

public class SquarePortView extends PortView {
    public SquarePortView(Port port) {
        super(port);
    }
    @Override
    public void draw(){
        this.shape = this.createRectangleShape();
        setPortCordination();
    }
}
