package view;


import controller.*;
import manager.LevelManager;
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

        LevelView lvl = LevelController.makeLevel(new Level(19000));

        LevelManager.lvl = lvl.getLevel();
        SceneManager.goToLevel(lvl.getScene());
        Server sServer = new Server(500 , 500 , "a");
        ComputerController.MakeComputer(sServer);
        PortController.makePort(new TrianglePort(PortType.OUTPUT, sServer, 1 , "s1f"));
        PortController.makePort(new SquarePort(PortType.OUTPUT, sServer, 2, "s2f"));
        PortController.makePort(new MaticPort(PortType.OUTPUT, sServer, 3, "s3f"));

        Server endServer = new Server(1400 , 500 , "b");
        ComputerController.MakeComputer(endServer);
       PortController.makePort(new TrianglePort(PortType.INPUT, endServer, 1, "s4f"));
        PortController.makePort(new SquarePort(PortType.INPUT, endServer, 2, "s5f"));
        PortController.makePort(new MaticPort(PortType.INPUT, endServer, 3, "s6f"));


        Merger merger = new Merger(1100 , 500, "c");
        ComputerController.MakeComputer(merger);
        PortController.makePort(new TrianglePort(PortType.INPUT, merger, 1, "s7f"));
        PortController.makePort(new SquarePort(PortType.INPUT, merger, 2, "s8f"));
        PortController.makePort(new MaticPort(PortType.INPUT, merger, 3, "s9f"));
        PortController.makePort(new TrianglePort(PortType.OUTPUT, merger, 1, "s0f"));
        PortController.makePort(new SquarePort(PortType.OUTPUT, merger, 2, "sf2"));
        PortController.makePort(new MaticPort(PortType.OUTPUT, merger, 3, "sf1"));

        Distributor distributor = new Distributor(800 , 500, "d");
        ComputerController.MakeComputer(distributor);

        PortController.makePort(new TrianglePort(PortType.INPUT, distributor, 1, "sf4"));
        PortController.makePort(new SquarePort(PortType.INPUT, distributor, 2, "sf5"));
        PortController.makePort(new MaticPort(PortType.INPUT, distributor, 3, "sf6"));
        PortController.makePort(new TrianglePort(PortType.OUTPUT, distributor, 1, "sf7"));
        PortController.makePort(new SquarePort(PortType.OUTPUT, distributor, 2, "sf3"));
        PortController.makePort(new MaticPort(PortType.OUTPUT, distributor, 3, "sfqfwe"));



    }
}