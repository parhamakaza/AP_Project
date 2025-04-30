package model;

import javafx.scene.layout.Pane;

public  class TrianglePort extends Port {

    public TrianglePort(Computer system, PortType portType, int num ){
        this.portType=portType;
        this.system=system;
        this.portNum = num;

    }

    public TrianglePort(PortType portType , int num){
        this.portType=portType;
        this.portNum = num;
    }


    public double centerX(){
        return this.x;
    }

    public double centerY(){
        return (this.y) + 10;
    }

}
