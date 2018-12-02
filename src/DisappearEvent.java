class DisappearEvent extends Event {

	private String primaryItemName;

	public DisappearEvent(String primaryItemName){
		this.primaryItemName  = primaryItemName;
	}

	String execute() {
	 GameState.instance().removeFromInventory(this);
	}
}
