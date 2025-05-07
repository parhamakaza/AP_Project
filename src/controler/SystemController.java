package controler;

import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.*;



import static model.Computer.*;

public class SystemController {
    private static double mouseOffsetX;
    private static double mouseOffsetY;

    //private final SystemManager systems = new SystemManager();
    public static void drawServers(Pane root, double x, double y,  Computer system){



        Rectangle module = new Rectangle(WIDTH , HEIGHT);
        module.setX(0);
        module.setY(0);
        module.setLayoutX(x);
        module.setLayoutY(y);
        system.x = x;
        system.y = y;
        for(Port i : system.ports){
            if(i instanceof SquarePort){
                PortsController.drawSquarePort( (SquarePort) i , root);
            }else if(i instanceof TrianglePort){
                PortsController.drawTrianglePort((TrianglePort) i , root);
            }

            if(i.wire != null){
                WireContoroller.drawWires(i.wire,root);
            }
        }


        module.setArcWidth(10);   // rounded corners
        module.setArcHeight(10);

        module.setFill(Color.web("#6e6e6e"));   // mid‑gray fill
        module.setStroke(Color.web("#4a4a4a")); // slightly darker border
        module.setStrokeWidth(2);

        // optional: subtle glow/drop‑shadow
        system.shape=module;
        DropShadow glow = new DropShadow(10, Color.web("#00ffff"));
        glow.setSpread(0.2);
        module.setEffect(glow);

        root.getChildren().add(module);
        module.toBack();


    }

}
