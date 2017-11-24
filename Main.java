import System.Order;
import System.Session;
import pipes.RequirementsInfo;

class Main{
    public static void main(String args[]){
        //TerminalInput in = new TerminalInput();
        
        //Most of these objects are just for testing.
        //The current session is stored in here.
        Session s = new Session();
        
        //Create the requirements and set it as the requirements for the order.
        RequirementsInfo requirements = new RequirementsInfo();
        requirements.setLength(10);
        requirements.setOuterDiameter(10);
        requirements.setChemicalResistance(true);
        
        //create an order within the session. This is how orders should be created.
        Order order1 = s.createOrder();
        order1.setRequirementsEqualTo(requirements);
        
        Order order2 = s.createOrder();
        requirements.setLength(0.33);
        order2.setRequirementsEqualTo(requirements);
        
        //Work out the total cost for the session.
        System.out.println("Total cost for session: " + s.getSessionTotal());
    }   
}
