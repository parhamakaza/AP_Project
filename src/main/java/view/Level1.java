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
        Server sServer = new Server(300 , 500 , "a");
        ComputerController.MakeComputer(sServer);
        //PortController.makePort(new TrianglePort(PortType.OUTPUT, sServer, 1 , "s1f"));
        PortController.makePort(new SquarePort(PortType.OUTPUT, sServer, 2, "s2f"));
        //PortController.makePort(new MaticPort(PortType.OUTPUT, sServer, 3, "s3f"));

        Server endServer = new Server(1400 , 500 , "b");
        ComputerController.MakeComputer(endServer);
        PortController.makePort(new TrianglePort(PortType.INPUT, endServer, 1, "s4f"));
        PortController.makePort(new SquarePort(PortType.INPUT, endServer, 2, "s5f"));
        PortController.makePort(new MaticPort(PortType.INPUT, endServer, 3, "s6f"));

        VPN vpn = new VPN(700 , 500, "vieajviern");
        ComputerController.MakeComputer(vpn);
        PortController.makePort(new SquarePort(PortType.INPUT, vpn, 2, "s5fsffvsvd"));
        PortController.makePort(new SquarePort(PortType.OUTPUT, vpn, 2, "s2fcac"));



        Spy merger = new Spy(900 , 300, "c");
        ComputerController.MakeComputer(merger);

        PortController.makePort(new SquarePort(PortType.OUTPUT, merger, 2, "sf2"));


        Spy distributor = new Spy(900 , 500, "d");
        ComputerController.MakeComputer(distributor);

        PortController.makePort(new TrianglePort(PortType.INPUT, distributor, 1, "sf4"));

        PortController.makePort(new MaticPort(PortType.OUTPUT, distributor, 3, "sfqfwe"));

        Spy spy = new Spy(900 , 700, "gad");
        ComputerController.MakeComputer(spy);
        PortController.makePort(new SquarePort(PortType.OUTPUT , spy , 2 , "nanrgnoei"));

    }
}