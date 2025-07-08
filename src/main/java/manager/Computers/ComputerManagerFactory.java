package manager.Computers;

import manager.TransformerManager;
import model.computer.Computer;
import model.computer.Server;
import model.computer.Spy;
import model.computer.Transformer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


public class ComputerManagerFactory {
    private static final Map<Class<? extends Computer>, Function<Computer, ComputerManager>> managerRegistry = new HashMap<>();
    static {
        managerRegistry.put(Transformer.class, comp -> new TransformerManager((Transformer) comp));
        managerRegistry.put(Spy.class,         comp -> new SpyManager((Spy) comp));
        managerRegistry.put(Server.class,      comp -> new ServerManager((Server) comp));
    }

    public static ComputerManager createManager(Computer computer) {
        // 3. Look up the creation function using the computer's specific class.
        Function<Computer, ComputerManager> managerCreator = managerRegistry.get(computer.getClass());

        if (managerCreator != null) {
            // 4. If a function was found, use it to create and return the manager.
            return managerCreator.apply(computer);
        }

        // If no creator function was found in the map, throw an exception.
        throw new IllegalArgumentException("No manager found for computer type: " + computer.getClass().getName());
    }


    }
