/**
 * Is the parent class of all the commands sub-classes
 * which interacts with users input and is parsed by 
 * the CommandFactory class. 
 * @author AN
 */
abstract class Command {
    /**
     * @return a string describing where you went and what you did
     */	
    abstract String execute();

}
