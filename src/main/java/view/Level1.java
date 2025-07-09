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

        Server server2 = new Server(1300 , 500);
        ComputerController.MakeComputer(server2);
        PortController.makePort(new SquarePort(PortType.INPUT, server2, 1));
        PortController.makePort(new MaticPort(PortType.INPUT, server2, 2));
       PortController.makePort(new TrianglePort(PortType.INPUT, server2, 3));


        DDOS ddos = new DDOS(800 , 500);
        ComputerController.MakeComputer(ddos);
        PortController.makePort(new SquarePort(PortType.INPUT,ddos, 1));
        PortController.makePort(new TrianglePort(PortType.INPUT,ddos, 3));
        PortController.makePort(new MaticPort(PortType.INPUT,ddos, 2));
        PortController.makePort(new SquarePort(PortType.OUTPUT, ddos, 1));
        PortController.makePort(new MaticPort(PortType.OUTPUT, ddos, 2));
        PortController.makePort(new TrianglePort(PortType.OUTPUT, ddos, 3));


     /*   Transformer transformer = new Transformer(1000 , 500);
        ComputerController.MakeComputer(transformer);
        PortController.makePort(new SquarePort(PortType.INPUT,transformer, 1));
        PortController.makePort(new TrianglePort(PortType.INPUT,transformer, 3));
        PortController.makePort(new MaticPort(PortType.INPUT,transformer, 2));
        PortController.makePort(new SquarePort(PortType.OUTPUT, transformer, 1));
        PortController.makePort(new MaticPort(PortType.OUTPUT, transformer, 2));
        PortController.makePort(new TrianglePort(PortType.OUTPUT, transformer, 3));
*/

    }
}