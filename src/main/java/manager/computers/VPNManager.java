package manager.computers;

import controller.ComponentsController;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import model.Type;
import model.computer.Computer;
import model.packet.MassivePacket;
import model.packet.Packet;
import view.Drawable;
import view.packets.PacketView;

import java.util.*;

import static controller.ComponentsController.TheComponentsController;

public class VPNManager extends ComputerManager{

    private static final Random RANDOM_GENERATOR = new Random();
    private static final Map<Packet , InitialPacket> vpnedMap = new HashMap<>();


    public VPNManager(Computer computer) {
        super(computer);
    }

    @Override
    public void takePacket(Packet packet){
        super.takePacket(packet);
        makeVpn(packet);
    }

    private void makeVpn(Packet packet){
        if(packet.isVpn() || packet.getType() == Type.BIT){
            return;
        }

        packet.setVpn(true);
        PacketView packetView =  TheComponentsController.getView(packet);
        initialSaving(packetView);

        //packet.setType(getRandomType());



        turnVpn(packetView);


    }
    private void turnVpn(PacketView packetView){
        Packet packet = packetView.getPacket();

        switch (packet.getType() ){
            case Type.CONFIDENTIAL -> makeConfidentialPacketVpn(packet , packetView);
            case Type.MASSIVE -> makeMassivePacketVpn( (MassivePacket) packet,packetView);
            default -> makeMessengerPacketVpn(packet , packetView);

        }
    }

    private void initialSaving(PacketView packetView ){
        Packet packet = packetView.getPacket();
        InitialPacket initialPacket  = new InitialPacket(packet.getSize() , packet.value , packetView.getShape() , packet.getType());
        vpnedMap.put(packet , initialPacket);


    }

    public static  void resetPacket(Packet packet){
        packet.setVpn(false);
        InitialPacket initialPacket = vpnedMap.get(packet);
        packet.setSize(initialPacket.size);
        packet.value = initialPacket.value;
        packet.checkToKill();
        resetPacketShape(packet);
    }

    private static void resetPacketShape(Packet packet){
        PacketView packetView = TheComponentsController.getView(packet);
        switch (packet.getType() ){
            case Type.CONFIDENTIAL -> packetView.getShape().setFill(Color.DARKBLUE);
            case Type.MASSIVE -> packetView.getShape().setStroke(Color.DARKBLUE);
            default -> packetView.setShape(vpnedMap.get(packet).shape);
        }

    }

    public static Type getRandomType() {
        Type[] choices = { Type.SQUARE, Type.TRIANGLE, Type.MATIC };

        int randomIndex = RANDOM_GENERATOR.nextInt(choices.length);


        return choices[randomIndex];
    }

    @Override
    public void disableComputer(){
        super.disableComputer();
        Set<Packet> packets = vpnedMap.keySet();
        packets.removeIf(packet -> {
            resetPacket(packet);
            return true;
        });
    }

    private void makeMassivePacketVpn(MassivePacket packet , PacketView packetView){

        packet.setSize(10);
        packet.value = 10;
        packetView.getShape().setStroke(Color.LIGHTBLUE);



    }
   private void makeConfidentialPacketVpn(Packet packet , PacketView packetView){
        packet.setSize(6);
        packet.value = 4;
        packetView.getShape().setFill(Color.GRAY);

    }

    private void makeMessengerPacketVpn(Packet packet , PacketView packetView){
        packet.setSize(packet.getNoise() * 2);
        packet.value = 5;
        packetView.setShape(Drawable.createVpnShape());

    }

    private record InitialPacket(int size , int value , Shape shape , Type type){}


}
