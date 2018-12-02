import java.util.ArrayList;

public class DropEvent extends Event {

	private Item i;
	private String event;

	public DropEvent(String event, Item i) {
		this.i = i;
		this.event = event;

	}

	public String execute() {
		GameState game = GameState.instance();
		Room r = game.getAdventurersCurrentRoom();
		ArrayList<Item> inventory = game.getInventory();
		if (inventory.contains(i)) {
			game.removeFromInventory(i);
		}
		
		return "" + capitalize(String.valueOf(i)) + "is gone";

	}

}
