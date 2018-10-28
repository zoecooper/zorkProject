
class TakeCommand extends Command {

	private String itemName;
	
	TakeCommand(String itemName) {
		this.itemName = itemName;
	}

	public String execute() {
		if(itemName == null || itemName.trim().length ==0) {
			 return "Take what?\n";
		}
	
	try{
		Room currentRoom = GameState.instance().getAdventurersCurrentRoom();
		Item theItem = currentRoom.getItemNamed(itemName);
		if(theItem.getWeight()>100 || theItem.getWeight() ==1)
			return "you can't carry that.\n";
		GameState.instance().addToInventory(theItem);
		currentRoom.remove(theItem);
		return itemName + "taken.\n";

	}
	catch(Item.NoItemException e) {


	try{
		GameState.instance().getItemFromIneventoryNamed(itemName);
		return "You already have the " + itemName + ".\n";

	}
	catch(Item.NoItemExeception e2) {
		return "There's no " + itemName + "here.\n";
	}
     } 
  }
}
 		      
