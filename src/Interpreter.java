
import java.io.*;
import java.util.*;


public class Interpreter {

    private static GameState state; // not strictly necessary; GameState is 
                                    // singleton, and so accessible that way

    public static void main(String args[]) {

        try {
            state = GameState.instance();
            state.initialize(buildSampleDungeon());

            String command;
            Scanner commandLine = new Scanner(System.in);
            System.out.println("\nWelcome to " + state.getDungeon().getName() +
                "!");

            System.out.print("\n" + 
                state.getAdventurersCurrentRoom().describe() + "\n");

            command = promptUser(commandLine);

            while (!command.equals("q")) {

                System.out.print(
                    CommandFactory.instance().parse(command).execute());

                command = promptUser(commandLine);
            }

            System.out.println("Bye!");

        } catch(Exception e) { 
            e.printStackTrace(); 
        }
    }

    private static String promptUser(Scanner commandLine) 
        throws IOException {

        System.out.print("> ");
        return commandLine.nextLine();
    }

    private static Dungeon buildSampleDungeon() {
        Room rotunda = new Room("Rotunda");
        rotunda.setDesc(
"You are in a beautiful round room, with a ceiling that seemingly reaches\n" +
"to the skies.");
        Room basement = new Room("Basement hallway");
        basement.setDesc(
"You are in a dusty, decrepit basement with the faint smell of body odor.");
        Room stephensOffice = new Room("Stephen's office");
        stephensOffice.setDesc(
"This is a cluttered office, with many geeky toys.");
        Room b6 = new Room("B6");
        b6.setDesc(
"Sunlight streams through tall windows and illuminates a brilliant\n" +
"classroom.");
        Room balcony = new Room("Rotunda balcony");
        balcony.setDesc(
"You stand on a circular white balcony overlooking an entry hall.\n" +
"Columnar bannisters in ancient Grecian style stand between you and the\n" +
"precipice.");
        new Exit("d",rotunda,basement);
        new Exit("u",basement,rotunda);
        new Exit("d",balcony,rotunda);
        new Exit("u",rotunda,balcony);
        new Exit("w",basement,b6);
        new Exit("e",basement,stephensOffice);
        new Exit("w",stephensOffice,basement);
        new Exit("e",b6,basement);
            
        Dungeon sampleDungeon = new Dungeon("Trinkle", rotunda);
        sampleDungeon.add(rotunda);
        sampleDungeon.add(basement);
        sampleDungeon.add(stephensOffice);
        sampleDungeon.add(b6);
        sampleDungeon.add(balcony);
        return sampleDungeon;
    }

}
