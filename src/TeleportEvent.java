class TeleportEvent extends Event {

	public TeleportEvent() {
	}

	String execute() {
		GameState.instance().setAdventurersCurrentRoom(GameState.instance().getDungeon().getEntry());
                System.out.println(GameState.instance().getDungeon().getEntry().describe());
	}
}
