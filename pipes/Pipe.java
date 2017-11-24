package pipes;

/**
 *
 * @author edward
 */
public abstract class Pipe {
    
    /**
     * An abstract class to represent a pipe.
     */
    public Pipe(){
        
    }
    
    /**
     * A method to determine if the supplied requirements can be supplied by this type of pipe.
     * @param req The requirements to check.
     * @return Whether or not the requirements are possible.
     */
    public boolean fitsRequirements(RequirementsInfo req){
        return false;
    }
}
