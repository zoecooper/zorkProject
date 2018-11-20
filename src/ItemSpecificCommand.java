/**
 * Controls tasks that only items can do.
 * @author ZC
 */
class ItemSpecificCommand extends Command {

    private String verb;
    private String noun;
                        
    //constructor
    ItemSpecificCommand(String verb, String noun) {
        this.verb = verb;
        this.noun = noun;
    }

   /**
    *Executes the command for the item returning the message assigned to the verb for that specific item.
    *@return a string saying the desired item does not exist in the room if it does not or a string saying the user cannot do something to the item given if they did not provide the correct command for that specific item
    *@throws NoItemException if the item given is not the in room the adventurer is currently inC
    */
    public String execute() {
        
        Item itemReferredTo = null;
        try {
            itemReferredTo = GameState.instance().getItemInVicinityNamed(noun);
        } catch (Item.NoItemException e) {
            return "There's no " + noun + " here.";
        }
        
        String msg = itemReferredTo.getMessageForVerb(verb);
        return (msg == null ? 
            "Sorry, you can't " + verb + " the " + noun + "." : msg) + "\n";
    }
}
