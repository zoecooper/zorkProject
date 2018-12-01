import java.io.*;

public class WinEvent extends Event {

	/**
	 * Message for the end of the game
	 */
	private String winMessage = "You have won! Your game has been now saved";
 	
	/**
	 * This is the end of the game, in which 
	 * the player has won. 
	 * WinEvent will happen when the player reaches 
	 * a certain score.
	 * @param w win message to print to the ccnsole
	 */ 

	public WinEvent() {
	}

	public String execute() {
		GameState instance = GameState.instance();
		try{
			instance.store(instance.DEFAULT_SAVE_FILE);
		}catch (IOException e) {}
		System.out.println(winMessage + instance.DEFAULT_SAVE_FILE);
		return "Congratulation, you have won!";
	}
	

}	
