package pipes;

/**
 * A class to contain information about the type of pipe required.
 * @author edward
 */
public class RequirementsInfo {
    double length = 0.0d;
    double outerDiameter = 0.0d;
    
    byte plasticGrade = 1;
    byte colourPrint = 0;
    
    boolean innerInsulation = false;
    boolean outerReinforcement = false;
    boolean chemicalResistance = false;
    
    /**
     * Construct an empty requirements info class.
     */
    public RequirementsInfo(){
        
    }

    /**
     * Construct a requirements info class setting parameters.
     * @param length The length of the pipe in metres.
     * @param outerDiameter The outer diameter of the pipe in inches.
     * @param plasticGrade The grade of the plastic.
     * @param colourPrint The number of colours on the pipe (0, 1 or 2).
     * @param innerInsulation Whether or not the pipe needs inner insulation.
     * @param outerReinforcement Whether or not the pipe needs outer reinforcement.
     * @param chemicalResistance Whether or not the pipe needs chemical resistance.
     */

    public RequirementsInfo(double length, double outerDiameter, byte plasticGrade, byte colourPrint, boolean innerInsulation, boolean outerReinforcement, boolean chemicalResistance){
        this.length = length;
        this.outerDiameter = outerDiameter;
        this.plasticGrade = plasticGrade;
        this.colourPrint = colourPrint;
        this.innerInsulation = innerInsulation;
        this.outerReinforcement = outerReinforcement;
        this.chemicalResistance = chemicalResistance;
    }
    
    /**
     * A copy constructor which copies the contents of the passed instance.
     * @param requirements The instance to copy.
     */
    public RequirementsInfo(RequirementsInfo requirements){
        this.length = requirements.length;
        this.outerDiameter = requirements.outerDiameter;
        this.plasticGrade = requirements.plasticGrade;
        this.colourPrint = requirements.colourPrint;
        this.innerInsulation = requirements.innerInsulation;
        this.outerReinforcement = requirements.outerReinforcement;
        this.chemicalResistance = requirements.chemicalResistance;
    }
    
    /**
     * Print the requirements info to the console in a list.
     */
    public void printRequirementsInfo(){
        System.out.println("Length: " + length);
        System.out.println("Outer Diameter: " + outerDiameter);
        System.out.println("Plastic Grade: " + plasticGrade);
        System.out.println("Colour Print: " + colourPrint);
        System.out.println("Inner Insulation: " + innerInsulation);
        System.out.println("Outer Reinforcement: " + outerReinforcement);
        System.out.println("Chemical Resistance: " + chemicalResistance);
        
    }
    
    public double getLength(){
        return length;
    }
    
    public double getOuterDiameter(){
        return outerDiameter;
    }
    
    public byte getPlasticGrade(){
        return plasticGrade;
    }
    
    public byte getColourPrint(){
        return colourPrint;
    }
    
    public boolean getInnerInsulation(){
        return innerInsulation;
    }
    
    public boolean getOuterReinforcement(){
        return outerReinforcement;
    }
    
    public boolean getChemicalResistance(){
        return chemicalResistance;
    }
    
    
    
    public void setLength(double length){
        if(length < 0) length = 0;
        this.length = length;
    }
    
    public void setOuterDiameter(double outerDiameter){
        if(outerDiameter < 0) outerDiameter = 0;
        this.outerDiameter = outerDiameter;
    }
    
    public void setPlasticGrade(byte plasticGrade){
        if(plasticGrade < 0) plasticGrade = 0;
        if(plasticGrade > 5) plasticGrade = 5;
        this.plasticGrade = plasticGrade;
    }
    
    public void setColourPrint(byte colourPrint){
        if(colourPrint < 0) colourPrint = 0;
        if(colourPrint > 2) colourPrint = 2;
        this.colourPrint = colourPrint;
    }
    
    public void setInnerInsulation(boolean innerInsulation){
        this.innerInsulation = innerInsulation;
    }
    
    public void setOuterReinforcement(boolean outerReinforcement){
        this.outerReinforcement = outerReinforcement;
    }
    
    public void setChemicalResistance(boolean chemicalResistance){
        this.chemicalResistance = chemicalResistance;
    }
    
}
