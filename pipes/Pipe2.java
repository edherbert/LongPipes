
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
        
        //Check all the aspects of the pipe to find out if it fits the requirements.
        if(req.getPlasticGrade() < 2 || req.getPlasticGrade() > 4) fits = false;
        
        if(req.getColourPrint() == 0 || req.getColourPrint() == 2) fits = false;
        
        if(req.getInnerInsulation()) fits = false;
        if(req.getOuterReinforcement()) fits = false;
        
        //Don't bother checking chemical resistance as it can be set either way and the pipe would still be possible.
        
        return fits;
    }
    
    
    
}
