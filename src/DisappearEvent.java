class DisappearEvent extends Event {

	private String itemName;

	public DisappearEvent(String itemName){
		this.itemName  = itemName;
	}

	String execute(){

	try{
		Item theItem  = GameState.instance().getItemFromInventoryNamed(itemName);
		GameState.instance().removeFromInventory(theItem);
	       	GameState.instance().getDungeon().disappearItem(this.itemName);
       	} catch (Exception e) {       	
       	}
	return "";
	}
}
