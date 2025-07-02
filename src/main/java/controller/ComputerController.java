package controller;

import model.Computer;
import model.Port;
import view.ComputerView;
import view.LevelView;

import java.util.HashMap;

public class ComputerController {
    public static HashMap<Computer, ComputerView> computerMap = new HashMap<>();

    public static ComputerView MakeComputer(Computer computer) {

       ComputerView computerView = new ComputerView(computer);
       computerMap.put(computer,computerView);
       LevelsController.lvl.comps.add(computer);
       return computerView;


    }


}
