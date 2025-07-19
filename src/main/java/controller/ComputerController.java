package controller;

import manager.GameManager;
import manager.computers.ComputerManagerFactory;
import model.computer.Computer;
import view.ComputerView;

import java.util.HashMap;
import java.util.Map;

public class ComputerController {
    public static Map<Computer, ComputerView> computerViewMap = new HashMap<>();


    public static ComputerView MakeComputer(Computer computer) {
       ComputerView computerView = new ComputerView(computer);
       computerViewMap.put(computer,computerView);
       GameManager.lvl.comps.add(computer);
       ComputerManagerFactory.createManager(computer);
       return computerView;


    }




}
