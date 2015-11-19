package backend.manager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

import backend.courses.Course;
import backend.courses.Coursed;
import backend.courses.Semester;
import backend.courses.StudyProgram;
import backend.enums.School;

public class StudyProgramsReaderWriter {

	/*
	 * File format studyProgram.txt => name&school&year program&max credits per
	 * semester&max failed credits&nº of semesters semesterX.txt => course initials
	 */

	/**
	 * Writes all the studyPrograms to the .txt file.
	 * 
	 * @param allStudyPrograms
	 *            All the studyPrograms to save.
	 */
	public static void writeStudyPrograms(ArrayList<StudyProgram> allStudyPrograms) {
		try {
			File studyProgramsFolder = new File(FolderFileManager.adminStudyPrograms);
			cleanUp(studyProgramsFolder);

			studyProgramsFolder.mkdir();

			for (StudyProgram studyProgram : allStudyPrograms) {
				File schoolFolder = new File(FolderFileManager.adminStudyPrograms + "/" + studyProgram.getSchool());
				if (!schoolFolder.exists()) {
					schoolFolder.mkdir();
				}
				File studyProgramFolder = new File(schoolFolder.getCanonicalPath() + "/" + studyProgram.getName());
				if (!studyProgramFolder.exists()) {
					studyProgramFolder.mkdir();
				}

				FileOutputStream fileOutputStream = new FileOutputStream(
						studyProgramFolder.getCanonicalPath() + FolderFileManager.adminStudyProgramInfo);
				PrintStream printStream = new PrintStream(fileOutputStream);

				printStream.print(studyProgram.getName());
				printStream.print("&");
				printStream.print(studyProgram.getSchool().toString());
				printStream.print("&");
				printStream.print(studyProgram.getyearProgram());
				printStream.print("&");
				printStream.print(studyProgram.getMaxCreditsPerSemester());
				printStream.print("&");
				printStream.print(studyProgram.getMaxFailedCredits());
				printStream.print("&");
				printStream.print(studyProgram.getSemesters().size());
				printStream.println("");

				printStream.close();

				int i = 1;
				for (Semester semester : studyProgram.getSemesters()) {
					FileOutputStream semesterFile = new FileOutputStream(
							studyProgramFolder.getCanonicalPath() + "/semester" + (i < 10 ? "0" + i : i) + ".txt");
					PrintStream semesterPrintStream = new PrintStream(semesterFile);

					for (Course course : semester.getCourses()) {
						semesterPrintStream.println(course.getInitials());
					}
					i++;

					semesterPrintStream.close();
				}
			}
		} catch (IOException ioException) {
			System.err.println("Unable to write to file");
			System.out.println(ioException);
		}
	}

	/**
	 * Reads all the study programs from the .txt file and returns them.
	 * 
	 * @param allCourses
	 *            All the courses of the system.
	 */
	public static ArrayList<StudyProgram> readStudyPrograms(ArrayList<Course> allCourses) {
		ArrayList<StudyProgram> studyPrograms = new ArrayList<StudyProgram>();
		try {
			File studyProgramsFolder = new File(FolderFileManager.adminStudyPrograms);
			for (File schoolFolder : studyProgramsFolder.listFiles()) {
				if (schoolFolder.getName().equals(".DS_Store")) {
					continue;
				}
				for (File studyProgramFolder : schoolFolder.listFiles()) {
					if (studyProgramFolder.getName().equals(".DS_Store")) {
						continue;
					}

					ArrayList<Semester> studyProgramSemesters = new ArrayList<Semester>();
					StudyProgram studyProgram = new StudyProgram(null, 0, null, null, 0, 0);

					FileInputStream studyProgramInfoFileInputStream = new FileInputStream(
							studyProgramFolder.getPath() + FolderFileManager.adminStudyProgramInfo);
					BufferedReader studyProgramInfoBufferedReader = new BufferedReader(
							new InputStreamReader(studyProgramInfoFileInputStream));
					String[] arguments = studyProgramInfoBufferedReader.readLine().split("&");

					String name = arguments[0];
					School school = School.valueOf(arguments[1]);
					int yearProgram = Integer.valueOf(arguments[2]);
					int maxCreditsPerSemester = Integer.valueOf(arguments[3]);
					int maxFailedCredits = Integer.valueOf(arguments[4]);
					int numberOfSemesters = Integer.valueOf(arguments[5]);

					int i = 0;
					while (i < numberOfSemesters) {
						Semester emptySemester = new Semester(null, i, i, null, null);
						studyProgramSemesters.add(emptySemester);
						i++;
					}

					studyProgram = new StudyProgram(name, yearProgram, null, school, maxCreditsPerSemester,
							maxFailedCredits);
					studyPrograms.add(studyProgram);
					studyProgram.setSemesters(studyProgramSemesters);
					studyProgramInfoBufferedReader.close();

					for (File studyProgramFile : studyProgramFolder.listFiles()) {
						if (studyProgramFile.getName().equals(FolderFileManager.adminStudyProgramInfo.split("/")[1])) {
							continue;
						} else {
							ArrayList<Course> courses = new ArrayList<Course>();
							String[] firstSplit = studyProgramFile.getName().split("semester");
							String[] secondSplit = firstSplit[1].split(".txt");
							int semesterNumber = Integer.valueOf(secondSplit[0]);

							FileInputStream semesterInputStream = new FileInputStream(
									studyProgramFile.getCanonicalPath());
							BufferedReader bufferedReader = new BufferedReader(
									new InputStreamReader(semesterInputStream));
							String courseInitials = bufferedReader.readLine();

							while (courseInitials != null) {
								for (Course course : allCourses) {
									if (course.getInitials().equals(courseInitials)) {
										courses.add(course);
									}
								}
								courseInitials = bufferedReader.readLine();
							}
							ArrayList<Coursed> coursesBeforeThisSemester = new ArrayList<Coursed>();
							for (Semester previousSemester : studyProgramSemesters) {
								for (Course previousCourse : previousSemester.getCourses()) {
									coursesBeforeThisSemester.add(new Coursed(previousCourse, true, 7, null, 0));
								}
							}
							Semester semester = new Semester(null, 0, 0, coursesBeforeThisSemester, courses);
							studyProgramSemesters.set(semesterNumber - 1, semester);

							bufferedReader.close();
						}
					}
				}
			}
		} catch (IOException ioException) {
			System.err.println("Unable to read from file");
			System.out.println(ioException);
		}
		return studyPrograms;
	}

	public static void cleanUp(File folderOrFile) {
		if (folderOrFile.isDirectory()) {
			for (File folderContent : folderOrFile.listFiles()) {
				cleanUp(folderContent);
			}
		} else {
			folderOrFile.delete();
		}
	}
}
