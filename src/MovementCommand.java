/**
 * Allows player to move in the direction desired to enter a room or exit.
 * @author NC
 */
class MovementCommand extends Command { 
    private String dir;    

    /**
     * Constructor setting the direction the user enters to be able to execute the command.
     */ 
    MovementCommand(String dir) {
        this.dir = dir;
    }


	
    /**
     * Executes the command for the player to move through the different rooms within 
     * the dungeon according to directions the player enters and returns the 
     * descriptions for that specific room the player is currently in if the player 
     * has never been to that specific room. If the direction the player enters does
     * not match any of the directions in the game, it will return "You can't go *dir*."
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
