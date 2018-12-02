import java.util.ArrayList;

class DropEvent extends Event{

    private String itemName;
   
    DropEvent(String itemName) {
        this.itemName = itemName;
    }
 
    public String execute() {
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

