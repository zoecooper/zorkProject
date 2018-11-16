
class SaveCommand extends Command {

    /**
     * Allows the player to save his/her progress  by typing "save".
     */
    private static String DEFAULT_SAVE_FILENAME = "zork";

    private String saveFilename;

    SaveCommand(String saveFilename) {
        if (saveFilename == null || saveFilename.length() == 0) {
            this.saveFilename = DEFAULT_SAVE_FILENAME;
        } else {
            this.saveFilename = saveFilename;
        }
    }

    /**
     * Executes the command so the player may save progress.
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
