package view;


import controler.LevelsController;
import controler.SystemController;
import javafx.animation.Timeline;
import model.*;
import model.Gsystem;
import java.util.ArrayList;
import java.util.Arrays;



public class Level1 extends Level{


    public Level1 (double x){

        this.wireLength.set(x);

    }


    public static void startLevel1(){

        Level lvl = new Level1(1290);


        LevelsController.lvl = lvl;

        Main.theStage.setScene(lvl.scene);

        ArrayList<Port> ports = new ArrayList<>();
        ports.add(new SquarePort(PortType.OUTPUT,1));
        ports.add(new TrianglePort(PortType.OUTPUT,2));
        ports.add(new SquarePort(PortType.OUTPUT,3));

        Server server1 = new Server(ports ,lvl);
        SystemController.drawServers(lvl.root,500,500,server1);

        lvl.startButton.setOnAction(e -> {
            LevelsController.startTimer();
            LevelsController.start();
            lvl.shopButton.setDisable(false);
            lvl.startButton.setDisable(true);
        });

        ArrayList<Port> ports2 = new ArrayList<>();
        ports2.add(new SquarePort(PortType.INPUT,1));
        ports2.add(new TrianglePort(PortType.INPUT,2));
        ports2.add(new SquarePort(PortType.INPUT,3));
        ports2.add(new TrianglePort(PortType.OUTPUT,1));
        ports2.add(new SquarePort(PortType.OUTPUT,2));
        ports2.add(new TrianglePort(PortType.OUTPUT,3));

        Gsystem system1 = new Gsystem(ports2 , lvl);
        SystemController.drawServers(lvl.root,800,500,system1);

        ArrayList<Port> ports3 = new ArrayList<>();
        ports3.add(new TrianglePort(PortType.INPUT,1));
        ports3.add(new SquarePort(PortType.INPUT,2));
        ports3.add(new TrianglePort(PortType.INPUT,3));

        Server server2 = new Server(ports3, lvl);
        SystemController.drawServers(lvl.root,1100,500,server2);

        lvl.comps.addAll(Arrays.asList(server1,server2,system1));

        lvl.menuButton.setOnAction(e -> {
            LevelsController.lvlOver(lvl);
            LevelsController.paused = true;

            Menu.menuConfig();
        });

        LevelsController.checkForCollison();





    }
}