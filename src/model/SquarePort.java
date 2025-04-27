package model;

public class SquarePort extends Port {
    public static final double SIZE = 20;

    public SquarePort(AbstarctSystem system, PortType portType, int num ){
        this.portType=portType;
        this.system=system;
        this.portNum = num;

    }
    public SquarePort(PortType portType , int num){
        this.portType=portType;
        this.portNum = num;
    }


}
