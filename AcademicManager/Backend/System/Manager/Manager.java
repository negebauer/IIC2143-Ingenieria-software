package System.Manager;

public class Manager {

	private final static Manager INSTANCE = new Manager();
	
	public static Manager getInstance() {
		return INSTANCE;
	}

	private Manager() {

	}
}
