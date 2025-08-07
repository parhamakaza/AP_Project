package model.computer;

public class DDOS extends Computer{
    public DDOS(double x , double y , String id){
        super(x,y,id);
        this.computerType = ComputerTypes.DDOS;
    }

}
