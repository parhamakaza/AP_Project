package view;


import controller.*;
import manager.LevelManager;
import model.*;
import model.computer.Server;
import model.port.PortType;
import model.port.SquarePort;
import model.port.TrianglePort;
import service.SceneManager;

public class Level1 extends LevelView{



    public Level1 (double x){
        super(new Level());
        this.getLevel().wireLength = x;
    }

    public static void startLevel1(){

        LevelView lvl = LevelController.makeLevel(new Level(10000));

        LevelManager.lvl = lvl.getLevel();
        SceneManager.goToLevel(lvl.getScene());
        Server server = new Server(500 , 500);
        ComputerController.MakeComputer(server);
        PortController.makePort(new SquarePort(PortType.OUTPUT,server, 1));
        PortController.makePort(new SquarePort(PortType.OUTPUT,server, 2));
        PortController.makePort(new TrianglePort(PortType.OUTPUT,server, 3));

        Server server2 = new Server(1200 , 500);
        ComputerController.MakeComputer(server2);
        PortController.makePort(new SquarePort(PortType.INPUT, server2, 1));

        PortController.makePort(new SquarePort(PortType.INPUT, server2, 2));
        PortController.makePort(new TrianglePort(PortType.INPUT, server2, 3));








    }
}