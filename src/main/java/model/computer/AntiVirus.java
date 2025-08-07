package model.computer;

public class AntiVirus extends Computer {
    public AntiVirus(double x , double y, String id){
        super(x,y,id);
        this.computerType = ComputerTypes.ANTIVIRUS;
    }
}
