
class UnknownCommand extends Command {

     /**
     * Returns a string if the player's command does not match any of the other commands written.
     */
    private String bogusCommand;

    UnknownCommand(String bogusCommand) {
        this.bogusCommand = bogusCommand;
    }

    /**
     * Executes the command to return the string for the unknown command.
     */ 
    String execute() {
        return "I'm not sure what you mean by \"" + bogusCommand + "\".\n";
    }
}
