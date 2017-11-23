package pipes;

/**
 * The first type of pipe.
 * @author edward
 */
public class Pipe1 extends Pipe{
    public Pipe1(){
        
    }
    
    public boolean fitsRequirements(RequirementsInfo req){
        boolean fits = true;
        
        //Check all the aspects of the pipe to find out if it fits the requirements.
        if(req.getPlasticGrade() < 1 || req.getPlasticGrade() > 3) fits = false;
        
        if(req.getColourPrint() == 1 || req.getColourPrint() == 2) fits = false;
        
        if(req.getInnerInsulation()) fits = false;
        if(req.getOuterReinforcement()) fits = false;
        
        //Don't bother checking chemical resistance as it can be set either way and the pipe would still be possible.
        
        return fits;
    }
}
