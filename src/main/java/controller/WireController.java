package controller;

import model.wire.Wire;
import view.WireView;

import static controller.ComponentsController.TheComponentsController;


public class WireController {

    public static WireView makeWire(Wire wire){
        WireView wireView = new WireView(wire);
        TheComponentsController.putView(wire,wireView);
        return wireView;
    }


}
