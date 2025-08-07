package model.computer;

public class Spy extends Computer{
        public Spy(double x , double y, String id){
            super(x,y,id);
            this.computerType = ComputerTypes.SPY;
        }


}
