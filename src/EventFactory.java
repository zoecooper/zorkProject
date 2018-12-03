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
                String[] n = event.split(",");
                for(String o : n){
			System.out.println(o);
                        if(o.toLowerCase().contains("wound")){
                                int l = o.indexOf("(");
                                int parse = Integer.parseInt(o.substring(l+1,o.length()-1));

                                return new WoundEvent(parse);
                        }
                        if(o.toLowerCase().contains("die")){
                                return new DieEvent();
                        }
			if(o.toLowerCase().contains("score")){
                                int l = o.indexOf("(");
                                int parse = Integer.parseInt(o.substring(l,o.length()-1));
				System.out.print(parse);
                                return new ScoreEvent(parse);
                        }
			if(o.toLowerCase().contains("teleport")){
				return new TeleportEvent();
			}
			if(o.toLowerCase().contains("transform")){
				int l = o.indexOf("(");
                                String name = o.substring(l+1,o.length()-1);
				return new TransformEvent(item,name);
			}
			if(o.toLowerCase().contains("disappear")){
				return new DisappearEvent(item);
			}
			if(o.toLowerCase().contains("win")){
				return new WinEvent();
			}
			if(o.toLowerCase().contains("drop")){
				return new DropEvent(item);
			}
			


                }
		return new TeleportEvent();
                
        }
}

