package model;

import java.util.ArrayList;

public class System extends AbstarctSystem{
    private int insidePackets;
    public System(ArrayList<Port> ports ){
        this.ports = ports;
        for(Port i : ports){
            i.system = this;
        }
    }



}
