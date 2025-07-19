package manager.computers;

import controller.PacketContoller;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import model.Type;
import model.computer.Computer;
import model.packet.MassivePacket;
import model.packet.Packet;
import view.Drawable;
import view.packets.PacketView;

import java.util.*;

public class VPNManager extends ComputerManager{
    private static final Random RANDOM_GENERATOR = new Random();
    private static  Map<Packet , InitialPacket> vpnedMap = new HashMap<>();
    private final List<Packet> vpned = new LinkedList<>();
    public VPNManager(Computer computer) {
        super(computer);
    }

    @Override
    public void takePacket(Packet packet){
        super.takePacket(packet);
        makeVpn(packet);


    }

    private void makeVpn(Packet packet){
        if(packet.isVpn()){
            return;
        }
        packet.setVpn(true);
        PacketView packetView =  PacketContoller.packetViewMap.get(packet);
        initialSaving(packetView);

        //packet.setType(getRandomType());

        switch (packet.getType() ){

            case Type.CONFIDENTIAL -> makeConfidentialPacketVpn(packet , packetView);
            case Type.MASSIVE -> makeMassivePacketVpn( (MassivePacket) packet,packetView);
            default -> makeMessengerPacketVpn(packet , packetView);

        }

        vpned.add(packet);

    }

    private void initialSaving(PacketView packetView ){
        Packet packet = packetView.getPacket();
        InitialPacket initialPacket  = new InitialPacket(packet.health, packet.value , packetView.getShape() , packet.getType() );
        vpnedMap.put(packet , initialPacket);


    }

    public static  void resetPacket(Packet packet){
        packet.setVpn(false);
        InitialPacket initialPacket = vpnedMap.get(packet);
        packet.health = chooseHealth(packet.health , initialPacket.health());
        packet.value = initialPacket.value;
        resetPacketShape(packet);

    }

    private static void resetPacketShape(Packet packet){
        PacketView packetView = PacketContoller.packetViewMap.get(packet);
        switch (packet.getType() ){
            case Type.CONFIDENTIAL -> packetView.getShape().setFill(Color.DARKBLUE);
            case Type.MASSIVE -> packetView.getShape().setFill(Color.DARKBLUE);
            default -> packetView.setShape(vpnedMap.get(packet).shape);
        }

    }

    private static int chooseHealth(int health1 , int health2){

        return Math.min(health1,health2);

    }

    public static Type getRandomType() {
        Type[] choices = { Type.SQUARE, Type.TRIANGLE, Type.MATIC };

        int randomIndex = RANDOM_GENERATOR.nextInt(choices.length);


        return choices[randomIndex];
    }

    @Override
    protected void disableComputer(){
        super.disableComputer();
        vpned.removeIf(packet -> {
            resetPacket(packet);
            return true;
        });

    }
    private void makeMassivePacketVpn(MassivePacket packet , PacketView packetView){

        packet.health  = 10;
        packet.value = 10;
        packet.setSize(10);
        packetView.getShape().setFill(Color.LIGHTBLUE);



    }
   private void makeConfidentialPacketVpn(Packet packet , PacketView packetView){
        packet.health  = 6;
        packet.value = 4;
        packetView.getShape().setFill(Color.GRAY);

    }

    private void makeMessengerPacketVpn(Packet packet , PacketView packetView){
        packet.health  *= 2;
        packet.value = 5;
        packetView.setShape(Drawable.createVpnShape());

    }

    private record InitialPacket(int health , int value , Shape shape , Type type){}


}
