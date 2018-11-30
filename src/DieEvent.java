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

		

