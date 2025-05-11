package model;

import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Gsystem extends Computer {
    private int insidePackets;
    ArrayList<Packet> packets;
    //private ArrayList<SquarePort> squarePorts;
    //private ArrayList<TrianglePort> trianglePorts;

    public Gsystem(ArrayList<Port> ports ,  Level lvl){
        this.ports = ports;
        for(Port i : ports){
            i.system = this;
            /*if(i.getClass().getSimpleName().equals("SquarePort")){
                this.squarePorts.add((SquarePort) i);

            }else {
                this.trianglePorts.add((TrianglePort)i);
            }*/
        }
        this.root = lvl.root;
        this.lvl=lvl;
    }
    public void transferPacket(Packet packet){
        if(packet instanceof SquarePacket){
            for(Port i : this.ports){
                if((i instanceof SquarePort ) && (i.portType.equals(PortType.OUTPUT) )&& (i.wire.avaible == true)){

                    packet.sendPacket(i,this.root);

                    return;
                }
            }

            for(Port i : this.ports){
                if( i.portType.equals(PortType.OUTPUT) && (i.wire.avaible == true)){


                    packet.sendPacket(i , this.root);
                    return;
                }
            }
            packets.add( packet);

        }
        if(packet instanceof TrianglePacket){
            for(Port i : this.ports){
                if((i instanceof TrianglePort ) && i.portType.equals(PortType.OUTPUT) && (i.wire.avaible == true)){

                    packet.sendPacket(i,this.root);
                    return;
                }
            }

            for(Port i : this.ports){
                if( i.portType.equals(PortType.OUTPUT) && (i.wire.avaible == true)){


                    packet.sendPacket(i , this.root);
                    return;
                }
            }
            packets.add( packet);

        }




    }

}
