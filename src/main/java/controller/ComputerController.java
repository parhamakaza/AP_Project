package controller;

import manager.computers.ComputerManagerFactory;
import manager.LevelManager;
import model.computer.Computer;
import view.ComputerView;

import java.util.HashMap;
import java.util.Map;

public class ComputerController {
    public static Map<Computer, ComputerView> computerViewMap = new HashMap<>();


    public static ComputerView MakeComputer(Computer computer) {
       ComputerView computerView = new ComputerView(computer);
       computerViewMap.put(computer,computerView);
       LevelManager.lvl.comps.add(computer);
       ComputerManagerFactory.createManager(computer);
       return computerView;


    }




}
