package backend.manager;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

import backend.others.Utilities;
import backend.users.Admin;
import backend.users.User.Gender;

/**
 * Class that manages the reading and writing of all the admins from the 'database'.
 */
public class AdminReaderWriter {

	/* File format
		rut&name&lastnameFather&lastnameMother&address&gender&access&phone&birthdayString
	*/

	/**
	 * Writes all the assistants to the admins.txt file.
	 * @param admins The admins to be written.
	 */
	public static void writeAdmins(ArrayList<Admin> admins) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream (FolderFileManager.admins);
			PrintStream printStream = new PrintStream(fileOutputStream);
			for (Admin admin : admins) {
				printStream.print(admin.getRut());
				printStream.print("&");
				printStream.print(admin.getName());
				printStream.print("&");
				printStream.print(admin.getLastnameFather());
				printStream.print("&");
				printStream.print(admin.getLastnameMother());
				printStream.print("&");
				printStream.print(admin.getAddress());
				printStream.print("&");
				printStream.print(admin.getGender());
				printStream.print("&");
				printStream.print(admin.getPhone());
				printStream.print("&");
				printStream.print(Utilities.getStringFromDate(admin.getBirthday()));
				printStream.println();
			}
			fileOutputStream.close();		
		} catch (IOException ioException) {
			System.err.println ("Unable to write to file");
			System.out.println(ioException);
		}
	}
	
	/**
	 * Reads all the admins from the admins.txt file and returns them in a list.
	 * @return The admins list.
	 */
	public static ArrayList<Admin> readAdmins() {
		ArrayList<Admin> admins = new ArrayList<Admin>();
		try {
			FileInputStream fileInputStream = new FileInputStream (FolderFileManager.admins);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
			String adminString = bufferedReader.readLine();
			while (adminString != null ) {
				String[] arguments = adminString.split("&");
				String rut = arguments[0];
				String name = arguments[1];
				String lastnameFather = arguments[2];
				String lastnameMother = arguments[3];
				String address = arguments[4];
				Gender gender = Gender.valueOf(arguments[5]);
				String phone = arguments[6];
				String birthdayString = arguments[7];
				Admin admin = new Admin(rut, name, lastnameFather, lastnameMother, address, gender, phone, birthdayString);
				admins.add(admin);
				adminString = bufferedReader.readLine();
			}
			fileInputStream.close();		
		} catch (IOException ioException) {
			System.err.println ("Unable to read from file");
			System.out.println(ioException);
		}
		return admins;
	}
}

