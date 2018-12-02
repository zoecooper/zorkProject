class DisappearEvent extends Event {

	private String primaryItemName;

	public DisappearEvent(String primaryItemName){
		this.primaryItemName  = primaryItemName;
	}

	String execute() {
	Item theItem = GameState.instance().getItemFromInventoryNamed(Item);
	GameState.instance().removeFromInventory(theItem);
	}
}
