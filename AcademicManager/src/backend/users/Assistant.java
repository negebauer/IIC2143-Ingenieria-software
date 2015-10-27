package backend.users;

/**
 * Class that represents a course assistant.
 */
public class Assistant extends User {

	/**
	 * Creates a new instance of Assistant.
	 * Supports default values for every parameter, therefore null is a valid value for every parameter.
	 * @param rut The unique role identifier of the Assistant.
	 * @param name The name of the Assistant.
	 * @param lastnameF The last name from the father of the Assistant.
	 * @param lastnameM The last name from the mother of the Assistant.
	 * @param address The address of the Assistant.
	 * @param gender The gender of the Assistant.
	 * @param access The access of the Assistant.
	 * @param phone The cell phone of the Assistant.
	 * @param birthdayString The birthday of the Assistant in the format dd.MM.yyyy
	 */
	public Assistant(String rut, String name, String lastnameFather, String lastnameMother, String address, Gender gender, String phone, String birthdayString) {
		super(rut, name, lastnameFather, lastnameMother, address, gender, Access.READONLY, phone, birthdayString);
	}
}
