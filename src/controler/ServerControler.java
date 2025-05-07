package controler;

import model.*;
import view.Level1;

public  class ServerControler {
    public static void takePacket(Server server, Packet packet , Level lvl){
        if(packet instanceof TrianglePacket){
            lvl.coins.set(lvl.coins.get() + 2);
        }

        if(packet instanceof SquarePacket){
            lvl.coins.set(lvl.coins.get() + 1);
        }
        packet = null;
    }
    public static void makePacket(Server server) {
        for(Port i : server.ports) {
            if (LevelsController.paused == false) {
                if (i instanceof SquarePort) {
                    if(i.wire.avaible==true) {
                        SquarePacket sq = new SquarePacket(i, server.root);
                        sq.movePacket(server.root);
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (i instanceof TrianglePort) {
                    if(i.wire.avaible == true) {
                        TrianglePacket tri = new TrianglePacket(i, server.root);
                        tri.movePacket(server.root);
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        }
    }


}
