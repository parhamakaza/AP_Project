package controller;

import model.Level;
import model.computer.Computer;
import model.packet.Packet;
import model.port.Port;
import model.wire.Wire;
import view.ComputerView;
import view.LevelView;
import view.WireView;
import view.packets.PacketView;
import view.port.PortView;

import java.util.HashMap;
import java.util.Map;

public class ComponentsController {
    public static ComponentsController TheComponentsController;
    public Map<Computer, ComputerView> computerViewMap = new HashMap<>();
    public Map<Level, LevelView> levelViewMap = new HashMap<>();
    public Map<Packet, PacketView> packetViewMap = new HashMap<>();
    public Map<Port, PortView> portViewMap = new HashMap<>();
    public Map<Wire, WireView> wireViewMap = new HashMap<>();

    public ComponentsController(){
        TheComponentsController = this;
    }

    public ComputerView getView(Computer computer) {
        return computerViewMap.get(computer);
    }


    public PacketView getView(Packet packet) {
        return packetViewMap.get(packet);
    }


    public LevelView getView(Level level) {
        return levelViewMap.get(level);
    }


    public PortView getView(Port port) {
        return portViewMap.get(port);
    }


    public WireView getView(Wire wire) {
        return wireViewMap.get(wire);
    }

    public void putView(Computer computer, ComputerView computerView) {
        computerViewMap.put(computer, computerView);
    }

    public void putView(Packet packet, PacketView packetView) {
        packetViewMap.put(packet, packetView);
    }

    public void putView(Level level, LevelView levelView) {
        levelViewMap.put(level, levelView);
    }


    public void putView(Port port, PortView portView) {
        portViewMap.put(port, portView);
    }

    public void putView(Wire wire, WireView wireView) {
        wireViewMap.put(wire, wireView);
    }
}
