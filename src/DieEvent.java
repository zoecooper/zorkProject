import java.io.*;

public class DieEvent extends Event {
	private String message = "You have died";

	/**
	 * This is where the game ends, meaning the player
	 * has lost. Die event can be caused when the player
	 * has reached a 0 in their hunger or health status.
	 * It will reload the game from last restore point. 
	 */
        
	public DieEvent() {
	}
	
	public String execute() {
		GameState instance = GameState.instance();
		try {
			instance.restore(instance.DEFAULT_SAVE_FILE + instance.SAVE_FILE_EXTENSION);

		}catch
			(IOException | GameState.IllegalSaveFormatException | Dungeon.IllegalDungeonFormatException e) {}
			System.out.println("You have died :( and are back at your last save point!");
			System.out.println(instance.getAdventurersCurrentRoom().getTitle());
			
			return "You have died.... dont give up!";
		}

 
}
