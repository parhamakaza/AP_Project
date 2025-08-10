package model.computer;


import model.packet.Packet;
import model.port.Port;
import model.wire.Wire;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Computer implements Serializable {
    public static final double WIDTH = 100;
    public static final double HEIGHT = 150;
    public double x;
    public double y;
    public ComputerTypes computerType ;
    protected final String id;
    public boolean disable = false;
    public boolean ready = false;

    public String getId() {
        return id;
    }

    public  List<Port> ports = new ArrayList<>();
    public List<Packet> packets = new ArrayList<>();


    public Computer(double x , double y , String id){
        this.id = id;
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

    public boolean thisWireIsMine(Wire wire){
        for (Port port : ports){
            if(port.wire == wire){
                return true;
            }
        }
        return false;

    }

}
