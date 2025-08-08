package manager.packets;

import javafx.animation.PauseTransition;
import javafx.util.Duration;
import manager.GameManager;
import model.computer.Computer;
import model.packet.Packet;
import model.wire.Wire;

import java.util.HashMap;
import java.util.Map;

public class ConfidentialPacketManager extends PacketManager {
    private Map<Packet, Double> distanceMap = new HashMap<>();

    public ConfidentialPacketManager(Packet packet, Wire path) {
        super(packet, path);
    }

    @Override
    public void handle(long now) {
        setSpeed();
        if (packet.isVpn()) {
            setStatus();
        }
        super.handle(now);
    }

    private boolean destinationComputerIsEmpty() {
        Computer computer = wire.ePort.computer;
        return computer.packets.isEmpty();
    }

    private void setSpeed() {
        if (destinationComputerIsEmpty()) {
            speed = Packet.STANDARDSPEED;
        } else {
            speed = Packet.STANDARDSPEED / 2;
        }

    }

  /*  private void setStatus() {
        for (Packet packet1 : LevelManager.lvl.packets) {
            double distance = distance(packet1, packet);
            boolean tooClose = distance < 45;
            if (packet1 != packet && tooClose && !packet1.insideSystem) {
                double prevDistance = distanceMap.getOrDefault(packet1, Double.MAX_VALUE);
                if (distance < prevDistance) {
                    currentState = PacketState.RETURNING;
                }
            distanceMap.put(packet1, distance);
                return;
            }
            currentState = PacketState.FORWARD;
        }
    }*/

  private void setStatus() {
      for (Packet packet1 : GameManager.lvl.packets) {
          double distance = distance(packet1, packet);
          boolean tooClose = distance < 45;
          if (packet1 != packet && tooClose && !packet1.insideSystem) {
              double prevDistance = distanceMap.getOrDefault(packet1, Double.MAX_VALUE);
              if (distance < prevDistance) {
                  currentState = PacketState.RETURNING;
                  distanceMap.put(packet1, distance);
                  scheduleReturnToForward();
              }

          }

      }
  }

    private void scheduleReturnToForward() {

        PauseTransition delay = new PauseTransition(Duration.millis(250));

        delay.setOnFinished(event -> {
            this.currentState = PacketState.FORWARD;
        });

        // Start the countdown.
        delay.play();
    }


    private double distance(Packet packet1, Packet packet2) {
        double x1 = packet1.x;
        double y1 = packet1.y;
        double x2 = packet2.x;
        double y2 = packet2.y;
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));

    }


}
