package System.Users;

/**
 * Class that represents a Professor.
 */
public class Professor extends User {
	
//	TODO Write java doc
	/**
	 * 
	 * @param rut
	 * @param name
	 * @param lastnameFather
	 * @param lastnameMother
	 * @param address
	 * @param gender
	 * @param phone
	 * @param birthdayString
	 */
	public Professor(String rut, String name, String lastnameFather, String lastnameMother, String address, Gender gender, int phone, String birthdayString) {
		super(rut, name, lastnameFather, lastnameMother, address, gender, Access.EDITOR, phone, birthdayString);
	}
}
