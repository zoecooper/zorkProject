/**
 * Controls tasks that only characters can do.
 * @author ZC
 */
class CharacterSpecificCommand extends Command {

    private String verb;
    private String noun;
                        
    //constructor
    CharacterSpecificCommand(String verb, String noun) {
        this.verb = verb;
        this.noun = noun;
    }

   /**
    *Executes the command for the character.
    *@return a string saying the desired character does not exist in the room if it does not or a string saying the user cannot do something to the character given if they did not provide the correct command for that specific character
    *@throws NoCharacterException if the character given is not the in room the adventurer is currently in
    */
    public String execute() {
       /* 
        Character characterReferredTo = null;
        try {
            characterReferredTo = GameState.instance().getCharacterInVicinityNamed(noun);
        } catch (Character.NoCharacterException e) {
            return "There's not a " + noun + " here.";
        }
        
        String msg = characterReferredTo.getMessageForVerb(verb);
        return (msg == null ? 
            "Sorry, you can't " + verb + " the " + noun + "." : msg) + "\n";
    }
    */
    return "Zoe";	    
}
}
