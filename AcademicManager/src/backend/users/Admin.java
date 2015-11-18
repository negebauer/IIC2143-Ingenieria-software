package backend.users;

/**
 * Class that represents a system administrator.
 */
public class Admin extends User {

	/**
	 * Creates a new instance of Admin. Supports default values for every
	 * parameter, therefore null is a valid value for every parameter.
	 * 
	 * @param rut
	 *            The unique role identifier of the Admin.
	 * @param name
	 *            The name of the Admin.
	 * @param lastnameF
	 *            The last name from the father of the Admin.
	 * @param lastnameM
	 *            The last name from the mother of the Admin.
	 * @param address
	 *            The address of the Admin.
	 * @param gender
	 *            The gender of the Admin.
	 * @param access
	 *            The access of the Admin.
	 * @param phone
	 *            The cell phone of the Admin.
	 * @param birthdayString
	 *            The birthday of the Admin in the format dd.MM.yyyy
	 */
	public Admin(String rut, String name, String lastnameFather, String lastnameMother, String address, Gender gender,
			String phone, String birthdayString) {
		super(rut, name, lastnameFather, lastnameMother, address, gender, Access.ADMIN, phone, birthdayString);
	}
}
