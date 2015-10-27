package backend.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class that manages the folders of the 'database'.
 */
public class FolderFileManager {

	/* Folder system
		Documents
		|-> language.txt						Contiene el lenguaje del sistema
		|-> Admin
			|-> admins.txt						Contiene informacion de todos los administradores
			|-> assistants.txt					Contiene inforamcion de todos los ayudantes
			|-> classrooms.txt					Todas las salas de clases con su informacion
			|-> courseCoRequirements.txt		Cada linea: sigla curso&sigla co requisito
			|-> courseCourses.txt				Todas las clases con toda su informacion mas prefijo: sigla curso&seccion
			|-> courseRequirements.txt			Cada linea: sigla curso&sigla requisito
			|-> courses.txt						Todos los cursos con su informacion directa (sigla, seccion, creditos)
			|-> evaluations.txt					Todas las evaluaciones con su informacion mas prefijo: sigla curso&seccion
			|-> professors.txt					Contiene informacion de todos los profesores
			|-> StudyPrograms					Todos los programas de estudio
				|-> Escuela1					Una escuela de la universidad. Llamar carpeta con el nombre de la escuela
				|-> Escuela2
					|-> StudyProgram1			Un programa de estudio. Llamar carpeta con el nombre del programa de estudio
					|-> StudyProgram2
						|-> studyProgram.txt	Informacion del programa de estudio
						|-> semester1.txt		Cursos del primer semester. Solo la sigla
						|-> semester2.txt		Cursos del segundo semester. Solo la sigla
		|-> Student
			|-> Student1						Un estudiante. Llamar carpeta como id_nombre_apellidoPaterno
			|-> Student2
				|-> coursed.txt					Todos los cursos cursados
				|-> coursedCourses.txt			Todas las clases con toda su informacion de los cursos ya cursados mas prefijo: sigla curso&seccion
				|-> courses.txt					Todos los cursos que se estan cursando
				|-> student.txt					Informacion del estudiante
				|-> studyPrograms.txt			Los programas de estudio del alumno (nombres)
	 */

	// TODO Check that all required folders and files are present
	// TODO Check that all information is accesible

	// Main folders
	public static String rootFolder 				= "Documents";
	public static String rootAdmin 					= rootFolder + "/Admin";
	public static String rootStudent 				= rootFolder + "/Student";

	// Main files
	public static String language	 				= rootFolder + "/language.txt";
	
	// Admin folders
	public static String adminStudyPrograms 		= rootAdmin + "/StudyPrograms";

	// Required files for each study program
	public static String adminStudyProgramInfo 		= "/studyProgram.txt";	

	// Required admin files
	public static String admins						= rootAdmin + "/admins.txt";
	public static String adminAssistants 			= rootAdmin + "/assistants.txt";
	public static String adminClassrooms 			= rootAdmin + "/classrooms.txt";
	public static String adminCourseCoRequirements 	= rootAdmin + "/courseCoRequirements.txt";
	public static String adminCourseCourses 		= rootAdmin + "/courseCourses.txt";
	public static String adminCourseRequirements 	= rootAdmin + "/courseRequirements.txt";
	public static String adminCourses				= rootAdmin + "/courses.txt";
	public static String adminEvaluations 			= rootAdmin + "/evaluations.txt";
	public static String adminProfessors 			= rootAdmin + "/professors.txt";

	// Required student files for each student
	public static String studentCoursed				= "/coursed.txt";
	public static String studentCoursedCourses		= "/coursedCourses.txt";
	public static String studentCourses 			= "/courses.txt";
	public static String studentInfo				= "/student.txt";
	public static String studentStudyPrograms		= "/studyPrograms.txt";

	/**
	 * Makes sure that all the required folders and files exist.
	 * Basically, maintains the folder hierarchy.
	 */
	public static void checkFolders() {
		ArrayList<String> folders = new ArrayList<String>();
		folders.add(rootFolder);
		folders.add(rootAdmin);
		folders.add(rootStudent);
		folders.add(adminStudyPrograms);

		for (String folderString : folders) {
			File folder = new File(folderString);
			if (!folder.exists()) {
				try {
					folder.mkdir();
				} catch (SecurityException securityException) {
					System.out.println(securityException);
				}
			}
		}

		ArrayList<String> files = new ArrayList<String>();
		files.add(language);
		files.add(admins);
		files.add(adminAssistants);
		files.add(adminClassrooms);
		files.add(adminCourseCoRequirements);
		files.add(adminCourseCourses);
		files.add(adminCourseRequirements);
		files.add(adminCourses);
		files.add(adminEvaluations);
		files.add(adminProfessors);

		for (String fileString : files) {
			File file = new File(fileString);
			if (!file.exists()) {
				try {
					FileOutputStream fileOutputStream = new FileOutputStream(fileString);
					fileOutputStream.close();
				} catch (FileNotFoundException fileNotFoundException) {
					System.out.println(fileNotFoundException);
				} catch (IOException iOException) {
					System.out.println(iOException);
				}
			}
		}
	}
}
