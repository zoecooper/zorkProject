class ScoreEvent extends Event {
	private int points;
	public ScoreEvent(int points){
		this.points = points;
	}

	String execute() {
		GameState.instance().addToScore(points);
		return "You scored!!!";
	}
}
