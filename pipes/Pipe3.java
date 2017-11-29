
package pipes;

/**
 * The third type of pipe.
 * @author NarupornNoidith
 */
public class Pipe3 extends Pipe{
    public Pipe3(){
        
    }
    
    public boolean fitsRequirements(RequirementsInfo req){
        boolean fits = true;
        
        if(req.getPlasticGrade() < 2 || req.getPlasticGrade() > 5) fits = false;
        
        if(req.getColourPrint() == 0 || req.getColourPrint() == 1) fits = false;
        
        if(req.getInnerInsulation()) fits = false;
        if(req.getOuterReinforcement()) fits = false;
        
        return fits;
    }
    
    
    
}
