class TransformEvent extends Event {

	private String itemName;

	public TransformEvent(String itemName){
		this.itemName = itemName;
	}

	String execute() {
		//first removes from inventory
		Item theItem = GameState.instance().getItemFromInventoryNamed(itemName);
		GameState.instance().removeFromInventory(theItem);
                //disappears it
                GameState.instance().getDungeon().disappearItem(String.valueOf(theItem));
                //new item
                Item newItem = GameState.instance().getDungeon().getItem(o.substring(o.indexOf("(")+1, o.indexOf(")")));


                GameState.instance().addToInventory(newItem);

		
	}
}
