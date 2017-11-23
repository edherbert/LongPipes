import System.Order;
import System.Session;
import pipes.Pipe;
import pipes.Pipe1;
import pipes.RequirementsInfo;

class Main{
    //Static instances of each type of pipe.
    public static Pipe1 pipe1 = new Pipe1();
    
    //An array of these pipes for easy searching.
    static Pipe[] pipeTypes = {pipe1};
    
    public static void main(String args[]){
        //TerminalInput in = new TerminalInput();
        
        //Most of these objects are just for testing.
        //The current session is stored in here.
        Session s = new Session();
        
        //create an order within the session. This is how orders should be created.
        Order order = s.createOrder();
        
        //Create the requirements with it's default values and set it as the requirements for the order.
        RequirementsInfo requirements = new RequirementsInfo();
        order.setRequirementsEqualTo(requirements);
        
        //Try changing the requirements here to see whether the pipe is allowed or not.
        //requirements.setColourPrint((byte)1);
        requirements.printRequirementsInfo();
        
        System.out.println("\n" + pipe1.fitsRequirements(requirements));
    }   
}
