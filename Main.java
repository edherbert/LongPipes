import System.Order;
import System.Session;
import pipes.RequirementsInfo;

class Main{
    public static void main(String args[]){
        //TerminalInput in = new TerminalInput();
        
        //The current session can be stored in here.
        Session s = new Session();
        
        Order order = s.createOrder();
        
        RequirementsInfo requirements = new RequirementsInfo();
        order.setRequirementsEqualTo(requirements);
        
    }   
}
