package System.Courses;

import java.util.ArrayList;

import Tools.Interfaces.*;
import Tools.Others.CopyCreator;
import Tools.Enums.AcademicSemester;
import Tools.Enums.School;

/**
 * Class that represents the students' Curriculum.
 */
public class Curriculum {

	private ArrayList<Coursed> coursedCourses;
	private ArrayList<StudyProgram> studyPrograms;
	
	/**
	 * Creates a new instance of Curriculum.
	 * @param program An ArrayList containing the study programs that the student is coursing in the University.
	 */
	public Curriculum(ArrayList<StudyProgram> programs) {
		this.coursedCourses = new ArrayList<Coursed>();
		this.studyPrograms = programs;
	}
	
	/**
	 * Adds a Coursed course to the curriculum.
	 * @param course The course that was coursed.
	 * @param approved Whether the course was approved or not.
	 * @param gpa The grade obtained in the course.
	 * @param semester The semester in which the course was coursed.
	 */
	public void addCoursedCourse(Course course, boolean approved, double grade, AcademicSemester semester) {
		this.coursedCourses.add(new Coursed(course, approved, grade, semester));
	}
	
	/**
	 * @return The list of all the coursed Courses.
	 */
	public ArrayList<Coursed> getCoursedCourses() {
		return this.coursedCourses;
	}
	
	/**
	 * @return The list of all the study programs being coursed.
	 */
	public ArrayList<StudyProgram> getStudyPrograms() {
		return this.studyPrograms;
	}
	
	/**
	 * Adds a study program to this Curriculum.
	 * @param program The study program to be added.
	 */
	public void addStudyProgram(StudyProgram program) {
		this.studyPrograms.add(program);
	}
	
	/**
	 * Removes a study program from this Curriculum.
	 * @param program The study program to be removed.
	 */
	public void removeStudyProgram(StudyProgram program) {
		this.studyPrograms.remove(program);
	}
	
	/**
	 * Calculates the amount of approved credits in a curriculum.
	 * @return The amount of approved credits.
	 */
	public int getApprovedCredits() {
		
		int credits = 0;
		for (int i = 0; i < coursedCourses.size(); i++)
			if(coursedCourses.get(i).isApproved())
				credits += coursedCourses.get(i).getCredits();
		
		return credits;		
	}
		
	/**
	 * Class that represents a Course that was already Coursed.
	 */
	public class Coursed {

		private String name;
		private String initials;
		private int credits;
		private String details;
		private School school;
		private AcademicSemester semester;
		private ArrayList<ICourse> courses;
		private boolean approved;
		private double grade;
		
		/**
		 * Creates a new Coursed instance from a coursed Course.
		 * @param course The Course that was coursed.
		 * @param approved Whether the Course was approved or not.
		 * @param grade The grade obtained in the Course.
		 * @param semester The semester in which the Course was taken.
		 */
		public Coursed(Course course, boolean approved, double grade, AcademicSemester semester) {
			this.name = course.getName();
			this.initials = course.getInitials();
			this.credits = course.getCredits();
			this.details = course.getDetails();
			this.school = course.getSchool();
			this.semester = semester;
			this.courses = CopyCreator.copyICourses(course.getCourses());
			this.approved = approved;
			this.grade = grade;
		}

		public String getName() {
			return name;
		}
		
		public String getInitials() {
			return initials;
		}
		
		public int getCredits() {
			return credits;
		}
		
		public String getDetails() {
			return details;
		}
		
		public School getSchool() {
			return school;
		}
		
		public AcademicSemester getSemester() {
			return semester;
		}
		
		public ArrayList<ICourse> getCourses() {
			return courses;
		}
		
		public boolean isApproved() {
			return approved;
		}
		
		public double getGrade() {
			return grade;
		}
	}
}
