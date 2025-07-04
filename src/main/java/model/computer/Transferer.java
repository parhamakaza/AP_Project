package model.computer;

import model.packet.Packet;

import java.util.ArrayList;

public class Transferer extends Computer {
    public ArrayList<Packet> packets = new ArrayList<>();

    public Transferer(double x , double y){
        super(x , y);
    }


}
