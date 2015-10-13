package System.Users;

/**
 * Class that represents a Professor.
 */
public class Professor extends User {
	
	/**
	 * Creates a new instance of Professor.
	 * Supports default values for every parameter, therefore null is a valid value for every parameter.
	 * @param rut The unique role identifier of the Professor.
	 * @param name The name of the Professor.
	 * @param lastnameF The last name from the father of the Professor.
	 * @param lastnameM The last name from the mother of the Professor.
	 * @param address The address of the Professor.
	 * @param gender The gender of the Professor.
	 * @param access The access of the Professor.
	 * @param phone The cell phone of the Professor.
	 * @param birthdayString The birthday of the Professor in the format dd.MM.yyyy
	 */
	public Professor(String rut, String name, String lastnameFather, String lastnameMother, String address, Gender gender, int phone, String birthdayString) {
		super(rut, name, lastnameFather, lastnameMother, address, gender, Access.EDITOR, phone, birthdayString);
	}
}
