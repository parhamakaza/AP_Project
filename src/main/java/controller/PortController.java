package controller;

import manager.WireManager;
import model.port.Port;
import view.port.PortView;
import view.port.PortViewFactory;

import java.util.HashMap;

public class PortController {

    public static HashMap<Port , PortView> portMap = new HashMap<>();
    public static PortView makePort(Port port){
        PortView portView = PortViewFactory.creatPortView(port);
        WireManager.professionalWiring(portView);
        portMap.put(port,portView);
        return portView;

    }

}
