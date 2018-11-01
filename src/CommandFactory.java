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
	      
	if(command.contains(" ")){       
	String parts[] = command.split(" ");
	verb = parts[0];
	noun = parts[1];  
	}
	else{
	verb = command; 
	noun = ""; 
	} 
	if(command.contains("take")){
                       return new TakeCommand(noun);
               }
	else if(command.contains("drop")){
		return new DropCommand(noun);
	}
	else if(command.equals("look")){
		return new LookCommand();
	}
	else if(command.equals("i")){
		return new InventoryCommand();
	}
	
	else if(MOVEMENT_COMMANDS.contains(verb)) {
		return new MovementCommand(verb);
	}
	else if(command.contains(" ")){
		return new ItemSpecificCommand(verb, noun);
	}
	
	return new UnknownCommand(command);
    }
}   

