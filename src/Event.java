/**
 * The parent class of all the commands sub-classes
 * which interacts with user's input and is parsed by 
 * the CommandFactory class. 
 * @author ZC
 */
abstract class Event {
        /**
	 * Executes the event command.
	 * @return a string with the event occurrence
	 */

	abstract String execute();

}
