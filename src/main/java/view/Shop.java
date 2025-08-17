package view;

import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox; // <-- Switched to VBox
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static manager.LevelManager.lvl;

public class Shop {

    // --- Button Declarations ---
    private final Button atarButton;
    private final Button airyamanButton;
    private final Button anahitaButton;
    private final Button aergiaButton;
    private final Button sisyphusButton;
    private final Button eliphasButton;

    private final Pane root = new Pane();

    private Shop() {
        root.setStyle("-fx-background-color: radial-gradient(center 50% 50%, radius 60%, #c866ff4D,#3a2a5c);");

        // Initialize buttons using your styler
        atarButton = UI.createHUDButton2("O' Atar  -3");
        airyamanButton = UI.createHUDButton2("O’ Airyaman  -4");
        anahitaButton = UI.createHUDButton2("O' Anahita  -5");
        aergiaButton = UI.createHUDButton2("Scroll of Aergia  -10");
        sisyphusButton = UI.createHUDButton2("Scroll of Sisyphus  -15");
        eliphasButton = UI.createHUDButton2("Scroll of Eliphas  -20");
    }

    public static void openShop(Event e) {
        Shop shop = new Shop();
        Stage shopStage = new Stage();
        shopStage.initModality(Modality.APPLICATION_MODAL);
        shopStage.setTitle("Shop");
        shopStage.setResizable(false);
        shopStage.setOnCloseRequest(e1 -> shop.closeShop());

        // --- Set a wider width for all buttons ---
        double buttonWidth = 300; // Even wider for a vertical layout
        shop.atarButton.setPrefWidth(buttonWidth);
        shop.airyamanButton.setPrefWidth(buttonWidth);
        shop.anahitaButton.setPrefWidth(buttonWidth);
        shop.aergiaButton.setPrefWidth(buttonWidth);
        shop.sisyphusButton.setPrefWidth(buttonWidth);
        shop.eliphasButton.setPrefWidth(buttonWidth);

        // --- Button Actions (No changes here) ---
        shop.atarButton.setOnAction(e2 -> { if (lvl.coins >= 3) { lvl.getShop().atar(); shopStage.close(); } });
        shop.airyamanButton.setOnAction(e3 -> { if (lvl.coins >= 4) { lvl.getShop().airyaman(); shopStage.close(); } });
        shop.anahitaButton.setOnAction(e4 -> { if (lvl.coins >= 5) { lvl.getShop().anahita(); shopStage.close(); } });
        shop.aergiaButton.setOnAction(e5 -> { if (lvl.coins >= 10) { lvl.getShop().aergia(); shopStage.close(); } });
        shop.sisyphusButton.setOnAction(e6 -> { if (lvl.coins >= 15) { lvl.getShop().sisyphus(); shopStage.close(); } });
        shop.eliphasButton.setOnAction(e7 -> { if (lvl.coins >= 20) { lvl.getShop().eliphas(); shopStage.close(); } });

        // --- Layout using a VBox for a single vertical column ---
        VBox buttonLayout = new VBox(15); // 15px vertical spacing
        buttonLayout.setPadding(new Insets(20));
        buttonLayout.setAlignment(Pos.CENTER);
        buttonLayout.getChildren().addAll(
                shop.atarButton, shop.airyamanButton, shop.anahitaButton,
                shop.aergiaButton, shop.sisyphusButton, shop.eliphasButton
        );

        // Bind the layout's size to the root pane's size
        buttonLayout.prefWidthProperty().bind(shop.root.widthProperty());
        buttonLayout.prefHeightProperty().bind(shop.root.heightProperty());

        shop.root.getChildren().add(buttonLayout);

        // --- Adjust Scene size for the vertical layout ---
        Scene scene = new Scene(shop.root, 400, 500); // Taller and narrower window
        shopStage.setScene(scene);
        shopStage.showAndWait();
    }


    private void closeShop() { }
}