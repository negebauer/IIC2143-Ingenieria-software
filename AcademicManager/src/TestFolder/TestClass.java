package TestFolder;

import java.io.*;

public class TestClass {

	public static void main(String [ ] args) {
		System.out.println("Hello, World");
		
//		TODO Delete this class

		// Stream to write file
		FileOutputStream fout;		

		try
		{
			// Open an output stream
			fout = new FileOutputStream ("myfile.txt");

			// Print a line of text
			new PrintStream(fout).println ("hello world!");

			// Close our output stream
			fout.close();		
		}
		// Catches any error conditions
		catch (IOException e)
		{
			System.err.println ("Unable to write to file");
			System.exit(-1);
		}
		
		// Stream to read file
		FileInputStream fin;		

		try
		{
			// Open an input stream
			fin = new FileInputStream ("myfile.txt");

			// Read a line of text
			System.out.println( new BufferedReader(new InputStreamReader(fin)).readLine());

			// Close our input stream
			fin.close();		
		}
		// Catches any error conditions
		catch (IOException e)
		{
			System.err.println ("Unable to read from file");
			System.exit(-1);
		}
	}
} 
