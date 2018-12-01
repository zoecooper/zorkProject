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
		return null;
	}
}
