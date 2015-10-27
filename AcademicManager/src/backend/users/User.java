package backend.users;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import backend.others.Utilities;

/**
 * Class that represents a User.
 */
public abstract class User {
	
	public enum Access {
		ADMIN,
		EDITOR,
		USER,
		READONLY;
		
		public static Access defaultAccess() {
			return Access.READONLY;
		}
	}
	
	public enum Gender {
		MALE,
		FEMALE;
		
		public static Gender defaultGender() {
			return Gender.MALE;
		}
	}
	
	private String rut;
	private String name;
	private String lastnameFather;
	private String lastnameMother;
	private String address;
	private Gender gender;
	private Access access;
	private String phone;
	private Date birthday;
	
	/**
	 * Creates a new instance of User.
	 * Supports default values for every parameter, therefore null is a valid value for every parameter.
	 * @param rut The unique role identifier of the User.
	 * @param name The name of the user.
	 * @param lastnameF The last name from the father of the User.
	 * @param lastnameM The last name from the mother of the User.
	 * @param address The address of the User.
	 * @param gender The gender of the User.
	 * @param access The access of the User.
	 * @param phone The cell phone of the User.
	 * @param birthdayString The birthday of the User in the format dd.MM.yyyy
	 */
	public User(String rut, String name, String lastnameFather, String lastnameMother, String address, Gender gender, Access access, String phone, String birthdayString) {
		this.rut = rut != null ? rut : "RUTNil";
		this.name = name != null ? name : "NameNil";
		this.lastnameFather = lastnameFather != null ? lastnameFather : "";
		this.lastnameMother = lastnameMother != null ? lastnameMother : "";
		this.address = address != null ? address : "" ;
		this.gender = gender != null ? gender : Gender.defaultGender();
		this.access = access != null ? access : Access.defaultAccess();
		this.phone = phone != null ? phone : "";
		this.birthday = birthdayString != null ? Utilities.getDateFromString(birthdayString + " 00:00") : new Date();
	}
	
	/**
	 * @return The age of the User
	 */
	public int getAge() {
		LocalDate birthdayDate = Instant.ofEpochMilli(birthday.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate currentDate = LocalDate.now();
		Period period = Period.between(birthdayDate, currentDate);
		return period.getYears();
	}

	/**
	 * @return The unique role identifier of the User.
	 */
	public String getRut() {
		return rut;
	}

	/**
	 * @return The name of the User.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Modifies the User name. 
	 * @param name The new name of the User.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return The last name from the father of the user
	 */
	public String getLastnameFather() {
		return lastnameFather;
	}

	/**
	 * Modifies the last name from the father of the user.
	 * @param lastnameFather The new last name from the father of the user.
	 */
	public void setLastnameFather(String lastnameFather) {
		this.lastnameFather = lastnameFather;
	}

	/**
	 * @return The last name from the mother of the user.
	 */
	public String getLastnameMother() {
		return lastnameMother;
	}
	
	/**
	 * Modifies last name from the mother of the user.
	 * @param lastnameFather The new last name from the mother of the user.
	 */
	public void setLastnameMother(String lastnameMother) {
		this.lastnameMother = lastnameMother;
	}

	/**
	 * @return The address of the user.
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * Modifies the address of the user.
	 * @param address The new address of the user.
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return The gender of the user.
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * Modifies the gender of the user.
	 * @param gender The new gender of the user.
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	/**
	 * @return The access mode of the user.
	 */
	public Access getAccess() {
		return access;
	}
	
	/**
	 * Modifies the access mode of the user.
	 * @param access The new access mode of the user.
	 */
	public void setAccess(Access access) {
		this.access = access;
	}

	/**
	 * @return The phone of the User.
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Modifies the phone of the user.
	 * @param phone The new phone of the user.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return The birthday of the user.
	 */
	public Date getBirthday() {
		return birthday;
	}
	
}