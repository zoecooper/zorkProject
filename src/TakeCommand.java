/**
 * Allows user to add an item to its inventory.
 * @author NC
 */
class TakeCommand extends Command {

    private String itemName;   
    /**
     * A contructor setting the item name of the item wanting to be taken.
     */ 
    TakeCommand(String itemName) {
        this.itemName = itemName;
    }

    /**
     * Executes the take command for an item allowing the user to "take" the item 
     * wanting to be taken if it is in the player's current room, then adding the item 
     * to the player's inventory and removing it from the room's contents. If the user 
     * simply enters "Take," the method will return "Take what?" The item cannot be 
     * taken twice, taken from a different room, nor taken if the inventory is more 
     * than/will be more than the max weight.
     */ 
    public String execute() {
        if (itemName == null || itemName.trim().length() == 0) {
            return "Take what?\n";
        }
        try {
            Room currentRoom = 
                GameState.instance().getAdventurersCurrentRoom();
            Item theItem = currentRoom.getItemNamed(itemName);
            if (theItem.getWeight() + 
                GameState.instance().getAdventurersCurrentWeight() > 40) {
                return "Your load is too heavy.\n";
            }
            GameState.instance().addToInventory(theItem);
            currentRoom.remove(theItem);
            return theItem.getPrimaryName() + " taken.\n";
        } catch (Item.NoItemException e) {
            // Check and see if we have this already. If no exception is
            // thrown from the line below, then we do.
            try {
                GameState.instance().getItemFromInventoryNamed(itemName);
                return "You already have the " + itemName + ".\n";
            } catch (Item.NoItemException e2) {
                return "There's no " + itemName + " here.\n";
            }
        }
    }
}
