package System;

import pipes.Pipe;
import pipes.Pipe1;
import pipes.Pipe2;
import pipes.Pipe3;
import pipes.Pipe4;
import pipes.Pipe5;
import pipes.RequirementsInfo;

/**
 * A class to contain a single order of a pipe.
 * @author edward
 */
public class Order {
    private RequirementsInfo requirements = new RequirementsInfo();
    
    //Static instances of each type of pipe.
    //These are just copies at the moment

    private static final Pipe1 pipe1 = new Pipe1();
    private static final Pipe2 pipe2 = new Pipe2();
    private static final Pipe3 pipe3 = new Pipe3();
    private static final Pipe4 pipe4 = new Pipe4();
    private static final Pipe5 pipe5 = new Pipe5();
    //An array of these pipes for easy searching.
    static Pipe[] pipeTypes = {pipe1, pipe2, pipe3, pipe4, pipe5};
    
    private double cost = 0d;
    
    private int quantity = 1;
    private boolean possible = true;
    
    String feedbackReasons = "";
    
    //Stores the cost of the plastic (£).
    private static final double plasticCost[] = {0.4, 0.6, 0.75, 0.8, 0.95};
    
    /**
     * Construct an instance of Order. This should not be called by itself. Use session.createOrder() to return an instance of Order.
     */
    public Order(){
        updateOrder();
    }
    
    /**
     * Set the current order requirements equal to the provided requirements.
     * This will make a call to updateOrder().
     * @param requirements What requirements to set. They are set using a copy constructor.
     */
    public final void setRequirementsEqualTo(RequirementsInfo requirements){
        this.requirements = new RequirementsInfo(requirements);
        
        updateOrder();
    }
    
    /**
     * Update the order by checking whether the requirements make sense.
     * This will determine whether the order is possible and should be called when the order's requirements change.
     * If the order is impossible, this method will determine the reason why and set it as a string.
     */
    public void updateOrder(){
        //Reset the feedback variable.
        feedbackReasons = "";
        
        //If the requirements specify a length or diameter of 0 then abort the entire check.
        if(requirements.getLength() <= 0 || requirements.getOuterDiameter() <= 0){
            feedbackReasons += "Pipes must have a length and outer diameter of more than 0.";
            possible = false;
            cost = 0d;
            return;
        }
        
        //Do not allow the user to proceed with a pipe length greater than 6 meters.
        if(requirements.getLength() > 6){
            feedbackReasons += "Pipes cannot have a length greater than 6 meters.";
            possible = false;
            cost = 0d;
            return;
        }
        
        //Go through the pipes and take a reference to it if one is found.
        Pipe p = null;
        for(int i = 0; i < pipeTypes.length; i++){
            //Use the first pipe found that fits the requirements.
            if(pipeTypes[i].fitsRequirements(requirements)){
                p = pipeTypes[i];
                break;
            }
        }
        //If a pipe is found then do this.
        if(p != null){
            possible = true;
            
            calculateCost(p, requirements);
            return;
        }
        
        //If no pipe is found then check why.
        //This bit won't run due to the return in the above if clase if the pipe is found.
        possible = false;
        cost = 0d;
        
        int plasticGrade = requirements.getPlasticGrade();
        //First of all check for the inner insulation.
        if(requirements.getInnerInsulation()){
            //Outer reinforcement can only be selected if it's with insulation, so this can be checked here.
            if(requirements.getOuterReinforcement()){
                //Pipe 5
                if(plasticGrade < 3 || plasticGrade > 5) feedbackReasons += "Inner insulation and outer reinforcement require a plastic grade of 3-5.\n";   
            }else{
                //Pipe 4
                if(plasticGrade < 2 || plasticGrade > 5) feedbackReasons += "Inner insulation requires a plastic grade of 2-5.\n";
            }
            //Inner insulation always requires two colours.
            if(requirements.getColourPrint() < 2) feedbackReasons += "Inner insulation requires 2 colours.\n";
        }
        //Then do the rest of the pipes
        if(!requirements.getInnerInsulation() && !requirements.getOuterReinforcement()){
            //Check for each individual pipe type.
            if(requirements.getColourPrint() == 2){
                if(plasticGrade < 2 || plasticGrade > 5) feedbackReasons += "A plastic grade of 2-5 is required for 2 colours.\n";
            }
            if(requirements.getColourPrint() == 1){
                if(plasticGrade < 2 || plasticGrade > 4) feedbackReasons += "A plastic grade of 2-4 is required for 1 colour.\n";
            }
            if(requirements.getColourPrint() == 0){
                if(plasticGrade < 1 || plasticGrade > 3) feedbackReasons += "A plastic grade of 1-3 is required for no colours.\n";
            }
        }
        if(requirements.getOuterReinforcement() && !requirements.getInnerInsulation()){
            //This is a fringe case, as outer reinforcement must always be paired with inner insulation.
            feedbackReasons += "Outer reinforcement must be paired with inner insulation.\n";
        }
    }
    
    /**
     * A function to calculate the cost of the supplied pipe given specific requirements. This will set the cost for this object.
     * @param pipe The type of pipe to check.
     * @param requirements The requirements to check.
     */
    private void calculateCost(Pipe pipe, RequirementsInfo requirements){
        //TODO: Check if these calculations are accurate. That can't really be done until all the pipes are ready.
        double baseCost = 0d;
        
        //Get the cost of the plastic. -1 to index the array correctly.
        baseCost = plasticCost[requirements.getPlasticGrade() - 1] * calculateArea(requirements);
        
        //Calculate the additional costs.
        //Add all the required percentages and then find the ammount based on the cost.
        double percentageToAdd = 0;
        
        if(requirements.getColourPrint() == 1){
            percentageToAdd += 0.12;
        }else if(requirements.getColourPrint() == 2){
            percentageToAdd += 0.16;
        }
        
        if(requirements.getInnerInsulation()) percentageToAdd += 0.13;
        if(requirements.getOuterReinforcement()) percentageToAdd += 0.17;
        if(requirements.getChemicalResistance()) percentageToAdd += 0.14;
        
        cost = baseCost + (baseCost * percentageToAdd);
        
        //Round both the base cost and final cost to 2dp
        baseCost = Math.floor(baseCost*100)/100;
        cost = Math.floor(cost*100)/100;
    }
    
    /**
     * Calculate the total area of a pipe in inches cubed.
     * @param requirements The requirements of the pipe.
     * @return The total area in inches cubed.
     */
    private double calculateArea(RequirementsInfo requirements){
        //Get the radius from the diameter.
        //The inner diameter is 90% of the outer diameter.
        double innerRadius = (requirements.getOuterDiameter() * 0.9) / 2;
        double outerRadius = requirements.getOuterDiameter() / 2;
        
        //Figure out the length of the pipe in inches.
        //1m = 39.37 inches.
        double lengthInInches = requirements.getLength() * 39.37;
        
        //Calculate the volume with circle area * length
        double outerVolume = (Math.PI * (outerRadius * outerRadius)) * lengthInInches;
        double innerVolume = (Math.PI * (innerRadius * innerRadius)) * lengthInInches;
        
        return outerVolume - innerVolume;
    }
    
    /**
     * Get the base cost of the order (Without the quantity).
     * @return The cost of the order.
     */
    public double getBaseCost(){
        return cost;
    }
    
    /**
     * Get the total cost of the order (With the quantity).
     * @return The cost of the order with quantity.
     */
    public double getTotalCost(){
        return cost * quantity;
    }
    
    /**
     * Get the requirements for this order.
     * @return The requirements.
     */
    public RequirementsInfo getRequirements(){
        return requirements;
    }
    
    /**
     * Get the quantity of the order.
     * @return Quantity
     */
    public int getQuantity(){
        return quantity;
    }
    
    /**
     * Set the quantity of the order. 0 or less or 10 or greater is not allowed.
     * @param quantity 
     */
    public void setQuantity(int quantity){
        if(quantity < 1) this.quantity = 1;
        else if(quantity > 10) this.quantity = 10;
        else this.quantity = quantity;
    }
    
    /**
     * Return whether the order is possible
     * @return possible
     */
    public boolean isPossible(){
        return possible;
    }
    
    /**
     * Get the feedback reasons as a string.
     * @return the feedback reasons.
     */
    public String getFeedback(){
        return feedbackReasons;
    }
}
