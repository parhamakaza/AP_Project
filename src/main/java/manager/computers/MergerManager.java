package manager.computers;

import model.Type;
import model.computer.Computer;
import model.packet.BitPacket;
import model.packet.MassivePacket;
import model.packet.MaticPacket;
import model.packet.Packet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

public class MergerManager extends  ComputerManager{
    private final Map< MassivePacket ,  List<BitPacket> > packetsMap= new HashMap<>();
    private final List<BitPacket> bitPackets = new ArrayList<>();
    public MergerManager(Computer computer) {
        super(computer);
    }

    @Override
    public void takePacket(Packet packet){
        if(packet.getType() != Type.BIT){
            super.takePacket(packet);
        }else {
            takeBitPacket((BitPacket) packet);
        }
    }

    private void takeBitPacket(BitPacket packet){
        packet.insideSystem = true;
        packet.wire.avaible = true;
        bitPackets.add(packet);
        MassivePacket fatherPacket = packet.fatherPacket;
        fatherPacket.reduceSize();
        if(packetsMap.containsKey(fatherPacket)){
            packetsMap.get(fatherPacket).add(packet);
        }else {
            packetsMap.put(fatherPacket , new ArrayList<>(List.of(packet)));
        }

    }


    @Override
    protected Packet choosePacketToSend(){
        for(MassivePacket fatherPacket : packetsMap.keySet()){
            if(fatherPacket.getSize() <= 0){
                fatherPacket.setSize(packetsMap.get(fatherPacket).size());
                packetsMap.remove(fatherPacket);
                return fatherPacket;
            }
        }
        return super.choosePacketToSend();
    }

}
