package view;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import model.wire.Wire;
import model.wire.WireType;
import service.AudioManager;
import service.SceneManager;

public class WireView {
    private Wire wire;

    public void setLine(Line line) {
        this.line = line;
    }

    public void setWire(Wire wire) {
        this.wire = wire;
    }

    public Wire getWire() {
        return wire;
    }

    public Line getLine() {
        return line;
    }

    private Line line;


    public  WireView(Wire wire){
        this.wire = wire;
        Pane root = (Pane) SceneManager.getPrimaryStage().getScene().getRoot();
        double x1 = wire.startX;
        double y1 = wire.startY;
        double x2 = wire.endX;
        double y2 = wire.endY;
        Line line = new Line(x1, y1, x2, y2);
        this.line = line;
        line.setStrokeWidth(3);
        if(wire.type.equals(WireType.SQUARE)){
            line.setStroke(Color.GREEN);
        } else {
            line.setStroke(Color.YELLOW);
        }

        root.getChildren().add(line);
        line.toFront();
        AudioManager.playConnection();

    }
}
