package controller;

import manager.WireManager;
import model.port.Port;
import view.port.PortView;
import view.port.PortViewFactory;

import static controller.ComponentsController.TheComponentsController;

public class PortController {

    public static PortView makePort(Port port){
        PortView portView = PortViewFactory.creatPortView(port);
        WireManager.professionalWiring(portView);
        TheComponentsController.putView(port,portView);
        return portView;

    }

}
