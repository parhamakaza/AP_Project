package model.computer;


import model.packet.Packet;
import model.port.Port;

import java.util.ArrayList;
import java.util.List;

public abstract class Computer {
    public static final double WIDTH = 100;
    public static final double HEIGHT = 150;
    public double x;
    public double y;
    public  ComputerTypes computerType ;
    public List<Port> ports = new ArrayList<>();
    public List<Packet> packets = new ArrayList<>();

    public boolean ready = false;

    public Computer(double x , double y){
        this.x = x;
        this.y = y;
    }

    public  boolean compIsReady(){
        for(Port i : this.ports){
            if (i.wire == null){
                return false;
            }
        }
       return true;
    }

    public double getCenterX(){
        return x + WIDTH/2;
    }
    public double getCenterY(){
        return y + HEIGHT/2;
    }
}
