
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
     * Checks to see if you are carrying anything if you are,
     * it will tell you what the items are. 
     * @return a message telling you what items you have if any, 
     * if you dont have nay it will tell you that.
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
