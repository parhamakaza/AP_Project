package service;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.HashMap;


public class File {
    private static HashMap<String , Double> configHashMap = readMapFromFile();

    private static void writeToFile() {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter("src/main/resources/config.json")) {

            gson.toJson(configHashMap, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static HashMap<String,Double> readMapFromFile() {
        Gson gson = new Gson();

        // Use a try-with-resources block to ensure the reader is closed.
        try (Reader reader = new FileReader("src/main/resources/config.json")) {
            // Define the specific type of the HashMap using TypeToken.
            // This is crucial for Gson to understand the generic types <String, Double>.
            Type mapType = new TypeToken<HashMap<String, Double>>() {}.getType();

            // Deserialize the JSON from the reader into the specified map type.
            configHashMap = gson.fromJson(reader, mapType);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return configHashMap;
    }
    public static  void putToMap (String s , double d){
        configHashMap.put(s,d);
        writeToFile();
    }

    public static double getFromMap(String s ){
        return configHashMap.get(s);
    }


}
