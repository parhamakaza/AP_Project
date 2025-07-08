package model.computer;

import model.packet.Packet;

import java.util.ArrayList;
import java.util.List;

public class Transformer extends Computer {



    public Transformer(double x , double y){
        super(x , y);
        this.computerType = ComputerTypes.TRANSFOMER;
    }


}
