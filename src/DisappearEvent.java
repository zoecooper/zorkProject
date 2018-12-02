class DisappearEvent extends Event {

	private String itemName;

	public DisappearEvent(String itemName){
		this.itemName  = itemName;
	}

	String execute() {
	Item theItem  = GameState.instance().getItemFromInventoryNamed(itemName);
	GameState.instance().removeFromInventory(theItem);
	return "";


        try {
	       	GameState.instance().getDungeon().disappearItem(this.itemName);
       	} catch (NoItemException e) {
	       	e.printStackTrace();
       	}
	}
}
