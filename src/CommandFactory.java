import java.util.List;
import java.util.Arrays;

public class CommandFactory {

    private static CommandFactory theInstance;
    public static List<String> MOVEMENT_COMMANDS = 
        Arrays.asList("n","w","e","s","u","d" );

    public static synchronized CommandFactory instance() {
        if (theInstance == null) {
            theInstance = new CommandFactory();
        }
        return theInstance;
    }

    private CommandFactory() {
    }

    public Command parse(String command) {
	    String verb;
	    String noun;
	       if (command.equals("save")){
                      return new SaveCommand("zork_save");
                      
         
	       }
	String parts[] = command.split("");
    	verb = parts[0];

	noun = parts.length >= 2 ? parts[1] : "";
	if(command.contains("take")){
                       return new TakeCommand(noun);
               }
	if(command.contains("drop")){
		return new DropCommand(noun);
	}
	if(command.equals("lool")){
		return new LookCommand();
	}
	
	if(MOVEMENT_COMMANDS.contains(verb)) {
		return new MovementCommand(verb);
	}
	
	return new UnknownCommand(command);
    }
}   

