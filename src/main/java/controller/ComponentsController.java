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
}
