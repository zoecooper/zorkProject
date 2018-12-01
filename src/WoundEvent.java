class WoundEvent extends Event {

	private int wound;
	public WoundEvent(int wound){
		this.wound = wound;
	}

	String execute() {
		GameState.instance().subtractHealth(wound);
		return "You were wounded and your health has decreased.";
	}
}
