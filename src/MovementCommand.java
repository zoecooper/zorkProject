class MovementCommand extends Command{

	private String dir;

	MovementCommand(String dir) {
		this.dir = dir;
	}
	

	public String execute() {
		Room currentRoom = GameState.instance().getAdventurersCurrentRoom();
		Room nextRoom = currentRoom.leaveBy(dir);

		if(nextRoom != null){
			GameState.instance().setAdventurersCurrentRoom(nextRoom);
			return "\n" + nextRoom.describe() + "\n";
		}
		else{
			return "You can't go" + dir + ".\n";
		}

	}	

}
	
