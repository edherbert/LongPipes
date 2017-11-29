
package pipes;

/**
 * The second type of pipe.
 * @author NarupornNoidith
 */
public class Pipe2 extends Pipe{
    public Pipe2(){
        
    }
    
    public boolean fitsRequirements(RequirementsInfo req){
        boolean fits = true;
        
        if(req.getPlasticGrade() < 2 || req.getPlasticGrade() > 4) fits = false;
        
        if(req.getColourPrint() == 0 || req.getColourPrint() == 2) fits = false;
        
        if(req.getInnerInsulation()) fits = false;
        if(req.getOuterReinforcement()) fits = false;
        
        return fits;
    }
    
    
    
}
