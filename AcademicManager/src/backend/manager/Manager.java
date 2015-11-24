package backend.manager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.core.ZipFile;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWriteMode;

import backend.courses.Classroom;
import backend.courses.Course;
import backend.courses.Evaluation;
import backend.courses.Schedule;
import backend.courses.Semester;
import backend.courses.StudyProgram;
import backend.enums.AcademicSemester;
import backend.interfaces.ICourse;
import backend.others.Messages.SupportedLanguage;
import backend.users.Admin;
import backend.users.Assistant;
import backend.users.Professor;
import backend.users.Student;
import backend.users.User;

/**
 * [Singleton] Main class that does what the application user requires. Contains
 * all the objects of the program. Data container.
 */
public class Manager {
	
	public DbxRequestConfig config = new DbxRequestConfig("Upload", Locale.getDefault().toString());
	public DbxClient client = new DbxClient(config, "dVvPUaRLEWAAAAAAAAAABzuW5o9besAnmRMCxddf_Gs3-PxoHn9WWKBGlVFGjakI");
	private byte[] zipBuffer = new byte[1024];
	
	public final static Calendar CALENDAR = Calendar.getInstance();
	public final static Manager INSTANCE = new Manager();

	public User currentUser;
	public String alertMessage;
	
	public ArrayList<Classroom> classrooms = new ArrayList<Classroom>();
	public ArrayList<Course> courses = new ArrayList<Course>();
	public ArrayList<StudyProgram> studyPrograms = new ArrayList<StudyProgram>();

	public ArrayList<Admin> admins = new ArrayList<Admin>();
	public ArrayList<Assistant> assistants = new ArrayList<Assistant>();
	public ArrayList<Professor> professors = new ArrayList<Professor>();
	public ArrayList<Student> students = new ArrayList<Student>();

	public Course courseToShowInfo;
	public Semester currentSemester;

	public StudyProgram currentEditingStudyProgram;
	public Course currentEditignCourse;
	public Schedule currentEditingSchedule;
	public Evaluation currentEditingEvaluation;
	public ICourse currentEditignICourse;
	
	/**
	 * Creates the instance of manager.
	 */
	private Manager() {
		try {
			System.out.println("Linked account: " + client.getAccountInfo().displayName);
		} catch (DbxException e) {
//			e.printStackTrace();
			System.out.println("Account link failed");
		}
	}

	/**
	 * Reloads all the data from the server
	 */
	public void reloadData() {
		if (currentUser == null) {
			downloadData();
		} else {
			saveData();
		}
	}
	
	/**
	 * MUST BE CALLED ONLY BY saveData().
	 */
	private void uploadData() {
		System.out.println("Starting upload...");
		File rootFolder = new File(FolderFileManager.rootFolder);
		try {
			// Create zip
			String zipName = FolderFileManager.rootFolder + ".zip";
			FileOutputStream zipFileOutputStream = new FileOutputStream(zipName);
			ZipOutputStream zip = new ZipOutputStream(zipFileOutputStream);
			addFileOrFolderToZip(zip, rootFolder);
			zip.close();
			
			// Upload zip
			File zipFile = new File(zipName);
			FileInputStream zipFileStream = new FileInputStream(zipFile);
		    DbxEntry.File uploadedFile = client.uploadFile("/" + zipName,
		        DbxWriteMode.force(), zipFile.length(), zipFileStream);
		    System.out.println("Uploaded: " + uploadedFile.name);
		    zipFileStream.close();
		} catch (FileNotFoundException e) {
//			e.printStackTrace();
			System.out.println("Upload failed");
		} catch (IOException e) {
//			e.printStackTrace();
			System.out.println("Upload failed");
		} catch (DbxException e) {
//			e.printStackTrace();
			System.out.println("Upload failed");
		}
	}
	
	private void addFileOrFolderToZip(ZipOutputStream zip, File fileOrFolder) {
		if (fileOrFolder.isDirectory()) {
			for (File childFileOrFolder : fileOrFolder.listFiles()) {
				addFileOrFolderToZip(zip, childFileOrFolder);
			}
		} else if (!(fileOrFolder.getName().contains(".DS_Store"))) {
			try {
				FileInputStream inputStream = new FileInputStream(fileOrFolder);
				String rawPath = fileOrFolder.getAbsolutePath();
				String[] splitedPath = rawPath.split(FolderFileManager.rootFolder);
				String realPath = "/" + FolderFileManager.rootFolder + splitedPath[splitedPath.length - 1];
				zip.putNextEntry(new ZipEntry(cleanSlashes(realPath)));
				int len;
				while ((len = inputStream.read(zipBuffer)) > 0) {
					zip.write(zipBuffer, 0, len);
				}
				zip.closeEntry();
				inputStream.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private String cleanSlashes(String stringToClear) {
		String cleaned = "";
		for (String character : stringToClear.split("")) {
			if (character == "\\" && cleaned.substring(cleaned.length() - 1, cleaned.length()) != "/") {
				cleaned += character;
			} else if (character == "\\") {
			
			} else {
				cleaned += character;
			}
		}
		return cleaned;
	}
	
	/**
	 * Downloads data from Dropbox and calls loadData().
	 */
	public void downloadData() {
		System.out.println("Starting download...");
		DbxEntry.WithChildren listing;
		try {
			Boolean shouldLoadLocal = true;
			listing = client.getMetadataWithChildren("/");
			for (DbxEntry child : listing.children) {
				if (child.name.equals(FolderFileManager.rootFolder + ".zip")) {
					FileOutputStream downloadFileOutputStream = new FileOutputStream(child.name);
					DbxEntry.File downloadedFile = client.getFile("/" + child.name, null,
							downloadFileOutputStream);
					System.out.println("Downloaded: " + downloadedFile.name);
					downloadFileOutputStream.close();
					extractData(downloadedFile);
					shouldLoadLocal = false;
					break;
				}
			}
			if (shouldLoadLocal) {
				System.out.println("Server doesn't have data, loading local data");
				Manager.INSTANCE.loadData();
			}
		} catch (DbxException e) {
			System.out.println("Download failed, loading local data");
			Manager.INSTANCE.loadData();
//			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println("Download failed, loading local data");
			Manager.INSTANCE.loadData();
//			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Download failed, loading local data");
			Manager.INSTANCE.loadData();
//			e.printStackTrace();
		}
	}
	
	private void extractData(DbxEntry downloadedFile) {
		System.out.println("Starting data extraction...");
		try {
			String zipName = downloadedFile.name;
			File oldDirectory = new File(FolderFileManager.rootFolder);
			deleteFolderContentOrFile(oldDirectory);
			
			String source = zipName;
			int pathSize = oldDirectory.getAbsolutePath().length();
			String destination = oldDirectory.getAbsolutePath().substring(0, pathSize - "Documents".length());
			String password = "";
			System.out.println("destination: " + destination);
			ZipFile zipFile = new ZipFile(source);
			if (zipFile.isEncrypted()) {
				zipFile.setPassword(password);
			}
			zipFile.extractAll(destination);

			System.out.println("Data extracted!");
			Manager.INSTANCE.loadData();
		} catch (ZipException e) {
			System.out.println("Extraction failed, loading local data");
			Manager.INSTANCE.loadData();
//			e.printStackTrace();
		}
	}
	
	private void deleteFolderContentOrFile(File folderOrFile) {
		for (File subFolderOrFile : folderOrFile.listFiles()) {
			if (subFolderOrFile.isDirectory()) {
				deleteFolderContentOrFile(subFolderOrFile);
				subFolderOrFile.delete();
			} else {
				subFolderOrFile.delete();
			}
		}
		folderOrFile.delete();
	}

	/**
	 * MUST BE CALLED ONLY BY downloadData().
	 * Loads all the data from the `database`.
	 */
	private void loadData() {
		System.out.println("Loading data...");

		if (getMonth() <= 6 && getDay() <= 19) {
			currentSemester = new Semester(AcademicSemester.FIRST, getYear(), 0, null, null);
		} else {
			currentSemester = new Semester(AcademicSemester.SECOND, getYear(), 0, null, null);
		}

		admins = AdminReaderWriter.readAdmins();
		assistants = AssistantsReaderWriter.readAssistants();
		classrooms = ClassroomReaderWriter.readClasrooms();
		courses = CoursesReaderWriter.readCourses();
		professors = ProfessorsReaderWriter.readProfessors();
		studyPrograms = StudyProgramsReaderWriter.readStudyPrograms(courses);
		CourseCoursesReaderWriter.readCourseCourses(courses, assistants, professors, classrooms);
		EvaluationsReaderWriter.readCoursesEvaluations(courses, classrooms);
		CourseCoRequirementsReaderWriter.readCoursesCoRequirements(courses);
		CourseRequirementsReaderWriter.readCoursesRequirements(courses);
		DetailsReaderWriter.readDetails(courses);
		students = StudentsReaderWriter.readStudents(
				courses, 
				studyPrograms, 
				classrooms, 
				currentSemester, 
				professors,
				assistants
		);
		ForumReaderWriter.readForums(courses, users());

		System.out.println("Data loaded!");
	}

	/**
	 * Writes all the data to the `database`.
	 * Then calls uploadData().
	 */
	public void saveData() {
		File oldDirectory = new File(FolderFileManager.rootFolder);
		deleteFolderContentOrFile(oldDirectory);
		FolderFileManager.checkFolders();
		System.out.println("Starting to save data...");
		AdminReaderWriter.writeAdmins(admins);
		AssistantsReaderWriter.writeAssistants(assistants);
		ClassroomReaderWriter.writeClassrooms(classrooms);
		CoursesReaderWriter.writeCourses(courses);
		ProfessorsReaderWriter.writeProfessors(professors);
		StudyProgramsReaderWriter.writeStudyPrograms(studyPrograms);
		CourseCoursesReaderWriter.writeCoursesCourses(courses);
		EvaluationsReaderWriter.writeCoursesEvaluations(courses);
		CourseCoRequirementsReaderWriter.writeCoursesCoRequirements(courses);
		CourseRequirementsReaderWriter.writeCoursesRequirements(courses);
		DetailsReaderWriter.writeDetails(courses);
		StudentsReaderWriter.writeStudents(students);
		ForumReaderWriter.writeForums(courses);

		System.out.println("Data saved!");
		uploadData();
	}

	/**
	 * Switches language between Spanish and English for the software.
	 */
	public void changeLanguage() {
		try {
			FileInputStream fileInputStream = new FileInputStream(FolderFileManager.language);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
			String languageString = bufferedReader.readLine();
			SupportedLanguage language = SupportedLanguage.defaultLanguage();
			if (languageString != null && languageString != "") {
				language = SupportedLanguage.valueOf(languageString);
			}
			bufferedReader.close();
			fileInputStream.close();
			
			FileOutputStream fileOutputStream = new FileOutputStream(FolderFileManager.language);
			PrintStream printStream = new PrintStream(fileOutputStream);

			switch (language) {
			case ENGLISH:
				printStream.println(SupportedLanguage.SPANISH.toString());
			case SPANISH:
				printStream.println(SupportedLanguage.ENGLISH.toString());
			default:
				break;
			}

			fileOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}
	}

	public int getNewStudentID() {
		int maxID = 0;
		for (Student student : students) {
			if (student.getId() > maxID) {
				maxID = student.getId();
			}
		}
		return maxID + 1;
	}

	public StudyProgram getStudyProgramForName(String name) {
		for (StudyProgram studyProgram : studyPrograms) {
			if (studyProgram.getName().equals(name)) {
				return studyProgram;
			}
		}
		return null;
	}

	public static int getYear() {
		return CALENDAR.get(Calendar.YEAR);
	}

	public static int getMonth() {
		return CALENDAR.get(Calendar.MONTH);
	}

	public static int getDay() {
		return CALENDAR.get(Calendar.DAY_OF_MONTH);
	}
	
	public ArrayList<User> users() {
		ArrayList<User> users = new ArrayList<User>();
		for (User user : admins) {
			users.add(user);
		}
		for (User user : assistants) {
			users.add(user);
		}
		for (User user : professors) {
			users.add(user);
		}
		for (User user : students) {
			users.add(user);
		}
		return users;
	}
}
