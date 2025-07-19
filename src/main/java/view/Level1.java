package view;


import controller.*;
import manager.GameManager;
import model.*;
import model.computer.*;
import model.port.MaticPort;
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

        GameManager.lvl = lvl.getLevel();
        SceneManager.goToLevel(lvl.getScene());
        Server sServer = new Server(500 , 500);
        ComputerController.MakeComputer(sServer);
        PortController.makePort(new TrianglePort(PortType.OUTPUT, sServer, 1));
        PortController.makePort(new SquarePort(PortType.OUTPUT, sServer, 2));
        PortController.makePort(new MaticPort(PortType.OUTPUT, sServer, 3));

        Server endServer = new Server(1400 , 500);
        ComputerController.MakeComputer(endServer);
       PortController.makePort(new TrianglePort(PortType.INPUT, endServer, 1));
        PortController.makePort(new SquarePort(PortType.INPUT, endServer, 2));
        PortController.makePort(new MaticPort(PortType.INPUT, endServer, 3));


        Merger merger = new Merger(1100 , 500);
        ComputerController.MakeComputer(merger);
        PortController.makePort(new TrianglePort(PortType.INPUT, merger, 1));
        PortController.makePort(new SquarePort(PortType.INPUT, merger, 2));
        PortController.makePort(new MaticPort(PortType.INPUT, merger, 3));
        PortController.makePort(new TrianglePort(PortType.OUTPUT, merger, 1));
        PortController.makePort(new SquarePort(PortType.OUTPUT, merger, 2));
        PortController.makePort(new MaticPort(PortType.OUTPUT, merger, 3));

        Distributor distributor = new Distributor(800 , 500);
        ComputerController.MakeComputer(distributor);

        PortController.makePort(new TrianglePort(PortType.INPUT, distributor, 1));
        PortController.makePort(new SquarePort(PortType.INPUT, distributor, 2));
        PortController.makePort(new MaticPort(PortType.INPUT, distributor, 3));
        PortController.makePort(new TrianglePort(PortType.OUTPUT, distributor, 1));
        PortController.makePort(new SquarePort(PortType.OUTPUT, distributor, 2));
        PortController.makePort(new MaticPort(PortType.OUTPUT, distributor, 3));



    }
}