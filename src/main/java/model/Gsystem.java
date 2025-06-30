package model;

import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Gsystem extends Computer {

    public ArrayList<Packet> packets = new ArrayList<>();

    public Gsystem(ArrayList<Port> ports ,  Level lvl){
        this.ports = ports;
        for(Port i : ports){
            i.system = this;
        }
        this.root = lvl.root;
        this.lvl=lvl;
    }


}
