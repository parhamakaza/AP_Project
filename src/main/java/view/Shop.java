package view;

import javafx.animation.PauseTransition;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox; // <-- Switched to VBox
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import manager.LevelManager;
import manager.ShopManager;

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
        shop.atarButton.setOnAction(e2 -> { if (LevelManager.lvl.coins >= 3) { atar(); shopStage.close(); } });
        shop.airyamanButton.setOnAction(e3 -> { if (LevelManager.lvl.coins >= 4) { airyaman(); shopStage.close(); } });
        shop.anahitaButton.setOnAction(e4 -> { if (LevelManager.lvl.coins >= 5) { anahita(); shopStage.close(); } });
        shop.aergiaButton.setOnAction(e5 -> { if (LevelManager.lvl.coins >= 10) { aergia(); shopStage.close(); } });
        shop.sisyphusButton.setOnAction(e6 -> { if (LevelManager.lvl.coins >= 15) { sisyphus(); shopStage.close(); } });
        shop.eliphasButton.setOnAction(e7 -> { if (LevelManager.lvl.coins >= 20) { eliphas(); shopStage.close(); } });

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

    // --- Purchase Logic Methods (No changes here) ---
    public static void atar() { /* ... */ }
    public static void airyaman() { /* ... */ }
    public static void anahita() { /* ... */ }
    public static void aergia() { /* ... */ }
    public static void sisyphus() { /* ... */ }
    public static void eliphas() { /* ... */ }

    private void closeShop() { /* ... */ }
}