package System;

import pipes.RequirementsInfo;

/**
 * A class to contain a single order of a pipe.
 * @author edward
 */
public class Order {

    /**
     * The requirements info object for this order.
     */
    public RequirementsInfo requirements = new RequirementsInfo();
    
    /**
     * Construct and instance of Order.
     */
    public Order(){
        
    }
    
    public Order(RequirementsInfo requirements){
        setRequirementsEqualTo(requirements);
    }
    
    public void setRequirementsEqualTo(RequirementsInfo requirements){
        this.requirements = new RequirementsInfo(requirements);
    }
}
