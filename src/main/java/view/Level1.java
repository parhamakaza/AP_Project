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
        PortController.makePort(new TrianglePort(PortType.OUTPUT, sServer, 1 , "s1f"));
        PortController.makePort(new SquarePort(PortType.OUTPUT, sServer, 2, "s2f"));
        PortController.makePort(new MaticPort(PortType.OUTPUT, sServer, 3, "s3f"));

        Server endServer = new Server(1700 , 500 , "b");
        ComputerController.MakeComputer(endServer);
        //PortController.makePort(new TrianglePort(PortType.INPUT, endServer, 1, "s4f"));
        PortController.makePort(new SquarePort(PortType.INPUT, endServer, 2, "s5f"));
        PortController.makePort(new MaticPort(PortType.INPUT, endServer, 3, "s6f"));

        Distributor distribuitor = new Distributor(500 , 500, "vieajviern");
        ComputerController.MakeComputer(distribuitor);
        PortController.makePort(new SquarePort(PortType.INPUT, distribuitor, 2, "s5fsffvsvd"));
        PortController.makePort(new SquarePort(PortType.OUTPUT, distribuitor, 2, "s2fcac"));
        PortController.makePort(new TrianglePort(PortType.OUTPUT, distribuitor, 1 , "s1vswd"));
        PortController.makePort(new TrianglePort(PortType.INPUT, distribuitor, 1 , "s1vswfwd"));
        PortController.makePort(new MaticPort(PortType.OUTPUT, distribuitor, 3, "s3wfeff"));
        PortController.makePort(new MaticPort(PortType.INPUT, distribuitor, 3, "s3fewff"));



        Transformer vpn = new Transformer(900 , 500, "c");
        ComputerController.MakeComputer(vpn);
        PortController.makePort(new SquarePort(PortType.INPUT, vpn, 2, "s5fsffweffvsvd"));

        PortController.makePort(new TrianglePort(PortType.OUTPUT, vpn, 1 , "s1vswd"));
        PortController.makePort(new TrianglePort(PortType.INPUT, vpn, 1 , "s1vsewfwfwd"));
        PortController.makePort(new MaticPort(PortType.OUTPUT, vpn, 3, "s3wfefweff"));
        PortController.makePort(new MaticPort(PortType.INPUT, vpn, 3, "s3fewwfwff"));

        Spy spy1 = new Spy(1100 , 500, "d");
        ComputerController.MakeComputer(spy1);
        PortController.makePort(new SquarePort(PortType.INPUT, spy1, 2, "s21f4"));
        PortController.makePort(new SquarePort(PortType.OUTPUT, spy1, 2, "sfqfwesxa"));

        Spy spy = new Spy(1100 , 300, "gad");
        ComputerController.MakeComputer(spy);
        PortController.makePort(new SquarePort(PortType.OUTPUT , spy , 2 , "nanrgnoei"));

        DDOS ddos = new DDOS(1100, 700 , "fafa" );
        ComputerController.MakeComputer(ddos);
        PortController.makePort(new MaticPort(PortType.INPUT, ddos, 2, "s3fewwfwff"));
        PortController.makePort(new SquarePort(PortType.OUTPUT, ddos, 2, "s2fcawfwefefwc"));

        AntiVirus antiVirus = new AntiVirus(1300 , 500 , "fwniofwa");
        ComputerController.MakeComputer(antiVirus);
        PortController.makePort(new SquarePort(PortType.INPUT, antiVirus, 1, "s5fswdffvwdwsvd"));
        PortController.makePort(new MaticPort(PortType.INPUT, antiVirus, 3, "s3fewwfdwdwff"));
        PortController.makePort(new SquarePort(PortType.OUTPUT, antiVirus, 2, "s5fsffdwdweffvsvd"));

        Merger merger = new Merger(1500 , 500 , "mergersfs");
        ComputerController.MakeComputer(merger);
        PortController.makePort(new MaticPort(PortType.INPUT, merger, 3, "s3fewwfdwdwff"));
        PortController.makePort(new SquarePort(PortType.OUTPUT, merger, 2, "s5fsffdwdweffvsvd"));


    }
}