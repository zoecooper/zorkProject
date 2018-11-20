
class LookCommand extends Command {

    /** Allows the player to see the full description of the current room after the initial showing.
     */
    LookCommand() {
    }

    /** Executes the command to return the description upon the user typng the command.
     */ 
    public String execute() {
        Room currRoom = GameState.instance().getAdventurersCurrentRoom();
        return "\n" + currRoom.describe(true) + "\n";
    }
}
