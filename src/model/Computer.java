package model;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public abstract class Computer {
    public static final int WIDTH = 100, HEIGHT = 150;
    public double x;
    public double y;
    public ArrayList<Port>  ports;
    public Pane root;
    public boolean ready = false;
    public Shape shape;




}
