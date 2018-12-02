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
    *Executes the command for the item and tells the user the desired item does not exist in the room if
it is not found. If it does exist, it will return a string saying the user cannot do something to the item
given if they did not provide the correct command for that specific item.
    *@return a string with the error messages for the user
    *@throws NoItemException if the item given is not the in room the adventurer is currently in
    */
    public String execute() {
        
        Item itemReferredTo = null;
        try {
            itemReferredTo = GameState.instance().getItemInVicinityNamed(noun);
        } catch (Item.NoItemException e) {
            return "There's no " + noun + " here.";
        }
	String event = itemReferredTo.getEvent(verb);
	if(event!= null){
		Event n = EventFactory.instance().parse(event, noun);
		n.execute();
	}
        try {
		String msg = itemReferredTo.getMessageForVerb(verb);
        
		return (msg == null ? 
				"Sorry, you can't " + verb + " the " + noun + "." : msg) + "\n";
	}catch(Exception e) {
	}
	return "";
    }
}
