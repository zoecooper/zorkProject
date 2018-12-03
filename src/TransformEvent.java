class TransformEvent extends Event {

	private Item itemName;

	public TransformEvent(Item itemName,String name){
		this.itemName = itemName;
	}

	String execute() {
		//first removes from inventory
//		Item theItem = GameState.instance().getItemFromInventoryNamed(itemName);
		GameState.instance().removeFromInventory(itemName);
                //disappears it
                GameState.instance().getDungeon().disappearItem(itemName);
                //new item
		Item newItem = new Item(name);
  //              Item newItem = GameState.instance().getDungeon().getItem(o.substring(o.indexOf("(")+1, o.indexOf(")")));


    //            GameState.instance().addToInventory(newItem);
	return "";	
	}
}
