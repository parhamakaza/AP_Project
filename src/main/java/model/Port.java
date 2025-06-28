package model;

import javafx.scene.shape.Shape;

public abstract class Port {
    public Computer system;
    public PortType portType;
    public double x;
    public double y;
    public int portNum;
    public Wire wire;
    public Shape shape;

    public abstract double centerX();
    public abstract double centerY();








}

