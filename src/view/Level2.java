package view;

import controler.LevelsController;
import controler.SystemController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import model.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Level2 extends Level{

        public Level2 (double x){

            this.wireLength.set(x);

        }


        public static void startLevel2(){

            Level lvl = new view.Level1(1300);

            LevelsController.lvl = lvl;

            Main.theStage.setScene(lvl.scene);

            ArrayList<Port> ports = new ArrayList<>();
            ports.add(new SquarePort(PortType.OUTPUT,1));
            ports.add(new TrianglePort(PortType.OUTPUT,2));
            ports.add(new SquarePort(PortType.OUTPUT,3));

            Server server1 = new Server(ports ,lvl.root);
            SystemController.drawServers(lvl.root,500,500,server1);

            lvl.startButton.setOnAction(e -> LevelsController.start(server1));

            ArrayList<Port> ports2 = new ArrayList<>();
            ports2.add(new SquarePort(PortType.INPUT,1));
            ports2.add(new TrianglePort(PortType.INPUT,2));
            ports2.add(new SquarePort(PortType.INPUT,3));
            ports2.add(new TrianglePort(PortType.OUTPUT,1));
            ports2.add(new SquarePort(PortType.OUTPUT,2));
            ports2.add(new TrianglePort(PortType.OUTPUT,3));

            Gsystem system1 = new Gsystem(ports2 , lvl.root);
            SystemController.drawServers(lvl.root,800,500,system1);

            ArrayList<Port> ports3 = new ArrayList<>();
            ports3.add(new TrianglePort(PortType.INPUT,1));
            ports3.add(new SquarePort(PortType.INPUT,2));
            ports3.add(new TrianglePort(PortType.INPUT,3));

            Server server2 = new Server(ports3, lvl.root);
            SystemController.drawServers(lvl.root,1100,500,server2);

            lvl.comps.addAll(Arrays.asList(server1,server2,system1));




        }
    }

