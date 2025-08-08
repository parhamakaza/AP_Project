package saveAndLoad;

import controller.*;
import model.Level;
import model.computer.Computer;
import model.packet.Packet;
import model.port.Port;
import model.wire.Wire;
import service.SceneManager;
import view.LevelView;

import static manager.LevelManager.theLevelManager;
import static saveAndLoad.Load.theLoadedData;

public class LinkData {

    public static void link(){
        LevelView levelView = linkLevel();
        SceneManager.goToLevel(levelView.getScene());
        linkComputers();
        linkPorts();
        linkWires();
        linkPackets();
        theLevelManager.pauseAndResume(false);
    }

    private static LevelView linkLevel(){
        for(Level level : theLoadedData.getLevels()){
            return LevelController.makeLevel(level);
        }
        return null;
    }

    private static void linkComputers(){
        for(Computer computer : theLoadedData.getComputers()){
            ComputerController.MakeComputer(computer);
        }
    }
    private static Computer findComputerById(String string){
        for(Computer computer : theLoadedData.getComputers()){
            if(computer.getId().equals(string)){
                return computer;
            }
        }
        System.out.println("the hell");
        return null;

    }

    private static void linkPorts(){
        for (Port port : theLoadedData.getPorts()){
            port.computer = findComputerById(port.computerID);
            PortController.makePort(port);
        }
    }
    private static Port findPortById(String string){
        for (Port port : theLoadedData.getPorts()){
            if(port.portID.equals(string)){
                return port;
            }
        }
        System.out.println("the hell");
        return null;
    }

    private static void linkWires(){
        for (Wire wire : theLoadedData.getWires()){
            wire.sPort = findPortById(wire.sPortId);
            wire.ePort = findPortById(wire.ePortId);
            WireController.makeWire(wire);
        }

    }

    private static void linkPackets(){
        for (Packet packet : theLoadedData.getPackets()){
            PacketContoller.makePacket(packet);
        }


    }

}
