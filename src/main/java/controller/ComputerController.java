package controller;

import manager.LevelManager;
import manager.TransfererManager;
import model.computer.Computer;
import model.computer.Transferer;
import view.ComputerView;

import java.util.HashMap;

public class ComputerController {
    public static HashMap<Computer, ComputerView> computerMap = new HashMap<>();

    public static ComputerView MakeComputer(Computer computer) {

       ComputerView computerView = new ComputerView(computer);
       computerMap.put(computer,computerView);
       LevelManager.lvl.comps.add(computer);

       if(computer instanceof Transferer){
           Transferer transferer = (Transferer)computer;
           new TransfererManager(transferer);
       }
       return computerView;


    }


}
