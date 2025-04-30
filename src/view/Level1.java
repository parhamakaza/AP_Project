package view;

import controler.SystemController;
import controler.WireContoroller;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import model.*;
import model.Gsystem;

import java.util.ArrayList;


public class Level1 {
    private  Pane root;
    private Button menuButton = Buttons.makeButton("Menu",200,100,200,80);
    {

        menuButton.setOnAction(e -> Menu.menuConfig());



        root = new Pane();


        root.setStyle("-fx-background-color: #0d1b2a;");
        root.getChildren().add(menuButton);
        Buttons.styler1(menuButton);

    }
    private Scene scene = new Scene(root);


    public static void startLevel1(){

        Level1 lvl = new Level1();

        Main.theStage.setScene(lvl.scene);



        ArrayList<Port> ports = new ArrayList<>();
        ports.add(new SquarePort(PortType.OUTPUT,1));
        ports.add(new TrianglePort(PortType.OUTPUT,2));
        ports.add(new SquarePort(PortType.OUTPUT,3));

        Server server1 = new Server(ports);
        SystemController.drawServers(lvl.root,400,450,server1);


        ArrayList<Port> ports2 = new ArrayList<>();
        ports2.add(new SquarePort(PortType.INPUT,1));
        ports2.add(new TrianglePort(PortType.INPUT,2));
        ports2.add(new SquarePort(PortType.INPUT,3));
        ports2.add(new TrianglePort(PortType.OUTPUT,1));
        ports2.add(new SquarePort(PortType.OUTPUT,2));
        ports2.add(new TrianglePort(PortType.OUTPUT,3));

        Gsystem system1 = new Gsystem(ports2 , lvl.root);
        SystemController.drawServers(lvl.root,700,450,system1);

        ArrayList<Port> ports3 = new ArrayList<>();
        ports3.add(new TrianglePort(PortType.INPUT,1));
        ports3.add(new SquarePort(PortType.INPUT,2));
        ports3.add(new TrianglePort(PortType.INPUT,3));

        Server server2 = new Server(ports3);
        SystemController.drawServers(lvl.root,1000,450,server2);

        try {
            Wire w1 = new Wire(ports.get(1),ports2.get(1));
            WireContoroller.drawWires(w1,lvl.root);
            Wire w2 = new Wire(ports2.get(3),ports3.get(0));
            WireContoroller.drawWires(w2,lvl.root);
            Wire w3 = new Wire(ports2.get(5),ports3.get(2));
            WireContoroller.drawWires(w3,lvl.root);
            Wire w4 = new Wire(ports.get(0),ports2.get(0));
            WireContoroller.drawWires(w4,lvl.root);
            Wire w5 = new Wire(ports2.get(4),ports3.get(1));
            WireContoroller.drawWires(w5,lvl.root);



        } catch (Exception e) {
            java.lang.System.out.println(e.getMessage());
        }

        TrianglePacket packet = new TrianglePacket(ports.get(1));
        packet.movePacket(lvl.root);
        SquarePacket packet1 = new SquarePacket(ports.get(0));
        packet1.movePacket(lvl.root);

        System.out.println("server 1 : " + server1);
        System.out.println("system 1 :" + system1);

















    }




}
