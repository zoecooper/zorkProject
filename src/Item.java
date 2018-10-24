import java.util.Hashtable;
import java.util.Scanner;
import java.lang.*;
import java.io.FileReader;
import java.io.FileNotFoundException;
public class Item{
	private String primaryName;
	private int weight;
	private Hashtable <String, String> messages;
	char colon = ':';

	public Item(Scanner s){
		String name = s.nextLine();		
		this.primaryName = s.nextLine();
		System.out.println(primaryName);
		this.weight = Integer.parseInt(s.nextLine());
		System.out.println(weight);
		String line = s.nextLine();
		while(!line.equals("---")){
			for(int i = 0; i < line.length(); i++){
				if(line.charAt(i) == colon){
					String reaction = line.substring(i+1);
					String action = line.substring(0,i);
					System.out.println(action + " does" + reaction);
					line = s.nextLine();
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
