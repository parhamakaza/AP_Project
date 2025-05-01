package model;

import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Server extends Computer {

    public Server(ArrayList<Port> ports , Pane root ){
        this.root = root;
        this.ports = ports;
        for(Port i : ports){
            i.system = this;
        }
    }


}
