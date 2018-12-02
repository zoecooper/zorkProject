class TransformEvent extends Event {

	
	public TransformEvent() {
	}

	String execute() {
		GameState.instance().removeFromInventory(this);

                GameState.instance().getDungeon().disappearItem(String.valueOf(this));

                Item newItem = GameState.instance().getDungeon().
                        getItem(command.substring(command.indexOf("(")+1, command.indexOf(")")));


                GameState.instance().addToInventory(newItem);

		
	}
}
