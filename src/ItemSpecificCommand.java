class ItemSpecificCommand extends Command {

    private String verb;
    private String noun;
                        

    ItemSpecificCommand(String verb, String noun) {
        this.verb = verb;
        this.noun = noun;
    }

    //execute() should go here
    public String execute()throws Item.NoItemException {
        
        Item itemReferredTo = null;
        try {
            itemReferredTo = GameState.instance().getItemInVicinityNamed(noun);
        } catch (Item.NoItemException e) {
            return "There's no " + noun + " here.";
        }
        
       String msg = itemReferredTo.getMessageForVerb(verb);
       System.out.println(itemReferredTo);
        return (msg == null ?
            "Sorry, you can't " + verb + " the " + noun + "." : msg) + "\n";
    }
} 
