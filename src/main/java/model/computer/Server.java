package model.computer;


public class Server extends Computer {



    public Server(double x , double y, String id){
       super(x,y,id);
       this.computerType = ComputerTypes.SERVER;
    }


}
