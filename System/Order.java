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
     * @param requirements What requirements to set. They are set using a copy constructor.
     */
    public final void setRequirementsEqualTo(RequirementsInfo requirements){
        this.requirements = new RequirementsInfo(requirements);
        
        updateOrder();
    }
    
    public void updateOrder(){
        feedbackReasons = "";
        
        if(requirements.getLength() <= 0 || requirements.getOuterDiameter() <= 0){
            feedbackReasons += "Pipes must have a length and outer diameter of more than 0.";
            possible = false;
            cost = 0d;
            return;
        }
        
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
        possible = false;
        cost = 0d;
        
        int plasticGrade = requirements.getPlasticGrade();
        if(requirements.getInnerInsulation()){
            if(requirements.getOuterReinforcement()){
                if(plasticGrade < 3 || plasticGrade > 5) feedbackReasons += "Inner insulation and outer reinforcement require a plastic grade of 3-5.\n";   
            }else{
                if(plasticGrade < 2 || plasticGrade > 5) feedbackReasons += "Inner insulation requires a plastic grade of 2-5.\n";
            }
            if(requirements.getColourPrint() < 2) feedbackReasons += "Inner insulation requires 2 colours.\n";
        }
        if(!requirements.getInnerInsulation() && !requirements.getOuterReinforcement()){
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
        //BY THE WAY:
        //I'm not sure if this is the correct way to do it, but I've done it so the cost percentages add up.
        //So I'll add all the required percentages and then find the ammount based on the cost.
        double percentageToAdd = 0;
        
        if(requirements.getColourPrint() == 1){
            percentageToAdd += 0.12;
        }else if(requirements.getColourPrint() == 2){
            percentageToAdd += 0.16;
        }
        
        if(requirements.getInnerInsulation()) percentageToAdd += 0.13;
        if(requirements.getOuterReinforcement()) percentageToAdd += 0.17;
        if(requirements.getChemicalResistance()) percentageToAdd += 0.14;
        
        cost = baseCost + baseCost * percentageToAdd;
        
        //Round to 2dp
        baseCost = Math.floor(baseCost*100)/100;
        cost = Math.floor(cost*100)/100;
        
        /*System.out.println("Base cost: " + baseCost);
        System.out.println("Additional percentage: " + percentageToAdd);
        System.out.println("Final Cost: " + cost);*/
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
     * Get the cost of the order
     * @return The cost of the order.
     */
    public double getBaseCost(){
        return cost;
    }
    
    public double getTotalCost(){
        return cost * quantity;
    }
    
    public RequirementsInfo getRequirements(){
        return requirements;
    }
    
    public int getQuantity(){
        return quantity;
    }
    
    public void setQuantity(int quantity){
        if(quantity < 1) this.quantity = 1;
        else if(quantity > 10) this.quantity = 10;
        else this.quantity = quantity;
    }
    
    public boolean isPossible(){
        return possible;
    }
    
    public String getFeedback(){
        return feedbackReasons;
    }
}
