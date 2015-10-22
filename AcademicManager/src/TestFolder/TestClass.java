package TestFolder;

import java.io.*;
import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;

public class TestClass extends Application{

	public static void main(String [ ] args) {
		Application.launch(TestClass.class, args);
	}

	@Override
	public void start(Stage primaryStage){
		URL location = getClass().getResource("Home.fxml");
		FXMLLoader fxmlLoader = new FXMLLoader(location);
		fxmlLoader.setController(new HomeController());
		Pane root = null;
		try {
			root = (Pane)fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Scene scene = new Scene(root,800,600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void writeAndRead() {
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
