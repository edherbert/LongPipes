
import java.util.Scanner;
import pipes.RequirementsInfo;

/**
 * A class to obtain pipe requirements from the terminal.
 * @author edward
 */
public class TerminalInput {
    
    public TerminalInput(){
        
    }
    
    public RequirementsInfo getRequirementsInfo(){
        RequirementsInfo info = new RequirementsInfo();
        Scanner s = new Scanner(System.in);
        
        System.out.println("Please enter the information about your pipe.");
        
        System.out.println("Length: ");
        info.setLength(s.nextDouble());
        
        System.out.println("Outer Diameter: ");
        info.setOuterDiameter(s.nextDouble());

        System.out.println("Plastic Grade (1-5): ");
        info.setPlasticGrade(s.nextByte());
        
        System.out.println("Colour Print: ");
        info.setColourPrint(s.nextByte());
        
        System.out.println("Inner Insulation: ");
        info.setInnerInsulation(s.nextBoolean());
        
        System.out.println("Outer Reinforcement: ");
        info.setOuterReinforcement(s.nextBoolean());

        System.out.println("Chemical Resistance: ");
        info.setChemicalResistance(s.nextBoolean());
        
        return info;
    }
    
}
