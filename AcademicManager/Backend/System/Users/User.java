package System.Users;

import Tools.Enums.Access;
import Tools.Enums.Gender;

public abstract class User {
	
	private String rut;
	private String name;
	private String lastname;
	private String address;
	private Gender gender;
	private Access access;
	private int phone;
	private int age;
	
	public User(String name, String lastname, String rut, Access access) {
		this.rut = rut;
		this.name = name;
		this.lastname = lastname;
		this.access = access;
	}
	
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public Access getAccess() {
		return access;
	}
	public void setAccsess(Access access) {
		this.access = access;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
}
