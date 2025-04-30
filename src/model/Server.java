package model;

import java.util.ArrayList;

public class Server extends Computer {

    public Server(ArrayList<Port> ports ){
        this.ports = ports;
        for(Port i : ports){
            i.system = this;
        }
    }


}
