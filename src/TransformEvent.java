class TransformEvent extends Event {

	
	public TransformEvent() {
	}

	String execute() {
		//first removes from inventory
		Item theItem = GameState.instance().removeFromInventory(theItem);
                //disappears it
                GameState.instance().getDungeon().disappearItem(String.valueOf(theItem));
                //new item
                Item newItem = GameState.instance().getDungeon().getItem(command.substring(command.indexOf("(")+1, command.indexOf(")")));


                GameState.instance().addToInventory(newItem);

		
	}
}
