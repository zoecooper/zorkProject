
class TakeCommand extends Command {

	private String itemName;
	
	TakeCommand(String itemName) {
		System.out.println("Item name is: " + itemName);
		this.itemName = itemName;
	}

	public String execute() {
		if(itemName == null || itemName.trim().length() == 0) {
			 return "Take what?\n";
		}
	
	try{
		Room currentRoom = GameState.instance().getAdventurersCurrentRoom();
		Item theItem = currentRoom.getItemNamed(itemName);
		if(theItem == null ){
			throw new Item.NoItemException(); 
		}
		GameState.instance().addToInventory(theItem);
		currentRoom.remove(theItem);
		return itemName + " taken.\n";

	}
	catch(Item.NoItemException e) {
	System.out.print("Not Found");
	}

	try{
		Item x = GameState.instance().getItemFromInventoryNamed(itemName);
		if(x == null){
			throw new Item.NoItemException(); 
		}
		return "You already have the " + itemName + ".\n";

	}
	catch(Item.NoItemException e) {
		return "There's no " + itemName + " here.\n";
		
	}
     } 
  }

 		      
