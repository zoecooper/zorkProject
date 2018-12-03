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
        public Event parse(String event, String item){
		Item i = null; 
		try{
		i = GameState.instance().getDungeon().getItem(item); 
		}catch(Exception e){
		
		}
                
		event = event.substring(1,event.length()-1);
//	       System.out.println(event); 	
		String[] n = event.split(",");
		//System.out.println("l");
		//System.out.println(n.length);
                for(String o : n){
		//	System.out.println(o);
                        if(o.toLowerCase().contains("wound")){
                                int l = o.indexOf("(");
                                int parse = Integer.parseInt(o.substring(l+1,o.length()-1));

                                Event c = new WoundEvent(parse);
                        	c.execute(); 
			
			}
                        if(o.toLowerCase().contains("die")){
                                Event c = new DieEvent();
                        	c.execute(); 
			}
			if(o.toLowerCase().contains("score")){
                                int l = o.indexOf("(");
                                int parse = Integer.parseInt(o.substring(l+1,o.length()-1));
				System.out.print(parse);
                                Event c=  new ScoreEvent(parse);
                        	c.execute(); 
			}
			if(o.toLowerCase().contains("teleport")){
				Event c = new TeleportEvent();
				c.execute(); 
			}
			if(o.toLowerCase().contains("transform")){
		//		System.out.println("hi");
				int l = o.indexOf("(");
                                String name = o.substring(l+1,o.length()-1);
				Event c = new TransformEvent(i,name);
				c.execute(); 
			}
			if(o.toLowerCase().contains("disappear")){
				Event c = new DisappearEvent(i);
				c.execute(); 
			}
			if(o.toLowerCase().contains("win")){
				Event c = new WinEvent();
				c.execute(); 
			}
			if(o.toLowerCase().contains("drop")){
				Event c =new DropEvent(item);
				c.execute(); 
			}
			else{
				Event c = new DropEvent(item);
				c.execute(); 
			}	


                }
		return null;
                
        }
}

