package model.computer;

public class AntiVirus extends Computer {
    public AntiVirus(double x , double y){
        super(x,y);
        this.computerType = ComputerTypes.ANTIVIRUS;
    }
}
