import java.util.ArrayList;

public class DropEvent extends Event {

	private String itemName;
	private String event;

	public DropEvent(String itemName) {
		this.itemName = itemName;

	}

	public String execute() {
		GameState game = GameState.instance();
		Item theItem = game.getItemFromInventoryNamed(itemName);
		Room r = game.getAdventurersCurrentRoom();
		ArrayList<Item> inventory = game.getInventory();
		if (inventory.contains(theItem)) {
			game.removeFromInventory(theItem);
			r.add(theItem);
		}
		
		return "" + capitalize(String.valueOf(i)) + "is gone";

	}

}
