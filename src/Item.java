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
	Hashtable <String, String> names = new Hashtable<String, String>();
	 

	public Item(Scanner s){

		String name = s.nextLine();		
		this.primaryName = s.nextLine();
		if(primaryName.contains(",")){
			for(int i = 0; i < primaryName.length(); i++){
				if(primaryName.charAt(i) == ','){
					String alias = primaryName.substring(i+1);
					primaryName = primaryName.substring(0,i);
					names.put(primaryName, alias);
					System.out.println("alias is: " + alias);
				}
			}
		}
		System.out.println(primaryName);
		this.weight = Integer.parseInt(s.nextLine());
		System.out.println(weight);
		String line = s.nextLine();
		while(!line.equals("---")){
			for(int i = 0; i < line.length(); i++){
				if(line.charAt(i) == colon){
					messages = new Hashtable<String, String>();
					String reaction = line.substring(i+1);
					String action = line.substring(0,i);
					messages.put(action, reaction);
					System.out.println(action + " does" + reaction);
					line = s.nextLine();
				}
			}
		}
				
	}
	public String getPrimaryName(){
		return primaryName;
	}
	public String getMessageForVerb(String verb){
		return messages.get(verb);
	}
	public Boolean goesBy(String name){
		if(names.get(primaryName).contains(name)){
			return true;
		}else{
			return false;
		}
	}
	public String toString(){
		return Item;
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
