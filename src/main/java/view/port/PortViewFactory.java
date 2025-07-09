package view.port;

import model.port.MaticPort;
import model.port.Port;
import model.port.SquarePort;
import model.port.TrianglePort;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class PortViewFactory {
    private static final Map<Class<? extends Port>, Function<Port, PortView>> managerRegistry = new HashMap<>();

    static {
        managerRegistry.put(SquarePort.class, port -> new SquarePortView(port));
        managerRegistry.put(TrianglePort.class, port -> new TrianglePortView(port));
        managerRegistry.put(MaticPort.class, port -> new MaticPortView(port));

    }

    public static PortView creatPortView(Port port) {

        Function<Port, PortView> managerCreator = managerRegistry.get(port.getClass());
        return managerCreator.apply(port);

    }
}
