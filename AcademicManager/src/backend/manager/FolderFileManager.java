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
		|-> Admin
			|-> admins.txt						Contiene información de todos los administradores
			|-> assistants.txt					Contiene inforamción de todos los ayudantes
			|-> classrooms.txt					Todas las salas de clases con su informacion
			|-> courseCoRequirements.txt		Cada linea: sigla curso&sigla co requisito
			|-> courseCourses.txt				Todas las clases con toda su informacion mas prefijo: sigla curso&seccion
			|-> courseRequirements.txt			Cada linea: sigla curso&sigla requisito
			|-> courses.txt						Todos los cursos con su informacion directa (sigla, seccion, creditos)
			|-> evaluations.txt					Todas las evaluaciones con su informacion mas prefijo: sigla curso&seccion
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
				|-> coursedCourses.txt			Todas las clases con toda su informacion de los cursos ya cursados mas prefijo: sigla curso&seccion
				|-> courseds.txt				Todos los cursos cursados
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

	// Admin folders
	public static String adminStudyPrograms 		= rootAdmin + "/StudyPrograms";				// Contains all the study programs with their courses separated in folders each containing a studyProgram.txt and files semester1.txt, semester2.txt, ...

	// Required admin files
	public static String admins						= rootAdmin + "/admins.txt";				// Contains all the admins
	public static String adminCourses				= rootAdmin + "/courses.txt";				// Contains all the Courses
	public static String adminCourseRequirements 	= rootAdmin + "/courseRequirements.txt";	// Contains all the requirements of the courses
	public static String adminCourseCoRequirements 	= rootAdmin + "/courseCoRequirements.txt";	// Contains all the corequirements of the courses
	public static String adminClassrooms 			= rootAdmin + "/classrooms.txt";			// Contains all the classrooms
	public static String adminProfessors 			= rootAdmin + "/professors.txt";			// Contains all the professors
	public static String adminAssistants 			= rootAdmin + "/assistants.txt";			// Contains all the assistants
	public static String adminEvaluations 			= rootAdmin + "/evaluations.txt";			// Contains all the evaluations of the courses
	public static String adminCourseCourses 		= rootAdmin + "/courseCourses.txt";			// Contains all the physical classes of the Courses with classroom, schedule, professors and assistants

	// Required student files
	public static String students					= rootStudent + "/students.txt";			// Contains all the students

	// Required student files for each student
	public static String studentCurriculums 		= "/curriculums.txt";
	public static String studentCoursed				= "/coursed.txt";							// Contains all the coursed courses of the student
	public static String studentCoursedCourses		= "/coursedCourses.txt";					// Contains all the physical classes of the Courses with classroom, schedule, professors and assistants
	public static String studentCourses 			= "/courses.txt";							// Contains the current courses

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
		folders.add(studentCurriculums);
		folders.add(studentStudyPrograms);
		folders.add(studentsFolders);

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
		files.add(adminCourses);
		files.add(adminCourseRequirements);
		files.add(adminCourseCoRequirements);
		files.add(adminClassrooms);
		files.add(adminProfessors);
		files.add(adminAssistants);
		files.add(adminEvaluations);
		files.add(adminCourseCourses);
		files.add(students);

		for (String fileString : files) {
			File file = new File(fileString);
			if (!file.exists()) {
				try {
					FileOutputStream fileOutputStream = new FileOutputStream (fileString);
					fileOutputStream.close();
				} catch (FileNotFoundException fileNotFoundException) {
					System.out.println(fileNotFoundException);
				} catch (IOException iOException) {
					System.out.println(iOException);
				}
			}
		}

		ArrayList<String> studentsFiles = new ArrayList<String>();
		studentsFiles.add(studentCoursed);
		studentsFiles.add(studentCoursedCourses);
		studentsFiles.add(studentCourses);

		File folderStudents = new File(studentsFolders);
		String[] studentsFolders = folderStudents.list();

		for (String studentFolderString : studentsFolders) {
			File studentFolder = new File(studentFolderString);
			if (studentFolder.isDirectory()) {
				for (String studentFile : studentsFiles) {
					File file = new File(studentFile);
					if (!file.exists()) {
						try {
							FileOutputStream fileOutputStream = new FileOutputStream (studentFile);
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
	}
}
