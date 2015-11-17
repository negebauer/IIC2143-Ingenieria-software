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
import backend.courses.CoursedSemester;
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
import backend.interfaces.IAssistants;
import backend.interfaces.ICourse;
import backend.interfaces.IProfessors;
import backend.others.Utilities;
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
				|-> coursed.txt			Line format: name&initials&section&credits&school&semester&year&approved&grade
				|-> coursedCourses.txt	Line format: 3 cases
											- Assistantship:	initials&section&year&semester&ASSISTANTSHIP&name:lastNameFather:lastNameMother;name:lastNameFather:lastNameMother&classroomInitials&dia1:modulo1;dia2:modulo2
											- Laboratory:		initials&section&year&semester&LABORATORY&name:lastNameFather:lastNameMother;name:lastNameFather:lastNameMother&classroomInitials&dia1:modulo1;dia2:modulo2
											- Lecture:			initials&section&year&semester&LECTURE&name:lastNameFather:lastNameMother;name:lastNameFather:lastNameMother&classroomInitials&dia1:modulo1;dia2:modulo2
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
			for (Student student : students) {
				String studentFolder = FolderFileManager.rootStudent + "/" + student.getId() + "_" + student.getName() + "_" + student.getLastnameFather();
				File studentRealFolder = new File(studentFolder);
				if (!studentRealFolder.exists()) {
					studentRealFolder.mkdir();
				}
				
				FileOutputStream coursedFile 		= new FileOutputStream(studentFolder + FolderFileManager.studentCoursed);
				FileOutputStream coursedCourses 	= new FileOutputStream(studentFolder + FolderFileManager.studentCoursedCourses);
				FileOutputStream courses 			= new FileOutputStream(studentFolder + FolderFileManager.studentCourses);
				FileOutputStream info 				= new FileOutputStream(studentFolder + FolderFileManager.studentInfo);
				FileOutputStream studyPrograms 		= new FileOutputStream(studentFolder + FolderFileManager.studentStudyPrograms);
				
				PrintStream coursedStream 			= new PrintStream(coursedFile);
				PrintStream coursedCoursesStream 	= new PrintStream(coursedCourses);
				PrintStream coursesStream 			= new PrintStream(courses);
				PrintStream infoStream 				= new PrintStream(info);
				PrintStream studyProgramsStream 	= new PrintStream(studyPrograms);
				
				// name&initials&section&credits&school&semester&year&approved&grade
				for (Coursed coursed : student.getCurriculum().getCoursedCourses()) {
					coursedStream.print(coursed.getName());
					coursedStream.print("&");
					coursedStream.print(coursed.getInitials());
					coursedStream.print("&");
					coursedStream.print(coursed.getSection());
					coursedStream.print("&");
					coursedStream.print(coursed.getCredits());
					coursedStream.print("&");
					coursedStream.print(coursed.getSchool());
					coursedStream.print("&");
					coursedStream.print(coursed.getSemester());
					coursedStream.print("&");
					coursedStream.print(coursed.getYear());
					coursedStream.print("&");
					coursedStream.print(coursed.isApproved());
					coursedStream.print("&");
					coursedStream.print(coursed.getGrade());
					coursedStream.println("");
				}
				
				// initials&section&year&semester&ASSISTANTSHIP&name:lastNameFather:lastNameMother;name:lastNameFather:lastNameMother&initials;school;campus;size&dia1:modulo1;dia2:modulo2
				for (Coursed coursed : student.getCurriculum().getCoursedCourses()) {
					for (ICourse icourse : coursed.getCourses()) {
						coursedCoursesStream.print(coursed.getInitials());
						coursedCoursesStream.print("&");
						coursedCoursesStream.print(coursed.getSection());
						coursedCoursesStream.print("&");
						coursedCoursesStream.print(coursed.getYear());
						coursedCoursesStream.print("&");
						coursedCoursesStream.print(coursed.getSemester().toString());
						coursedCoursesStream.print("&");
						if (icourse instanceof Assistantship) {
							coursedCoursesStream.print("ASSISTANTSHIP");
						} else if (icourse instanceof Lecture) {
							coursedCoursesStream.print("LECTURE");
						} else if (icourse instanceof Laboratory) {
							coursedCoursesStream.print("LABORATORY");
						}
						coursedCoursesStream.print("&");
 						Boolean moreThanOneProfessorAsisstants = false;
 						if (icourse instanceof IAssistants) {
 							for (Assistant assistant : ((IAssistants) icourse).getAssistants()) {
 								if (moreThanOneProfessorAsisstants) {
 									coursedCoursesStream.print(";");
 								}
 								coursedCoursesStream.print(assistant.getRut());
 								moreThanOneProfessorAsisstants = true;
 							}
 						} else if (icourse instanceof IProfessors) {
 							for (Professor professor : ((IProfessors) icourse).getProfessors()) {
 								if (moreThanOneProfessorAsisstants) {
 									coursedCoursesStream.print(";");
 								}
 								coursedCoursesStream.print(professor.getRut());
 								moreThanOneProfessorAsisstants = true;
 							}
 						}
 						coursedCoursesStream.print("&");
						coursedCoursesStream.print(icourse.getClassroom().getInitials());
						coursedCoursesStream.print("&");
						Boolean moreThanOneDayModuleTuple = false;
						for (DayModuleTuple dayModuleTuple : icourse.getSchedule().getModules()) {
							if (moreThanOneDayModuleTuple) {
									coursedCoursesStream.print(";");
								}
							coursedCoursesStream.print(dayModuleTuple.day.toString());
							coursedCoursesStream.print(":");
							coursedCoursesStream.print(dayModuleTuple.module.toString());
							moreThanOneDayModuleTuple = true;
						}
						coursedCoursesStream.println("");
					}
				}
				
				if (student.getCurriculum().getCurrentSemester() != null) {
					// initials&section
					for (Course course : student.getCurriculum().getCurrentSemester().getCourses()) {
						coursesStream.print(course.getInitials());
						coursesStream.print("&");
						coursesStream.print(course.getSection());
						coursesStream.println("");
					}
				}

				// id&yearEntrance&yearGraduation&regularStudent&rut&name&lastnameFather&lastnameMother&address&gender&access&phone&birthdayString
				infoStream.print(student.getId());
				infoStream.print("&");
				infoStream.print(student.getYearEntrance());
				infoStream.print("&");
				infoStream.print(student.getYearGraduation());
				infoStream.print("&");
				infoStream.print(student.isRegularStudent());
				infoStream.print("&");
				infoStream.print(student.getRut());
				infoStream.print("&");
				infoStream.print(student.getName());
				infoStream.print("&");
				infoStream.print(student.getLastnameFather());
				infoStream.print("&");
				infoStream.print(student.getLastnameMother());
				infoStream.print("&");
				infoStream.print(student.getAddress());
				infoStream.print("&");
				infoStream.print(student.getGender());
				infoStream.print("&");
				infoStream.print(student.getAccess().toString());
				infoStream.print("&");
				infoStream.print(student.getPhone());
				infoStream.print("&");
				infoStream.print(Utilities.getStringFromDate(student.getBirthday()));
				infoStream.println("");

				// name&year
				for (StudyProgram studyProgram : student.getCurriculum().getStudyPrograms()) {
					studyProgramsStream.print(studyProgram.getName());
					studyProgramsStream.print("&");
					studyProgramsStream.print(studyProgram.getyearProgram());
					studyProgramsStream.println("");
				}
				
				coursedStream.close();
				coursedCoursesStream.close();
				coursesStream.close();
				infoStream.close();
				studyProgramsStream.close();
			}
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
	public static ArrayList<Student> readStudents(ArrayList<Course> allCourses, ArrayList<StudyProgram> allStudyPrograms, ArrayList<Classroom> allClassrooms, Semester currentSemester, ArrayList<Professor> allProfessors, ArrayList<Assistant> allAssistants) {
		// TODO Dude, this function is way to complex and big.
		ArrayList<Student> students = new ArrayList<Student>();
		try {
			File studentRootFolder = new File(FolderFileManager.rootStudent);
			for (File studentFolderFile : studentRootFolder.listFiles()) {
				if (studentFolderFile == null || studentFolderFile.getName().equals(".DS_Store")) {
					continue;
				}
				try {
					FileInputStream coursed 					= new FileInputStream(studentFolderFile.getPath() + FolderFileManager.studentCoursed);
					FileInputStream coursedCourses 				= new FileInputStream(studentFolderFile.getPath() + FolderFileManager.studentCoursedCourses);
					FileInputStream courses 					= new FileInputStream(studentFolderFile.getPath() + FolderFileManager.studentCourses);
					FileInputStream student 					= new FileInputStream(studentFolderFile.getPath() + FolderFileManager.studentInfo);
					FileInputStream studyPrograms 				= new FileInputStream(studentFolderFile.getPath() + FolderFileManager.studentStudyPrograms);

					BufferedReader coursedReader 				= new BufferedReader(new InputStreamReader(coursed));
					BufferedReader coursedCoursesReader			= new BufferedReader(new InputStreamReader(coursedCourses));
					BufferedReader coursesReader 				= new BufferedReader(new InputStreamReader(courses));
					BufferedReader studentReader 				= new BufferedReader(new InputStreamReader(student));
					BufferedReader studyProgramsReader 			= new BufferedReader(new InputStreamReader(studyPrograms));

					ArrayList<Coursed> coursedList 				= new ArrayList<Coursed>();
					ArrayList<Course> coursesList 				= new ArrayList<Course>();
					Student student1							= new Student(0, 0, null, null, null, null, null, null, null, null, null);
					ArrayList<StudyProgram> studyProgramsList 	= new ArrayList<StudyProgram>();
					ArrayList<Coursed> currentSemesterCoursed	= new ArrayList<Coursed>();
					
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
						School school = School.valueOf(arguments[4]);
						AcademicSemester semester = AcademicSemester.valueOf(arguments[5]);
						int year = Integer.parseInt(arguments[6]);
						boolean approved = Boolean.valueOf(arguments[7]);
						double grade = Double.parseDouble(arguments[8]);
						Coursed coursedLoaded = new Coursed(name, initials, section, credits, "", school, semester, year, approved, grade);
						coursedList.add(coursedLoaded);
						coursedString = coursedReader.readLine();
						if (year == Manager.getYear() && semester == Manager.INSTANCE.currentSemester.getSemester()) {
							currentSemesterCoursed.add(coursedLoaded);
						}
					}

					while (coursedCoursesString != null) {
						String[] arguments = coursedCoursesString.split("&");
						String initials = arguments[0];
						int section = Integer.parseInt(arguments[1]);
						int year = Integer.parseInt(arguments[2]);
						AcademicSemester semester = AcademicSemester.valueOf(arguments[3]);
						String classroomInitials = arguments[6];
						Classroom classroom = new Classroom(null, null, null, -1);
						for (Classroom possibleClassroom : allClassrooms) {
							if (possibleClassroom.getInitials().equals(classroomInitials)) {
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
								String rut = assistantString;
								for (Assistant assistant : allAssistants) {
									if (assistant.getRut().equals(rut)) {
										assistants.add(assistant);
									}
								}
							}
							Assistantship assistantship = new Assistantship(assistants, classroom, schedule);
							for (Coursed possibleCoursed : coursedList) {
								if (possibleCoursed.getInitials().equals(initials) && possibleCoursed.getSection() == section && possibleCoursed.getYear() == year && possibleCoursed.getSemester() == semester) {
									possibleCoursed.addICourse(assistantship);
								}
							}
						case "LABORATORY":
							ArrayList<Professor> professors = new ArrayList<Professor>();
							for (String professorString : arguments[5].split(";")) {
								String rut = professorString;
								for (Professor professor : allProfessors) {
									if (professor.getRut().equals(rut)) {
										professors.add(professor);
									}
								}
							}
							Laboratory laboratory = new Laboratory(professors, classroom, schedule);
							for (Coursed possibleCoursed : coursedList) {
								if (possibleCoursed.getInitials().equals(initials) && possibleCoursed.getSection() == section && possibleCoursed.getYear() == year && possibleCoursed.getSemester() == semester) {
									possibleCoursed.addICourse(laboratory);
								}
							}
						case "LECTURE":
							ArrayList<Professor> professors2 = new ArrayList<Professor>();
							for (String professorString : arguments[5].split(";")) {
								String rut = professorString;
								for (Professor professor : allProfessors) {
									if (professor.getRut().equals(rut)) {
										professors2.add(professor);
									}
								}
							}
							Lecture lecture = new Lecture(professors2, classroom, schedule);
							for (Coursed possibleCoursed : coursedList) {
								if (possibleCoursed.getInitials().equals(initials) && possibleCoursed.getSection() == section && possibleCoursed.getYear() == year && possibleCoursed.getSemester() == semester) {
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
							if (course.getInitials().equals(initials) && course.getSection() == section) {
								coursesList.add(course);
							}
						}
						coursesString = coursesReader.readLine();
					}
					
					while (studyProgramsString != null) {
						String[] arguments = studyProgramsString.split("&");
						String name = arguments[0];
						int year = Integer.parseInt(arguments[1]);
						for (StudyProgram program : allStudyPrograms) {
							if (program.getName().equals(name) && program.getyearProgram() == year) {
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
//					Access access = 
					String phone = studentArguments[11];
					String birthdayString = studentArguments[12];
					student1 = new Student(id, yearEntrance, null, rut, name, lastnameFather, lastnameMother, address, gender, phone, birthdayString);
					students.add(student1);
					
					student1.setYearGraduation(yearGraduation);
					student1.setRegularStudent(regularStudent);
					
					Curriculum curriculum = new Curriculum(studyProgramsList);
					for (Coursed coursed1 : coursedList) {
						curriculum.addCoursedCourse(coursed1);
					}
					
					Semester semester = new Semester(currentSemester.getSemester(), currentSemester.getYear(), 100, coursedList, null);
					for (Course course : coursesList) {
						semester.addCourse(course);
					}
					
					curriculum.setCurrentSemester(semester);
					
					student1.setCurriculum(curriculum);
					
					if (student1.getCurriculum().getCurrentSemester().getCourses().size() == 0) {
						student1.getCurriculum().setCurrentSemester(null);
					}
					
					for (Course course : allCourses) {
						for (Coursed coursed2 : coursedList) {
							if (course.getInitials().equals(coursed2.getInitials())) {
								coursed2.setDetails(course.getDetails());
							}
						}
					}
					
					if (currentSemesterCoursed.size() > 0) {
						student1.getCurriculum().setCurrentCoursedSemester(new CoursedSemester(currentSemester.getSemester(), currentSemester.getYear(), student1.getCurriculum().getMaxSemesterCredits()));
						student1.getCurriculum().getCurrentCoursedSemester().setCoursedCourses(currentSemesterCoursed);
					}
					
				} catch (IOException iOException) {
					iOException.printStackTrace();
				}
			}
		} finally {
			
		}
		return students;
	}
}