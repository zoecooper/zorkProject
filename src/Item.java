import java.util.Hashtable;
import java.util.Scanner;
import java.lang.*;
import java.io.FileReader;
import java.io.FileNotFoundException;
public class Item{
	private String primaryName;
	private int weight;
	private Hashtable <String, String> messages;

	public Item(Scanner s){
		String name = s.nextLine();		
		this.primaryName = s.nextLine();
		System.out.println(primaryName);
		this.weight = Integer.parseInt(s.nextLine());
		System.out.println(weight);
		if(s.nextLine().contains(":")){
			System.out.println("it here");
			String action = s.nextLine();
			for(int i = 0; i < action.length(); i++){
				while(action.charAt(i) == ','){
					String reaction = action.substring(i);
					action = action.substring(0,i);
					System.out.println(action + " " + reaction);
				}
			}
		}
				
	}
	public static void main(String args[]){
		try{
			Scanner r = new Scanner(new FileReader("../files/test.zork")); 
			Item y = new Item(r);

		}catch (FileNotFoundException e){
			System.out.print("nah");
			System.exit(1);
		}
	}

}
