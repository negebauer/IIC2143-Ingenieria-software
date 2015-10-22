package System.Manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class that manages the folders of the 'database'.
 */
public class FolderFileManager {

	// TODO Check that all required folders and files are present
	// TODO Check that all information is accesible
	
	// Folders
	public static String rootFolder 				= "Documents";
	public static String rootAdmin 					= rootFolder + "/Admin";
	public static String rootStudent 				= rootFolder + "/Student";
	public static String adminStudyProgram 			= rootAdmin + "/StudyPrograms";				// Contains all the study programs with their courses separated in folders each containing a studyProgram.txt and files semester1.txt, semester2.txt, ...

	// Required admin files
	public static String adminCourses				= rootAdmin + "/courses.txt";				// Contains all the Courses
	public static String adminCourseRequirements 	= rootAdmin + "/courseRequirements.txt";	// Contains all the requirements of the courses
	public static String adminCourseCoRequirements 	= rootAdmin + "/courseCoRequirements.txt";	// Contains all the corequirements of the courses
	public static String adminClassrooms 			= rootAdmin + "/classrooms.txt";			// Contains all the classrooms
	public static String adminProfessors 			= rootAdmin + "/professors.txt";			// Contains all the professors
	public static String adminAssistants 			= rootAdmin + "/assistants.txt";			// Contains all the assistants
	public static String adminEvaluations 			= rootAdmin + "/evaluations.txt";			// Contains all the evaluations of the courses
	public static String adminCourseCourses 		= rootAdmin + "/courseCourses.txt";			// Contains all the physical classes of the Courses with classroom, schedule, professors and assistants

	// Required student files
	public static String studentCoursed				= rootStudent + "/coursed.txt";				// Contains all the coursed courses of the student
	public static String studentCoursedCourses		= rootStudent + "/coursedCourses.txt";		// Contains all the physical classes of the Courses with classroom, schedule, professors and assistants
	public static String studentCourses 			= rootStudent + "/courses.txt";				// Contains the current courses
	public static String studentStudyPrograms		= rootStudent + "/StudyPrograms";			// Contains the name of the student study programs

	/**
	 * Makes sure that all the required folders and files exist.
	 * Basically, maintains the folder hierarchy.
	 */
	public static void checkFolders() {
		ArrayList<String> folders = new ArrayList<String>();
		folders.add(rootFolder);
		folders.add(rootAdmin);
		folders.add(rootStudent);
		folders.add(adminStudyProgram);

		ArrayList<String> files = new ArrayList<String>();
		files.add(adminCourses);
		files.add(adminCourseRequirements);
		files.add(adminCourseCoRequirements);
		files.add(adminClassrooms);
		files.add(adminProfessors);
		files.add(adminAssistants);
		files.add(adminEvaluations);
		files.add(adminCourseCourses);
		files.add(studentCoursed);
		files.add(studentCoursedCourses);
		files.add(studentCourses);
		files.add(studentStudyPrograms);

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

	}
}
