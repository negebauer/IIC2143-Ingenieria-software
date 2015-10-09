package System.Manager;

public class Manager {

	public enum Language {
		SPANISH,
		ENGLISH;
		
		public static Language defaultLanguage() {
			return Language.SPANISH;
		}
	}
	
	private final static Manager INSTANCE = new Manager();
	private Language language = Language.defaultLanguage();
	
	public static Manager getInstance() {
		return INSTANCE;
	}

	private Manager() {

	}
}
