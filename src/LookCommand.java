
class LookCommand extends Command {

    LookCommand() {
    }

    /** Returns the full description of the current room after the user types "Look".
     * @author NC
     */ 
    public String execute() {
        Room currRoom = GameState.instance().getAdventurersCurrentRoom();
        return "\n" + currRoom.describe(true) + "\n";
    }
}
