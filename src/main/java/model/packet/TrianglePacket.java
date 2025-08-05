package model.packet;


import model.Type;
import model.port.Port;

public class TrianglePacket extends Packet {


    public TrianglePacket (){
        super();
        size = 3;
        value = 3;
        type = Type.TRIANGLE;

    }





}
