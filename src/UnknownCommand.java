/**
 * Returns a string if the player's command does not match any of the other commands written.
 * @author NC
 */
class UnknownCommand extends Command {
    private String bogusCommand;

    /**
     * Constructor setting the command the user enters to a "bogusCommand"
     */ 
    UnknownCommand(String bogusCommand) {
        this.bogusCommand = bogusCommand;
    }

    /**
     * Executes the command to return the string for the command unknown to the game.
     */ 
    String execute() {
        return "I'm not sure what you mean by \"" + bogusCommand + "\".\n";
    }
}
