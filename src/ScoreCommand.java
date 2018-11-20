/**
 * Allows the player to be able to view his/her score in the game.
 * @author NC
 */
class ScoreCommand extends Command{
	private int score;

	/**
	 * Constructor setting the player's score.
	 */ 
	ScoreCommand(int score) {
		this.score = score;
	}
	/**
	 * Return's the player's current score.
	 */ 
	int getScore(){
		return score;
	}

	/**
	 * Executes the command to return the player's current score along with a specific string
	 * to correlate with the specific score.
	 */
       public String execute(){
       return "Nadiya";
       }	


}	
