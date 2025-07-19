package manager.computers;

import model.computer.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


public class ComputerManagerFactory {
    private static final Map<Class<? extends Computer>, Function<Computer, ComputerManager>> managerRegistry = new HashMap<>();
    static {

        managerRegistry.put(Transformer.class, comp -> new TransformerManager((Transformer) comp));
        managerRegistry.put(Spy.class,         comp -> new SpyManager((Spy) comp));
        managerRegistry.put(Server.class,      comp -> new ServerManager((Server) comp));
        managerRegistry.put(DDOS.class , comp -> new DDOSManager((DDOS)comp));
        managerRegistry.put(AntiVirus.class , comp -> new AntiVirusManager((AntiVirus)comp));
        managerRegistry.put(VPN.class , comp -> new VPNManager((VPN)comp));
        managerRegistry.put(Merger.class , comp -> new MergerManager((Merger)comp));
        managerRegistry.put(Distributor.class , comp -> new DistributorManager((Distributor)comp));

    }

    public static ComputerManager createManager(Computer computer) {

        Function<Computer, ComputerManager> managerCreator = managerRegistry.get(computer.getClass());
        return managerCreator.apply(computer);


    }


    }
