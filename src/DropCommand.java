class DropCommand extends Command {

	private	String itemName;

	DropCommand(String itemName) {
		this.itemName = itemName;
	}

	public String execute() {
		if(itemName == null || itemName.trim() == 0) {
			return "Drop what?\n";
		}

		try{
			Item theItem = GameState.instance().getItemFromInventoryName(itemName);
			GameState.instance().removeFromInventory(theItem);
			GameState.instance().getAnventurersCurrentRoom().add(theItem);
			return itemName + " dropped.\n";
		}

		catch(Item.NoItemException e) {
			return "You don't have a " + itemName + ".\n";
		}
	}
}
