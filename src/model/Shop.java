package model;

import controler.LevelsController;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Shop {

    public Button atar = Buttons.makeButton("O' Atar  \n     -3", 200, 100, 220, 250);
    public Button airyaman = Buttons.makeButton("O’ Airyaman \n     -4", 200, 100, 485, 250);
    public Button anahita = Buttons.makeButton("O' Anahita \n     -5", 200, 100, 770, 250);
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
            LevelsController.paused = false;
            LevelsController.resumelvl(LevelsController.lvl);
        });



        Buttons.styler1(shop.atar);
        Buttons.styler1(shop.anahita);
        Buttons.styler1(shop.airyaman);


        shop.root.getChildren().addAll(shop.atar, shop.airyaman, shop.anahita);
        Scene scene = new Scene(shop.root);
        shopStage.setScene(scene);
        shopStage.showAndWait();

    }

}
