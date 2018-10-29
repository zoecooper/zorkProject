class DropCommand extends Command {

	private	String itemName;

	DropCommand(String itemName) {
		this.itemName = itemName;
	}

	public String execute() {
		if(itemName == null || itemName.trim().length() == 0) {
			return "Drop what?\n";
		}

		try{
			Item theItem = GameState.instance().getItemFromInventoryNamed(itemName);
			GameState.instance().removeFromInventory(theItem);
			GameState.instance().getAdventurersCurrentRoom().add(theItem);
			return itemName + " dropped.\n";
		}

		catch(Exception e) {
			return "You don't have a " + itemName + ".\n";
		}
	}
}
