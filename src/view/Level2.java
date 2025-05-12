package view;

import controler.LevelsController;
import controler.SystemController;
import model.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Level2 extends Level{

        public Level2 (double x){

            this.wireLength.set(x);

        }


        public static void startLevel2(){

            Level lvl = new view.Level1(13000);

            LevelsController.lvl = lvl;

            Main.theStage.setScene(lvl.scene);

            ArrayList<Port> ports = new ArrayList<>();
            ports.add(new TrianglePort(PortType.OUTPUT,1));
            ports.add(new SquarePort(PortType.OUTPUT,2));
            ports.add(new SquarePort(PortType.OUTPUT,3));

            Server server1 = new Server(ports ,lvl);
            SystemController.drawServers(lvl.root,500,600,server1);

            lvl.startButton.setOnAction(e -> LevelsController.start(server1));

            ArrayList<Port> ports2 = new ArrayList<>();
            //ports2.add(new SquarePort(PortType.INPUT,1));
            ports2.add(new SquarePort(PortType.INPUT,2));
            ports2.add(new SquarePort(PortType.INPUT,3));
            //ports2.add(new SquarePort(PortType.OUTPUT,1));
            ports2.add(new SquarePort(PortType.OUTPUT,2));
            ports2.add(new SquarePort(PortType.OUTPUT,3));

            Gsystem system1 = new Gsystem(ports2 , lvl);
            SystemController.drawServers(lvl.root,800,600,system1);

            ArrayList<Port> ports21 = new ArrayList<>();
            //ports2.add(new SquarePort(PortType.INPUT,1));
            ports21.add(new TrianglePort(PortType.INPUT,2));
            //ports21.add(new SquarePort(PortType.INPUT,3));
            //ports21.add(new SquarePort(PortType.OUTPUT,1));
            ports21.add(new SquarePort(PortType.OUTPUT,2));
            //ports21.add(new SquarePort(PortType.OUTPUT,3));


            //Gsystem system2 = new Gsystem(ports21 , lvl);
            //SystemController.drawServers(lvl.root,800,800,system2);


            ArrayList<Port> ports3 = new ArrayList<>();
            ports3.add(new TrianglePort(PortType.INPUT,1));
            ports3.add(new SquarePort(PortType.INPUT,2));
            ports3.add(new SquarePort(PortType.INPUT,3));

            Server server2 = new Server(ports3, lvl);
            SystemController.drawServers(lvl.root,1100,600,server2);

            lvl.comps.addAll(Arrays.asList(server1,server2,system1));

            lvl.menuButton.setOnAction(e -> {

                LevelsController.paused = true;
                LevelsController.pauseLvl(lvl);
                Menu.menuConfig();

            });
            LevelsController.checkForCollison();





        }
    }

