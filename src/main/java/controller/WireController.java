package controller;

import model.wire.Wire;
import view.WireView;

import java.util.HashMap;


public class WireController {

    public static HashMap<Wire,WireView> wireMap = new HashMap<>();

    public static WireView makeWire(Wire wire){
        WireView wireView = new WireView(wire);
        wireMap.put(wire,wireView);
        return wireView;
    }


}
