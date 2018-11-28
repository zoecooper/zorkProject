/**
 * Allows the player to be able to view his/her current health in the game.
 * @author NC
 */
class HealthCommand extends Command {

        /**
	 * Executes the command to return a specific string describing the player's health 
	 * correlating to the number in health the player currently has.
         */
       public String execute(){
        int health = GameState.instance().getHealth();
               String message;
               if(health<=24){
                       message  = "You are about to die.";
               }
               else if(health<=49){
                       message = "You feel a bit light-headed.";
               }
               else if(health<=74){
                       message = "You are OK.";
               }
               else if(health<=99){
                       message = "You are in good condition.";
               }
               else{

                       message= "You are in excellent condition.";
               }
	       return message;
       }
}

