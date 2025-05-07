package controler;

import model.Level;
import model.Server;

import java.util.Timer;
import java.util.TimerTask;

public class LevelsController {
    public static Level lvl;
    public static boolean paused;
    public static void start(Server server){
        LevelsController.paused=false;
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                ServerControler.makePacket(server);

            }
        };
        timer.scheduleAtFixedRate(task, 0, 3000);




    }
    public static void recivelvl(Level lvl){
        LevelsController.lvl = lvl;
    }

}
