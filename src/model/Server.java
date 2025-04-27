package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.ArrayList;

public class Server extends AbstarctSystem{

    public Server(ArrayList<Port> ports ){
        this.ports = ports;
        for(Port i : ports){
            i.system = this;
        }
    }




}
