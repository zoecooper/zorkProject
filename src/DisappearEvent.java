class DisappearEvent extends Event {

	private String itemName;

	public DisappearEvent(String itemName){
		this.itemName  = itemName;
	}

	String execute() {
	Item theItem  = GameState.instance().getItemFromInventoryNamed(itemName);
	GameState.instance().removeFromInventory(theItem);
	}
}
