package backend.manager;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

import backend.others.Utilities;
import backend.users.Assistant;
import backend.users.User.Gender;

/**
 * Class that manages the reading and writing of all the assistants from the 'database'.
 */
public class AssistantsReaderWriter {

	/**
	 * Writes all the assistants to the assistants.txt file.
	 * @param professors The assistants to be written.
	 */
	public static void writeAssistants(ArrayList<Assistant> assistants) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream (FolderFileManager.adminAssistants);
			PrintStream printStream = new PrintStream(fileOutputStream);
			for (Assistant assistant : assistants) {
				printStream.println(assistant.getRut());
				printStream.println("&");
				printStream.println(assistant.getName());
				printStream.println("&");
				printStream.println(assistant.getLastnameFather());
				printStream.println("&");
				printStream.println(assistant.getLastnameMother());
				printStream.println("&");
				printStream.println(assistant.getAddress());
				printStream.println("&");
				printStream.println(assistant.getGender());
				printStream.println("&");
				printStream.println(assistant.getPhone());
				printStream.println("&");
				printStream.println(Utilities.getStringFromDate(assistant.getBirthday()));
			}
			fileOutputStream.close();		
		} catch (IOException ioException) {
			System.err.println ("Unable to write to file");
			System.out.println(ioException);
		}
	}
	
	/**
	 * Reads all the assistants from the assistants.txt file and returns them in a list.
	 * @return The assistants list.
	 */
	public static ArrayList<Assistant> readAssistants() {
		ArrayList<Assistant> assistants = new ArrayList<Assistant>();
		
		try {
			FileInputStream fileInputStream = new FileInputStream (FolderFileManager.adminAssistants);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
			String assistantString = bufferedReader.readLine();
			while (assistantString != null ) {
				String[] arguments = assistantString.split("&");
				String rut = arguments[0];
				String name = arguments[1];
				String lastnameFather = arguments[2];
				String lastnameMother = arguments[3];
				String address = arguments[4];
				Gender gender = Gender.valueOf(arguments[5]);
				int phone = Integer.parseInt(arguments[6]);
				String birthdayString = arguments[7];
				Assistant assistant = new Assistant(rut, name, lastnameFather, lastnameMother, address, gender, phone, birthdayString);
				assistants.add(assistant);
				assistantString = bufferedReader.readLine();
			}
			fileInputStream.close();		
		} catch (IOException ioException) {
			System.err.println ("Unable to read from file");
			System.out.println(ioException);
		}
		return assistants;
	}
}
