/**
 * Allows the player to save his/her current progress in the game.
 * @author NC
 */
class SaveCommand extends Command {
    private static String DEFAULT_SAVE_FILENAME = "zork";

    private String saveFilename;

    /**
     * Constructor for the save file ruling that if the user does not specifically enter a 
     * name for the save file, the game will be saved to a default save file.
     */ 

    SaveCommand(String saveFilename) {
        if (saveFilename == null || saveFilename.length() == 0) {
            this.saveFilename = DEFAULT_SAVE_FILENAME;
        } else {
            this.saveFilename = saveFilename;
        }
    }

    /**
     * Executes the command so the player may save current progress to a save file using 
     * the store method in GameState. If the game fails to save, it will return "Couldn't save!"
     */ 
    public String execute() {
        try {
            GameState.instance().store(saveFilename);
            return "Data saved to " + saveFilename +
                GameState.SAVE_FILE_EXTENSION + ".\n";
        } catch (Exception e) {
            System.err.println("Couldn't save!");
            e.printStackTrace();
            return "";
        }
    }
}
