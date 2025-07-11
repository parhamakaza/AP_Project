package controller;

import model.wire.Wire;
import view.WireView;

import java.util.HashMap;


public class WireController {

    public static HashMap<Wire,WireView> wireViewMap = new HashMap<>();

    public static WireView makeWire(Wire wire){
        WireView wireView = new WireView(wire);
        wireViewMap.put(wire,wireView);
        return wireView;
    }


}
