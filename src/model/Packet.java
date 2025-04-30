package model;

import javafx.scene.layout.Pane;

public abstract class Packet {
    public Wire wire;
    public Port sPort;
    public Port ePort;

    abstract void movePacket(Pane root);



}
