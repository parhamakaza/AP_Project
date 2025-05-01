package controler;

import model.*;
import view.Level1;

public  class ServerControler {
    public static void takePacket(Server server, Packet packet){
        if(packet instanceof TrianglePacket){
            Level1.coins += 2;
        }

        if(packet instanceof SquarePacket){
            Level1.coins++;
        }
        System.out.println(Level1.coins);
        packet = null;
    }
    public static void makePacket(Server server) {
        for(Port i : server.ports){

            if(i instanceof SquarePort){
                SquarePacket sq = new SquarePacket(i,server.root);
                sq.movePacket(server.root);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            if(i instanceof TrianglePort){
                TrianglePacket tri = new TrianglePacket(i,server.root);

                tri.movePacket(server.root);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }


}
