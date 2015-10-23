package backend.manager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

import backend.courses.Assistantship;
import backend.courses.Classroom;
import backend.courses.Course;
import backend.courses.Coursed;
import backend.courses.Curriculum;
import backend.courses.Laboratory;
import backend.courses.Lecture;
import backend.courses.Schedule;
import backend.courses.Schedule.Day;
import backend.courses.Schedule.DayModuleTuple;
import backend.courses.Schedule.Module;
import backend.courses.Semester;
import backend.courses.StudyProgram;
import backend.enums.AcademicSemester;
import backend.enums.School;
import backend.users.Assistant;
import backend.users.Professor;
import backend.users.Student;
import backend.users.User.Gender;

/**
 * Class that manages the reading and writing of all the students from the 'database'.
 * This includes reading the current courses, coursed courses, student info, studyProgram, etc.
 */
public class StudentsReaderWriter {

	/* Files to be read and written
		|-> Student
			|-> Student1
			|-> Student2
				|-> coursed.txt			Line format: name&initials&section&credits&details&school&semester&year&approved&grade
				|-> coursedCourses.txt	Line format: 3 cases
											- Assistantship:	initials&section&year&semester&ASSISTANTSHIP&name:lastNameFather:lastNameMother;name:lastNameFather:lastNameMother&initials;school;campus;size&dia1:modulo1;dia2:modulo2
											- Laboratory:		initials&section&year&semester&LABORATORY&name:lastNameFather:lastNameMother;name:lastNameFather:lastNameMother&initials;school;campus;size&dia1:modulo1;dia2:modulo2
											- Lecture:			initials&section&year&semester&LECTURE&name:lastNameFather:lastNameMother;name:lastNameFather:lastNameMother&initials;school;campus;size&dia1:modulo1;dia2:modulo2
				|-> courses.txt			Line format: initials&section
				|-> student.txt			Line format: id&yearEntrance&yearGraduation&regularStudent&rut&name&lastnameFather&lastnameMother&address&gender&access&phone&birthdayString
				|-> studyPrograms.txt	Line format: name&year
	*/

	/**
	 * Writes all the students to the professors.txt file.
	 * @param students The students to be written.
	 */
	public static void writeStudents(ArrayList<Student> students) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream (FolderFileManager.adminProfessors);
			PrintStream printStream = new PrintStream(fileOutputStream);
			for (Student student : students) {
				// TODO Save students
				printStream.println("SOMETHING");
			}
			fileOutputStream.close();		
		} catch (IOException ioException) {
			System.err.println ("Unable to write to file");
			System.out.println(ioException);
		}
	}
	
	/**
	 * Reads all the students from the Students folder and returns them in a list.
	 * <br><b>IMPORTANT</b>: This method <b>MUST</b> be called <b>AFTER</b> reading:
	 * <ul>
	 * <li> All the courses with their schedules and evaluations
	 * <li> All the study programs
	 * </ul>
	 * @param allCourses All the courses of the system.
	 * @param allStudyPrograms All the studyPrograms of the system.
	 * @param allClassrooms All the classrooms of the system.
	 * @param currentSemester Just a current semester object to extract the semester and year.
	 * @return The students list.
	 */
	public static ArrayList<Student> readStudents(ArrayList<Course> allCourses, ArrayList<StudyProgram> allStudyPrograms, ArrayList<Classroom> allClassrooms, Semester currentSemester) {
		ArrayList<Student> students = new ArrayList<Student>();
		try {
			File studentRootFolder = new File(FolderFileManager.rootStudent);
			String[] studentsFolderString = studentRootFolder.list();
			for (String studentFolderString : studentsFolderString) {
				if (studentFolderString == null) {
					break;
				}
				try {
					FileInputStream coursed 					= new FileInputStream(FolderFileManager.studentCoursed);
					FileInputStream coursedCourses 				= new FileInputStream(FolderFileManager.studentCoursedCourses);
					FileInputStream courses 					= new FileInputStream(FolderFileManager.studentCourses);
					FileInputStream student 					= new FileInputStream(FolderFileManager.studentInfo);
					FileInputStream studyPrograms 				= new FileInputStream(FolderFileManager.studentStudyPrograms);

					BufferedReader coursedReader 				= new BufferedReader(new InputStreamReader(coursed));
					BufferedReader coursedCoursesReader			= new BufferedReader(new InputStreamReader(coursedCourses));
					BufferedReader coursesReader 				= new BufferedReader(new InputStreamReader(courses));
					BufferedReader studentReader 				= new BufferedReader(new InputStreamReader(student));
					BufferedReader studyProgramsReader 			= new BufferedReader(new InputStreamReader(studyPrograms));

					ArrayList<Coursed> coursedList 				= new ArrayList<Coursed>();
					ArrayList<Course> coursesList 				= new ArrayList<Course>();
					Student student1							= new Student(0, 0, null, null, null, null, null, null, null, 0, null);
					ArrayList<StudyProgram> studyProgramsList 	= new ArrayList<StudyProgram>();

					String coursedString 						= coursedReader.readLine();
					String coursedCoursesString					= coursedCoursesReader.readLine();
					String coursesString 						= coursesReader.readLine();
					String studentString 						= studentReader.readLine();
					String studyProgramsString 					= studyProgramsReader.readLine();

					while (coursedString != null) {
						String[] arguments = coursedString.split("&");
						String name = arguments[0];
						String initials = arguments[1];
						int section = Integer.parseInt(arguments[2]);
						int credits = Integer.parseInt(arguments[3]);
						String details = arguments[4];
						School school = School.valueOf(arguments[5]);
						AcademicSemester semester = AcademicSemester.valueOf(arguments[6]);
						int year = Integer.parseInt(arguments[7]);
						boolean approved = Boolean.valueOf(arguments[8]);
						double grade = Double.parseDouble(arguments[9]);
						Coursed coursedLoaded = new Coursed(name, initials, section, credits, details, school, semester, year, approved, grade);
						coursedList.add(coursedLoaded);
						coursedString = coursedReader.readLine();
					}

					while (coursedCoursesString != null) {
						// initials&section&year&semester&ASSISTANTSHIP&name:lastNameFather:lastNameMother;name:lastNameFather:lastNameMother&initials;school;campus;size&dia1:modulo1;dia2:modulo2
						// initials&section&year&semester&LABORATORY&name:lastNameFather:lastNameMother;name:lastNameFather:lastNameMother&initials;school;campus;size&dia1:modulo1;dia2:modulo2
						// initials&section&year&semester&LECTURE&name:lastNameFather:lastNameMother;name:lastNameFather:lastNameMother&initials;school;campus;size&dia1:modulo1;dia2:modulo2
						String[] arguments = coursedCoursesString.split("&");
						String initials = arguments[0];
						int section = Integer.parseInt(arguments[1]);
						int year = Integer.parseInt(arguments[2]);
						AcademicSemester semester = AcademicSemester.valueOf(arguments[3]);
						String classroomInitials = arguments[6];
						Classroom classroom = new Classroom(null, null, null, -1);
						for (Classroom possibleClassroom : allClassrooms) {
							if (possibleClassroom.getInitials() == classroomInitials) {
								classroom = possibleClassroom;
							}
						}
						Schedule schedule = new Schedule();
						String[] schedulesString = arguments[7].split(";");
						for (String scheduleString : schedulesString) {
							String[] scheduleStringArguments = scheduleString.split(":");
							DayModuleTuple dayModuleTuple = schedule.new DayModuleTuple(Day.valueOf(scheduleStringArguments[0]), Module.valueOf(scheduleStringArguments[1]));
							schedule.addModule(dayModuleTuple);
						}
						switch (arguments[4]) {
						case "ASSISTANTSHIP":
							ArrayList<Assistant> assistants = new ArrayList<Assistant>();
							for (String assistantString : arguments[5].split(";")) {
								String[] assistantArguments = assistantString.split(":");
								String name = assistantArguments[0];
								String lastnameFather = assistantArguments[1];
								String lastnameMother = assistantArguments[2];
								Assistant assistant = new Assistant(null, name, lastnameFather, lastnameMother, null, null, -1, null);
								assistants.add(assistant);
							}
							Assistantship assistantship = new Assistantship(assistants, classroom, schedule);
							for (Coursed possibleCoursed : coursedList) {
								if (possibleCoursed.getInitials() == initials && possibleCoursed.getSection() == section && possibleCoursed.getYear() == year && possibleCoursed.getSemester() == semester) {
									possibleCoursed.addICourse(assistantship);
								}
							}
						case "LABORATORY":
							ArrayList<Professor> professors = new ArrayList<Professor>();
							for (String professorString : arguments[5].split(";")) {
								String[] professorArguments = professorString.split(":");
								String name = professorArguments[0];
								String lastnameFather = professorArguments[1];
								String lastnameMother = professorArguments[2];
								Professor professor = new Professor(null, name, lastnameFather, lastnameMother, null, null, -1, null);
								professors.add(professor);
							}
							Laboratory laboratory = new Laboratory(professors, classroom, schedule);
							for (Coursed possibleCoursed : coursedList) {
								if (possibleCoursed.getInitials() == initials && possibleCoursed.getSection() == section && possibleCoursed.getYear() == year && possibleCoursed.getSemester() == semester) {
									possibleCoursed.addICourse(laboratory);
								}
							}
						case "LECTURE":
							ArrayList<Professor> professors2 = new ArrayList<Professor>();
							for (String professorString : arguments[5].split(";")) {
								String[] professorArguments = professorString.split(":");
								String name = professorArguments[0];
								String lastnameFather = professorArguments[1];
								String lastnameMother = professorArguments[2];
								Professor professor = new Professor(null, name, lastnameFather, lastnameMother, null, null, -1, null);
								professors2.add(professor);
							}
							Lecture lecture = new Lecture(professors2, classroom, schedule);
							for (Coursed possibleCoursed : coursedList) {
								if (possibleCoursed.getInitials() == initials && possibleCoursed.getSection() == section && possibleCoursed.getYear() == year && possibleCoursed.getSemester() == semester) {
									possibleCoursed.addICourse(lecture);
								}
							}
						}
						coursedCoursesString = coursedCoursesReader.readLine();
					}
					
					while (coursesString != null) {
						String[] arguments = coursesString.split("&");
						String initials = arguments[0];
						int section = Integer.parseInt(arguments[1]);
						for (Course course : allCourses) {
							if (course.getInitials() == initials && course.getSection() == section) {
								coursesList.add(course);
							}
						}
						coursesString = coursedReader.readLine();
					}
					
					while (studyProgramsString != null) {
						String[] arguments = studyProgramsString.split("&");
						String name = arguments[0];
						int year = Integer.parseInt(arguments[1]);
						for (StudyProgram program : allStudyPrograms) {
							if (program.getName() == name && program.getyearProgram() == year) {
								studyProgramsList.add(program);
							}
						}
						studyProgramsString = studyProgramsReader.readLine();
					}
					
					// id&yearEntrance&yearGraduation&regularStudent&rut&name&lastnameFather&lastnameMother&address&gender&access&phone&birthdayString
					String[] studentArguments = studentString.split("&");
					int id = Integer.parseInt(studentArguments[0]);
					int yearEntrance = Integer.parseInt(studentArguments[1]);
					int yearGraduation = Integer.parseInt(studentArguments[2]);
					boolean regularStudent = Boolean.valueOf(studentArguments[3]);
					String rut = studentArguments[4];
					String name = studentArguments[5];
					String lastnameFather = studentArguments[6];
					String lastnameMother = studentArguments[7];
					String address = studentArguments[8];
					Gender gender = Gender.valueOf(studentArguments[9]);
					int phone = Integer.parseInt(studentArguments[10]);
					String birthdayString = studentArguments[11];
					student1 = new Student(id, yearEntrance, null, rut, name, lastnameFather, lastnameMother, address, gender, phone, birthdayString);
					students.add(student1);
					
					student1.setYearGraduation(yearGraduation);
					student1.setRegularStudent(regularStudent);
					
					Curriculum curriculum = new Curriculum(studyProgramsList);
					for (Coursed coursed1 : coursedList) {
						curriculum.addCoursedCourse(coursed1);
					}
					
					Semester semester = new Semester(currentSemester.getSemester(), currentSemester.getYear(), 100, null, null);
					for (Course course : coursesList) {
						semester.addCourse(course);
					}
					
					curriculum.setCurrentSemester(semester);
					
					student1.setCurriculum(curriculum);
					
				} catch (IOException iOException) {
					iOException.printStackTrace();
				} finally {
					
				}
			}
		} finally {
			
		}
		return students;
	}
}