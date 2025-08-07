package saveAndLoad;

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

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static controller.ComponentsController.TheComponentsController;

public class GameData implements Serializable {
    // Best practice: always include a serialVersionUID for version control.
    private static final long serialVersionUID = 1L;

    private final Set<Computer> computers;
    private final Set<Level> levels;
    private final Set<Packet> packets;
    private final Set<Port> ports;
    private final Set<Wire> wires;


    public GameData(Map<Computer, ComputerView> computerMap,
                    Map<Level, LevelView> levelMap,
                    Map<Packet, PacketView> packetMap,
                    Map<Port, PortView> portMap,
                    Map<Wire, WireView> wireMap) {
        this.computers = new HashSet<>(computerMap.keySet());
        this.levels = new HashSet<>(levelMap.keySet());
        this.packets =new HashSet<>(packetMap.keySet()) ;
        this.ports = new HashSet<>(portMap.keySet());
        this.wires = new HashSet<>(wireMap.keySet());
    }

    // --- GETTERS to access the data after deserialization ---

    public Set<Computer> getComputers() {
        return computers;
    }

    public Set<Level> getLevels() {
        return levels;
    }

    public Set<Packet> getPackets() {
        return packets;
    }

    public Set<Port> getPorts() {
        return ports;
    }

    public Set<Wire> getWires() {
        return wires;
    }




}