import java.util.ArrayList;
/**
 * It runs commands to check the inventory
 * @author AN
 */
class InventoryCommand extends Command {
    /**
     * A constructor to create an inventory command
     */	
    InventoryCommand() {
    }
    /**
     * Contents are printed to the console if inventory
     * is not empty.   
     * @return a message telling you if you have any items.
     */ 
    public String execute() {
        ArrayList<String> names = GameState.instance().getInventoryNames();
        if (names.size() == 0) {
            return "You are empty-handed.\n";
        }
        String retval = "You are carrying:\n";
        for (String itemName : names) {
            retval += "   A " + itemName + "\n";
        }
        return retval;
    }
}
