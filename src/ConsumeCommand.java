/**
 * Allows player to consume an item to increase his/her health.
 * @author NC
 */


class ConsumeCommand extends Command {
	private String itemName;
	/**
	 * A contructor setting the item name of the item wanting to be eaten.
	 */ 
    	ConsumeCommand(String itemName) {
        	this.itemName = itemName;
    	}
	/**
	 * Executes the command for the player to eat the item, also allowing the player's
	 * health to increase by the amount of points assigned to that specific food item.
	 * The item also is removed from the player's inventory.
	 */ 
	public String execute(){

		return "Nadiya";
	}
}	
