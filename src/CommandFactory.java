import java.util.List;
import java.util.Arrays;
/**
 * Takes in the users commands as strings, parses them and 
 * tries to determine what the player is trying to accomplish. 
 * If parsed the commands are matched to the valid sub-command 
 * classes, they are created and executed. 
 * It returns a string to be printed out, letting the player know the 
 * results of their actions and inputs.
 * 
 * @author AN
 */ 
public class CommandFactory {

    private static CommandFactory theInstance;
    /**
     * List of all the possible command which the player can use
     */ 
    public static List<String> MOVEMENT_COMMANDS = 
        Arrays.asList("n","w","e","s","u","d" );
    /**
     * @return creates an instance of the CommandFactory if one is not 
     * already created. 
     */ 
    public static synchronized CommandFactory instance() {
        if (theInstance == null) {
            theInstance = new CommandFactory();
        }
        return theInstance;
    }

    private CommandFactory() {
    }
    /**
     * Receives the users command and attempts to parse it into Strings 
     * which are stored in an array.
     * There is a multiple part command composed of a verb "key word" 
     * and at least one noun. Multiple worded nouns are concatenated into
     * a single noun and then parsed by the command code. 
     * @param command
     * @return the specific command to execute
     */    	
    public Command parse(String command) {
        String parts[] = command.split(" ");
        String verb = parts[0];
        String noun = parts.length >= 2 ? parts[1] : "";
        if (verb.equals("look")) {
            return new LookCommand();
        }
        if (verb.equals("save")) {
            return new SaveCommand(noun);
        }
        if (verb.equals("take")) {
            return new TakeCommand(noun);
        }
        if (verb.equals("drop")) {
            return new DropCommand(noun);
        }
        if (verb.equals("i") || verb.equals("inventory")) {
            return new InventoryCommand();
        }
        if (MOVEMENT_COMMANDS.contains(verb)) {
            return new MovementCommand(verb);
        }
        if (parts.length == 2) {
            return new ItemSpecificCommand(verb, noun);
        }
        return new UnknownCommand(command);
    }
}
