package System.Manager;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

import System.Users.Professor;
import System.Users.User.Gender;
import Tools.Others.Utilities;

/**
 * Class that manages the reading and writing of all the professors from the 'database'.
 */
public class ProfessorsReaderWriter {

	/**
	 * Writes all the professors to the professors.txt file.
	 * @param professors The professors to be written.
	 */
	public static void writeProfessors(ArrayList<Professor> professors) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream (FolderFileManager.adminProfessors);
			for (Professor professor : professors) {
				new PrintStream(fileOutputStream).println(professor.getRut());
				new PrintStream(fileOutputStream).println("&");
				new PrintStream(fileOutputStream).println(professor.getName());
				new PrintStream(fileOutputStream).println("&");
				new PrintStream(fileOutputStream).println(professor.getLastnameFather());
				new PrintStream(fileOutputStream).println("&");
				new PrintStream(fileOutputStream).println(professor.getLastnameMother());
				new PrintStream(fileOutputStream).println("&");
				new PrintStream(fileOutputStream).println(professor.getAddress());
				new PrintStream(fileOutputStream).println("&");
				new PrintStream(fileOutputStream).println(professor.getGender());
				new PrintStream(fileOutputStream).println("&");
				new PrintStream(fileOutputStream).println(professor.getPhone());
				new PrintStream(fileOutputStream).println("&");
				new PrintStream(fileOutputStream).println(Utilities.getStringFromDate(professor.getBirthday()));
			}
			fileOutputStream.close();		
		} catch (IOException ioException) {
			System.err.println ("Unable to write to file");
			System.out.println(ioException);
		}
	}
	
	/**
	 * Reads all the professors from the professors.txt file and returns them in a list.
	 * @return The professors list.
	 */
	public static ArrayList<Professor> readProfessors() {
		ArrayList<Professor> professors = new ArrayList<Professor>();
		
		try {
			FileInputStream fileInputStream = new FileInputStream (FolderFileManager.adminProfessors);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
			String professorString = bufferedReader.readLine();
			while (professorString != null ) {
				String[] arguments = professorString.split("&");
				String rut = arguments[0];
				String name = arguments[1];
				String lastnameFather = arguments[2];
				String lastnameMother = arguments[3];
				String address = arguments[4];
				Gender gender = Gender.valueOf(arguments[5]);
				int phone = Integer.parseInt(arguments[6]);
				String birthdayString = arguments[7];
				Professor professor = new Professor(rut, name, lastnameFather, lastnameMother, address, gender, phone, birthdayString);
				professors.add(professor);
				professorString = bufferedReader.readLine();
			}
			fileInputStream.close();		
		} catch (IOException ioException) {
			System.err.println ("Unable to read from file");
			System.out.println(ioException);
		}

		return professors;
	}
}
