package model.computer;

import model.packet.Packet;

import java.util.ArrayList;
import java.util.List;

public class Transformer extends Computer {
    public List<Packet> packets = new ArrayList<>();



    public Transformer(double x , double y){
        super(x , y);
    }


}
