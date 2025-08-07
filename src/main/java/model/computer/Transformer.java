package model.computer;

public class Transformer extends Computer {



    public Transformer(double x , double y, String id){
        super(x , y,id);
        this.computerType = ComputerTypes.TRANSFORMER;
    }


}
