class TransformEvent extends Event {

	private Item itemName;
	private String name;
	public TransformEvent(Item itemName,String name){
		this.itemName = itemName;
		this.name = name;
		//System.out.println("hi");
	}
	public TransformEvent(String name){
		this.name = name; 
	}


	String execute() {
		//first removes from inventory
//		Item theItem = GameState.instance().getItemFromInventoryNamed(itemName);
		GameState.instance().removeFromInventory(itemName);
                //disappears it
                GameState.instance().getDungeon().disappearItem(itemName);
                //new item
		Item newItem = new Item(name);
		GameState.instance().addToInventory(newItem); 
  //              Item newItem = GameState.instance().getDungeon().getItem(o.substring(o.indexOf("(")+1, o.indexOf(")")));
	//	Item theItem = GameState.instance().getItemFromInventoryNamed(itemName);
	//	GameState.instance().removeFromInventory(theItem);
                //disappears it
        //        GameState.instance().getDungeon().disappearItem(String.valueOf(theItem));
        //        new item

		return "";
	}
}
