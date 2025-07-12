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

        LevelView lvl = LevelController.makeLevel(new Level(10000));

        LevelManager.lvl = lvl.getLevel();
        SceneManager.goToLevel(lvl.getScene());
        Server server = new Server(500 , 500);
        ComputerController.MakeComputer(server);
        PortController.makePort(new SquarePort(PortType.OUTPUT,server, 1));
        PortController.makePort(new TrianglePort(PortType.OUTPUT,server, 2));
        PortController.makePort(new MaticPort(PortType.OUTPUT,server, 3));

        Server server2 = new Server(1400 , 500);
        ComputerController.MakeComputer(server2);
        PortController.makePort(new SquarePort(PortType.INPUT, server2, 2));
        PortController.makePort(new MaticPort(PortType.INPUT, server2, 3));
       PortController.makePort(new TrianglePort(PortType.INPUT, server2, 1));


        VPN vpn = new VPN(900 , 500);
        ComputerController.MakeComputer(vpn);
        PortController.makePort(new SquarePort(PortType.INPUT,vpn, 1));
        PortController.makePort(new TrianglePort(PortType.INPUT,vpn, 2));
        PortController.makePort(new MaticPort(PortType.INPUT,vpn, 3));
        PortController.makePort(new SquarePort(PortType.OUTPUT, vpn, 1));
        PortController.makePort(new TrianglePort(PortType.OUTPUT, vpn, 2));
        PortController.makePort(new MaticPort(PortType.OUTPUT,vpn, 3));

        DDOS ddos = new DDOS(1150 , 500);
        ComputerController.MakeComputer(ddos);
        PortController.makePort(new TrianglePort(PortType.INPUT,ddos, 2));
        PortController.makePort(new SquarePort(PortType.OUTPUT, ddos, 1));

        Spy spy1 = new Spy(1000 , 700);
        ComputerController.MakeComputer(spy1);
        PortController.makePort(new MaticPort(PortType.OUTPUT, spy1, 2));
        PortController.makePort(new MaticPort(PortType.INPUT,spy1, 2));


        Spy spy = new Spy(1000 , 300);
        ComputerController.MakeComputer(spy);
        PortController.makePort(new SquarePort(PortType.INPUT,spy, 2));
       // PortController.makePort(new TrianglePort(PortType.INPUT,spy, 3));
       // PortController.makePort(new MaticPort(PortType.INPUT,spy, 2));
        //PortController.makePort(new SquarePort(PortType.OUTPUT, spy, 1));
        //PortController.makePort(new MaticPort(PortType.OUTPUT, spy, 2));
        PortController.makePort(new TrianglePort(PortType.OUTPUT, spy, 2));




    }
}