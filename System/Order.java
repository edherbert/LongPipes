package System;

import pipes.RequirementsInfo;

/**
 * A class to contain a single order of a pipe.
 * @author edward
 */
public class Order {
    private RequirementsInfo requirements = new RequirementsInfo();
    
    /**
     * Construct an instance of Order. This should not be called by itself. Use session.createOrder() to return an instance of Order.
     */
    public Order(){
        
    }
    
    /**
     * Set the current order requirements equal to the passed value.
     * @param requirements What requirements to set. They are set using a copy constructor.
     */
    public final void setRequirementsEqualTo(RequirementsInfo requirements){
        this.requirements = new RequirementsInfo(requirements);
    }
}
