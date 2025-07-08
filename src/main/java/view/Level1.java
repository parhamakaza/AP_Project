package view;


import controller.*;
import manager.LevelManager;
import model.*;
import model.computer.Server;
import model.computer.Spy;
import model.computer.Transformer;
import model.port.PortType;
import model.port.SquarePort;
import model.port.TrianglePort;
import service.SceneManager;

public class Level1 extends LevelView{



    public Level1 (double x){
        super(new Level(x));
    }

    public static void startLevel1(){

        LevelView lvl = LevelController.makeLevel(new Level(10000));

        LevelManager.lvl = lvl.getLevel();
        SceneManager.goToLevel(lvl.getScene());
        Server server = new Server(500 , 500);
        ComputerController.MakeComputer(server);
        //PortController.makePort(new SquarePort(PortType.OUTPUT,server, 1));
        PortController.makePort(new SquarePort(PortType.OUTPUT,server, 2));
        //PortController.makePort(new TrianglePort(PortType.OUTPUT,server, 3));

        Server server2 = new Server(1200 , 500);
        ComputerController.MakeComputer(server2);
        PortController.makePort(new SquarePort(PortType.INPUT, server2, 1));
        PortController.makePort(new SquarePort(PortType.INPUT, server2, 2));
       PortController.makePort(new TrianglePort(PortType.INPUT, server2, 3));

        Spy spy1 = new Spy(800 , 300);
        ComputerController.MakeComputer(spy1);
        PortController.makePort(new SquarePort(PortType.OUTPUT,spy1, 1));
        PortController.makePort(new SquarePort(PortType.INPUT, spy1, 1));

        Spy spy2 = new Spy(800 , 500);
        ComputerController.MakeComputer(spy2);
        //PortController.makePort(new TrianglePort(PortType.INPUT, spy2, 3));
        PortController.makePort(new SquarePort(PortType.OUTPUT,spy2, 3));

        Spy spy3 = new Spy(800 , 700);
        ComputerController.MakeComputer(spy3);
        //PortController.makePort(new SquarePort(PortType.INPUT, spy3, 2));
        PortController.makePort(new TrianglePort(PortType.OUTPUT,spy3, 2));


    }
}