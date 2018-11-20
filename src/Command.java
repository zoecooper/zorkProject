/**
 * The parent class of all the commands sub-classes
 * which interacts with user's input and is parsed by 
 * the CommandFactory class. 
 * @author AN
 */
abstract class Command {
    /**
     * Executes the command.
     * @return a string describing where you went and what you did
     */	
    abstract String execute();

}
