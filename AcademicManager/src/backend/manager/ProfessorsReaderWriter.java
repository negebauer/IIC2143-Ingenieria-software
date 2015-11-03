package backend.manager;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

import backend.others.Utilities;
import backend.users.Professor;
import backend.users.User.Gender;

/**
 * Class that manages the reading and writing of all the professors from the 'database'.
 */
public class ProfessorsReaderWriter {

	/* File format
		rut&name&lastnameFather&lastnameMother&address&gender&access&phone&birthdayString
	*/

	/**
	 * Writes all the professors to the professors.txt file.
	 * @param professors The professors to be written.
	 */
	public static void writeProfessors(ArrayList<Professor> professors) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream (FolderFileManager.adminProfessors);
			PrintStream printStream = new PrintStream(fileOutputStream);
			for (Professor professor : professors) {
				printStream.print(professor.getRut());
				printStream.print("&");
				printStream.print(professor.getName());
				printStream.print("&");
				printStream.print(professor.getLastnameFather());
				printStream.print("&");
				printStream.print(professor.getLastnameMother());
				printStream.print("&");
				printStream.print(professor.getAddress());
				printStream.print("&");
				printStream.print(professor.getGender());
				printStream.print("&");
				printStream.print(professor.getPhone());
				printStream.print("&");
				printStream.print(Utilities.getStringFromDate(professor.getBirthday()));
				printStream.println();
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
				System.out.print(professorString);
				String rut = arguments[0];
				String name = arguments[1];
				String lastnameFather = arguments[2];
				String lastnameMother = arguments[3];
				String address = arguments[4];
				Gender gender = Gender.valueOf(arguments[5]);
				String phone = arguments[6];
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
