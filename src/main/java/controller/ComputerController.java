package controller;

import manager.LevelManager;
import manager.SpyManager;
import model.computer.Computer;
import model.computer.ComputerTypes;
import model.computer.Spy;
import view.ComputerView;

import java.util.HashMap;
import java.util.Map;

public class ComputerController {
    public static Map<Computer, ComputerView> computerMap = new HashMap<>();

    public static ComputerView MakeComputer(Computer computer) {

       ComputerView computerView = new ComputerView(computer);
       computerMap.put(computer,computerView);
       LevelManager.lvl.comps.add(computer);
       if(computer.computerType.equals(ComputerTypes.SPY)){
           SpyManager.spyList.add((Spy) computer);
       }
       return computerView;


    }


}
