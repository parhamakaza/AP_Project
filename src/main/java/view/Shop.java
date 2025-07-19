package view;

import manager.GameManager;
import manager.ShopManager;
import javafx.animation.PauseTransition;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.packet.Packet;
import model.packet.SquarePacket;
import model.packet.TrianglePacket;

import static manager.GameLoopManager.gameLoopManager;

public class Shop {

    public Button atar = UI.makeButton("O' Atar  \n     -3", 200, 100, 220, 250);
    public Button airyaman = UI.makeButton("O’ Airyaman \n     -4", 200, 100, 485, 250);
    public Button anahita = UI.makeButton("O' Anahita \n     -5", 200, 100, 770, 250);
    Pane root = new Pane();
        {
            root.setStyle("-fx-background-color: #0d1b2a;");
        }

    public static void openShop (Event e){

        Shop shop = new Shop();
        Stage shopStage = new Stage();
        shopStage.initModality(Modality.APPLICATION_MODAL);  // blocks input to other windows
        shopStage.setTitle("Shop");
        shopStage.setWidth(800);
        shopStage.setHeight(500);
        shopStage.setResizable(false);
        shopStage.setOnCloseRequest(e1 -> {
            shop.closeShop();
        });
        shop.atar.setOnAction(e2 -> {
            if(GameManager.lvl.coins >= 3) {
                atar();
                shopStage.close();
                shop.closeShop();

            }

        });
        shop.airyaman.setOnAction(e3 -> {
            if(GameManager.lvl.coins >= 4) {
                airyaman();
                shopStage.close();
                shop.closeShop();


            }

        });
        shop.anahita.setOnAction(e3 -> {
            if(GameManager.lvl.coins >= 5) {
                anahita();
                shopStage.close();
                shop.closeShop();


            }

        });



        UI.styler1(shop.atar);
        UI.styler1(shop.anahita);
        UI.styler1(shop.airyaman);


        shop.root.getChildren().addAll(shop.atar, shop.airyaman, shop.anahita);
        Scene scene = new Scene(shop.root);
        shopStage.setScene(scene);
        shopStage.showAndWait();

    }
    public static void atar(){
        GameManager.lvl.coins = GameManager.lvl.coins - 3;

        ShopManager.atar=true;
        PauseTransition delay = new PauseTransition(Duration.seconds(10));
        delay.setOnFinished(event -> {
            ShopManager.atar=false;


        });
        delay.play();




    }
    public static void airyaman(){
        GameManager.lvl.coins -= 4;

            ShopManager.airyaman=true;
            PauseTransition delay = new PauseTransition(Duration.seconds(5));
            delay.setOnFinished(event -> {
                ShopManager.airyaman=false;


            });
            delay.play();




    }
    public static void anahita(){
        GameManager.lvl.coins -= 5;

        for(Packet i : GameManager.lvl.packets ){
            if(i instanceof TrianglePacket){
                i.health = 3;
            }
            if(i instanceof SquarePacket){
                i.health = 2;
            }

        }





    }

        private void closeShop() {
            gameLoopManager.pauseAndResume(false);
        }
}
