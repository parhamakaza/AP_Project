package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class UI {
    public static void styler1(Button button){
        String style = "-fx-background-color: transparent;"+
                "-fx-border-color: #00b4d8; /* cyan-ish glow */"+
                "-fx-border-width: 2;"+
                "-fx-text-fill: #00b4d8;"+
                "-fx-font-size: 16px;"+
                "-fx-font-weight: bold;"+
                "-fx-padding: 10 20 10 20;"+
                "-fx-background-radius: 5;"+
                "-fx-border-radius: 5;"+
                "-fx-effect: dropshadow(gaussian, #00b4d8, 5, 0.5, 0, 0));";

        button.setStyle(style);
        button.setOnMouseEntered(e -> {
            button.setStyle("-fx-background-color: #00b4d810; ");
        });
        button.setOnMouseExited(e -> {
            button.setStyle(style);
        });

    }

    public static void styler1(Label label){
        label.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #002b36 0%, #003842 50%, #002b36 100%);" +
                "-fx-background-radius: 8;" +
                "-fx-padding: 8 16 8 16;" +
                "-fx-text-fill: #00ffff;" +
                "-fx-font-size: 50px;" +
                "-fx-font-weight: bold;" +
                "-fx-effect: dropshadow(one-pass-box, rgba(0,255,255,0.6), 4, 0.0, 0, 0);" +
                "-fx-alignment: center;" +
                "-fx-border-color: #0088aa;" +
                "-fx-border-width: 2;" +
                "-fx-border-radius: 8;");
    }

    public static Button makeButton (String s ,double w, double h, double lx, double ly){
        Button button = makeButton(s , lx , ly);
        button.setPrefWidth(w);
        button.setPrefHeight(h);


        return button;
    }

    public static Button makeButton (String s, double lx, double ly){
        Button button = new Button();
        button.setText(s);
        double h = 150;
        button.setPrefHeight(h);
        double w = 400 ;
        button.setPrefWidth(470);

        button.setLayoutX(lx - w/2);
        button.setLayoutY(ly - h/2);


        return button;
    }

    public static Button createHUDButton(String text) {
        Button button = new Button(text);
        button.setPrefHeight(40); // Smaller height for HUD
        button.setPrefWidth(120); // Smaller width
        styler1(button); // Reuse the same cool style
        // You might want to adjust font size here if needed
        // button.setStyle(button.getStyle() + "-fx-font-size: 14px;");
        return button;
    }

    public static Label createHUDLabel(String text) {
        Label label = new Label(text);
        // Use a simpler style for HUD labels for readability
        label.setStyle("-fx-text-fill: #00ffff; -fx-font-size: 16px; -fx-font-weight: bold;");
        return label;
    }
    public static Slider styleSlider(Slider slider){
        slider.setStyle("-fx-background-color: linear-gradient(to right, #002b36 0%, #003842 25%, #002b36 75%, #003842 100%); " +
                "-fx-background-insets: 0; " +
                "-fx-background-radius: 4; " +
                "-fx-padding: 4 0 4 0; " +
                "-fx-control-inner-background: transparent; " +        // ensure track shows through
                "-fx-accent: #00ffff; " +                              // the default fill color
                "-fx-focus-color: rgba(0,255,255,0.9,1); " +           // glow when focused
                "-fx-faint-focus-color: rgba(0,255,255,0.4,0.5);"
        );
        return slider;
    }
}
