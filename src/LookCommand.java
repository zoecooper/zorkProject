import java.util.ArrayList;

class LookCommand extends Command {

	String currenRoom = GameState.instance().getAdventurersCurrentRoom().getTitle();
	ArrayList<Item>contents = GameState.instance().getAdventurersCurrentRoom().getContents();
	String lookLeader = "Looking around";

	
	public LookCommand() {
	
	}

	String execute() {
		return GameState.instance().getAdventurersCurrentRoom().describeLook();
	}
}

