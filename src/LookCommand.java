
class LookCommand extends Command {

    /** Returns the full description of the current room after the user types "Look".
     */
    LookCommand() {
    }

    /** Executes the command to return the description.
     */ 
    public String execute() {
        Room currRoom = GameState.instance().getAdventurersCurrentRoom();
        return "\n" + currRoom.describe(true) + "\n";
    }
}
