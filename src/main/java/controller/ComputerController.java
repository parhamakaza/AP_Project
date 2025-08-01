package controller;

import manager.GameManager;
import manager.computers.ComputerManagerFactory;
import model.computer.Computer;
import view.ComputerView;

import static controller.ComponentsController.TheComponentsController;

public class ComputerController {


    public static ComputerView MakeComputer(Computer computer) {
       ComputerView computerView = new ComputerView(computer);
       TheComponentsController.computerViewMap.put(computer,computerView);
       GameManager.lvl.comps.add(computer);
       ComputerManagerFactory.createManager(computer);
       return computerView;

    }




}
