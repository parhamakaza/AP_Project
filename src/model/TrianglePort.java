package model;

public  class TrianglePort extends Port {
    public TrianglePort(AbstarctSystem system, PortType portType, int num ){
        this.portType=portType;
        this.system=system;
        this.portNum = num;

    }
    public TrianglePort(PortType portType , int num){
        this.portType=portType;
        this.portNum = num;
    }

}
