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
        public Event parse(String event){
                String[] n = event.split(",");
                for(String o : n){
                        if(o.toLowerCase().contains("wound")){
                                int l = o.indexOf("(");
                                int parse = Integer.parseInt(o.substring(l,o.length()-1));
                                return new WoundEvent(parse);
                        }
                        if(o.toLowerCase().contains("die")){
                                return new DieEvent();
                        }
                }
                return null;
        }
}

