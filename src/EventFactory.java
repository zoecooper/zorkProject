import java.util.List;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class EventFactory {
	private static EventFactory theInstance;

	public static synchronized EventFactory instance() {
		if (theInstance == null) {
			theInstance = new EventFactory();
		}
		return theInstance;
	}

	private EventFactory() {
	}

        private void parseOtherCommands(String key) throws NoItemException {
        String otherCommands = key.substring(key.indexOf('[') +1 ,key.indexOf(']'));
        String[] commands = otherCommands.split(",");


        for(int i = 0; i < commands.length; i++){

            String command = commands[i];

            if(command.contains("Wound")){
              GameState.instance().woundAdventurer(Integer.parseInt(command.substring(command.indexOf("(")+1,
                      command.indexOf(")"))));
            }
            if(command.contains("Score")){
                GameState.instance().addScore(Integer.parseInt(
                        command.substring(command.indexOf("(")+1,command.indexOf(")"))));
            }
            if(command.contains("Die")){
                GameState.instance().setAdventurersCurrentHealth(0);
                GameState.instance().setLoseCondition(true);
            }
            if(command.contains("Win")){
                GameState.instance().setWinCondition(true);
            }
            if(command.contains("Disappear")){
                GameState.instance().removeFromInventory(this);

                try {
                    GameState.instance().getDungeon().disappearItem(this.primaryName);
                } catch (NoItemException e) {
                    e.printStackTrace();
                }
            }
            //will remove the item from the inventory, the dungeon itself, and add the new item to the inventory.
            if(command.contains("Transform")){
                GameState.instance().removeFromInventory(this);

                GameState.instance().getDungeon().disappearItem(String.valueOf(this));

                Item newItem = GameState.instance().getDungeon().
                        getItem(command.substring(command.indexOf("(")+1, command.indexOf(")")));


                GameState.instance().addToInventory(newItem);
            }
            if(command.contains("Teleport")){
                GameState.instance().setAdventurersCurrentRoom(GameState.instance().getDungeon().getEntry());
                System.out.println(GameState.instance().getDungeon().getEntry().describe());
            }
        }
	}
}
