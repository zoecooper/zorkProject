/**
 * @author AN
 */ 
class DropCommand extends Command {

    private String itemName;
    /**
     * Takes in the itemName and assigns it to the variable itemName
     * @param itemName
     */ 
    DropCommand(String itemName) {
        this.itemName = itemName;
    }
    /**
     * After finding out that target item name variable in not empty 
     * and exists in the players inventory, the corresponding item 
     * objects are removed from inventory and added to contents of 
     * the players current room. 
     * @return a message describing the action taken by the olayer,
     * if there is any. 
     */ 
    public String execute() {
        if (itemName == null || itemName.trim().length() == 0) {
            return "Drop what?\n";
        }
        try {
            Item theItem = GameState.instance().getItemFromInventoryNamed(
                itemName);
            GameState.instance().removeFromInventory(theItem);
            GameState.instance().getAdventurersCurrentRoom().add(theItem);
            return theItem.getPrimaryName() + " dropped.\n";
        } catch (Item.NoItemException e) {
            return "You don't have a " + itemName + ".\n";
        }
    }
}
