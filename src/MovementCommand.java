/**
 * Allows player to move in the direction desired to enter a room or exit.
 * @author NC
 */
class MovementCommand extends Command { 
    private String dir;    

    MovementCommand(String dir) {
        this.dir = dir;
    }


	
    /**
     * Executes the command for the player to move through the different rooms within 
     * the dungeon according to directions the player enters and returns the 
     * descriptions for the room the player is currently in if the player 
     * has never been to that specific room.
     */ 
    
    public String execute() {
        Room currentRoom = GameState.instance().getAdventurersCurrentRoom();
        Room nextRoom = currentRoom.leaveBy(dir);
        if (nextRoom != null) {  // could try/catch here.
            GameState.instance().setAdventurersCurrentRoom(nextRoom);
            return "\n" + nextRoom.describe() + "\n";
        } else {
            return "You can't go " + dir + ".\n";
        }
    }
}
