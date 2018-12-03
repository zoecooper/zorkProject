class DisappearEvent extends Event {

	private Item itemName;

	public DisappearEvent(Item itemName){
		this.itemName  = itemName;
	}

	String execute(){

	try{
		//Item theItem  = GameState.instance().getItem(itemName);
		GameState.instance().removeFromInventory(itemName);
	       	GameState.instance().getDungeon().disappearItem(this.itemName);
       	} catch (Exception e) {       	
       	}
	return "";
	}
}
