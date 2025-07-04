package controller;

import manager.WireManager;
import model.port.Port;
import view.PortView;

import java.util.HashMap;

public class PortController {

    public static HashMap<Port , PortView> portMap = new HashMap<>();
    public static PortView makePort(Port port){
        PortView portView = new PortView(port);
        WireManager.professionalWiring(portView);
        portMap.put(port,portView);
        return portView;

    }

}
