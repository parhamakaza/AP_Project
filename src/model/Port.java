package model;

public abstract class Port {
    public Computer system;
    public PortType portType;
    public double x;
    public double y;
    public int portNum;
    public Wire wire;

    abstract double centerX();
    abstract double centerY();








}

