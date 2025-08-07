package saveAndLoad;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.computer.*;
import service.RuntimeTypeAdapterFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static controller.ComponentsController.TheComponentsController;

public class Save {
    private static ScheduledExecutorService scheduler;

    public static void save(){
        if (scheduler == null || scheduler.isShutdown()) {
            scheduler = Executors.newSingleThreadScheduledExecutor();

            // Schedule the saveData method to run every 2 seconds
            // It starts with an initial delay of 0 (runs immediately)
            scheduler.scheduleAtFixedRate(Save::saveData, 0, 2, TimeUnit.SECONDS);
        }
    }

    public static void stop() {
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdown();
        }
    }




    private static void saveData(){

        GameData gameSaveData = new GameData(
                TheComponentsController.computerViewMap,
                TheComponentsController.levelViewMap,
                TheComponentsController.packetViewMap,
                TheComponentsController.portViewMap,
                TheComponentsController.wireViewMap

        );

// 2. Serialize this single object to a file.
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/main/resources/gameSavedData.dat"))) {
            out.writeObject(gameSaveData);
            System.out.println("saving");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
