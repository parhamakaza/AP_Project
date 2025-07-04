package controller;

import manager.LevelManager;
import model.computer.Computer;
import view.ComputerView;

import java.util.HashMap;

public class ComputerController {
    public static HashMap<Computer, ComputerView> computerMap = new HashMap<>();

    public static ComputerView MakeComputer(Computer computer) {

       ComputerView computerView = new ComputerView(computer);
       computerMap.put(computer,computerView);
       LevelManager.lvl.comps.add(computer);
       return computerView;


    }


}
