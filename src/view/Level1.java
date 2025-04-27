package view;

import controler.LevelsController;
import controler.SystemController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import model.*;
import model.System;

import java.util.ArrayList;


public class Level1 {
    private static Pane root;
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
        ports.add(new SquarePort(PortType.INPUT,2));
        ports.add(new SquarePort(PortType.OUTPUT,3));
        ports.add(new TrianglePort(PortType.INPUT,1));
        ports.add(new TrianglePort(PortType.OUTPUT,2));
        ports.add(new TrianglePort(PortType.INPUT,3));




        Server server = new Server(ports);

        SystemController.drawServers(root,600,200,server);
        SystemController.drawServers(root,200,200,server);








    }




}
