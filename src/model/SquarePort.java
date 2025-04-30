package model;

public class SquarePort extends Port {
    public static final double SIZE = 20;

    public SquarePort(Computer system, PortType portType, int num ){

        this.portType=portType;
        this.system=system;
        this.portNum = num;

    }

    public SquarePort(PortType portType , int num){
        this.portType=portType;
        this.portNum = num;
    }


    public double centerX(){
        return (this.x) + SIZE/2;
    }

    public double centerY(){
        return (this.y) + SIZE/2;
    }



}
