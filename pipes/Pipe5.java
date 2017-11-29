
package pipes;

/**
 * The last type of pipe.
 * @author NarupornNoidith
 */
public class Pipe5 extends Pipe{
    public Pipe5(){
        
    }
    
    public boolean fitsRequirements(RequirementsInfo req){
        boolean fits = true;
        
        if(req.getPlasticGrade() < 3 || req.getPlasticGrade() > 5) fits = false;
        
        if(req.getColourPrint() == 0 || req.getColourPrint() == 1) fits = false;
        
        if(req.getInnerInsulation()) fits = true;
        if(req.getOuterReinforcement()) fits = true;
        
        return fits;
    }
    
    
    
}

