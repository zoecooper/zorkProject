/**
 * Allows the player to be able to view his/her score in the game.
 * @author NC
 */
class ScoreCommand extends Command{
	ScoreCommand(){
	}

	/**
	 * Executes the command to return the player's current score along with a specific string
	 * to correlate with the specific score.
	 */
       public String execute(){
	       int score = GameState.instance().getScore();
	       String rank;
	       if(score<=24){
		       rank = "Loser";
	       }
	       else if(score<=49){
		       rank = "Beginning Player";
	       }
	       else if(score<=74){
		       rank = "Intermediate Player";
	       }
	       else if(score<=99){
		       rank = "Advanced Player";
	       }
	       else{
	
		     rank = "Expert";
	       }
	       return "You have accumulated " + score + "points. This gives you a rank of " + rank;
       
       }	


}	
