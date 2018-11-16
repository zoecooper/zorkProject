
class MovementCommand extends Command {


    /**Allows player to move in the direction desired by typing the direction commands "w","e","n","s","u", or "d".
     */ 
    private String dir;    

    MovementCommand(String dir) {
        this.dir = dir;
    }


	
    /**Execute the command for the player to move through the game with directions.
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
