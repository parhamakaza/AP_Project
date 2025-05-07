package model;

import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Timer;

public class Server extends Computer {
    public Timer timer;


    public Server(ArrayList<Port> ports , Level lvl ){
        this.root = lvl.root;
        this.lvl = lvl;
        this.ports = ports;
        for(Port i : ports){
            i.system = this;
        }
    }


}
