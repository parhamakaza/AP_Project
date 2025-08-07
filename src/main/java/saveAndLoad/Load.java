package saveAndLoad;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Load {
    public static GameData theLoadedData;
    public static void loadGameData() {
        GameData loadedData = null;

        // Use a try-with-resources block to auto-close the streams
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/main/resources/gameSavedData.dat"))) {

            // 1. Read the object from the file
            // 2. Cast the generic Object to your specific GameData class
            loadedData = (GameData) in.readObject();

            System.out.println("Game data loaded successfully!");

        } catch (ClassNotFoundException e) {
            // This error happens if the class is not found during deserialization
            System.err.println("Error: The GameData class was not found.");
            e.printStackTrace();
        } catch (IOException e) {
            // This handles other file reading errors
            System.err.println("Error reading the save file.");
            e.printStackTrace();
        }

        theLoadedData = loadedData;
        LinkData.link();
    }
}
