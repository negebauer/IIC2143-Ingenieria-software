package System.Courses;

import java.util.ArrayList;
import System.Users.Professor;
import Tools.Interfaces.*;

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
	public void addCoursed(Course course, boolean approved, double gpa, String semester) {
		this.coursedCourses.add(new Coursed(course, approved, gpa, semester));
	}
	
	/**
	 * @return The list of all the coursed Courses.
	 */
	public ArrayList<Coursed> getCurriculum() {
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
		
		private ArrayList<Professor> professors;
		private ArrayList<Classroom> classrooms;
		private ArrayList<Schedule> schedules;	
		
		private boolean approved;
		private double grade;
		private String semester;
		
		public Coursed(Course course, boolean approved, double grade, String semester) {
			
			this.name = course.getName();
			this.initials = course.getInitials();
			this.credits = course.getCredits();	
			
			ArrayList<Professor> professors = new ArrayList<Professor>();
			for(ICourse icourse : course.getCourses()) {
				if (icourse instanceof IProfessors) {
					for (Professor professor : ((IProfessors) icourse).getProfessors()) {
						professors.add(professor);
					}
				}
			}
			this.professors = professors;
			
			ArrayList<Classroom> classrooms = new ArrayList<Classroom>();
			for(ICourse icourse : course.getCourses()) {
				classrooms.add(icourse.getClassroom());
			}
			this.classrooms = classrooms;
			
			ArrayList<Schedule> schedules = new ArrayList<Schedule>();
			for(ICourse icourse : course.getCourses()) {
				schedules.add(icourse.getSchedule());
			}
			this.schedules = schedules;
			
			this.setApproved(approved);
			this.setSemester(semester);
			this.setGrade(grade);
		}

		public String getName() {
			return this.name;
		}
		
		public String getInitials() {
			return this.initials;
		}
		
		public int getCredits() {
			return this.credits;
		}
		
		public ArrayList<Professor> getProfessors() {
			return this.professors;
		}
		
//		@Override
//		public Classroom getClassroom() {
//			return classroom;
//		}
//		@Override
//		public void setClassroom(Classroom classroom) {
//			this.classroom = classroom;
//		}
//
//		@Override
//		public Schedule getSchedule() {
//			return schedule;
//		}
//		@Override
//		public void setSchedule(Schedule schedule) {
//			this.schedule = schedule;
//		}
		// TODO Update this
		public ArrayList<Classroom> getClassroom() {
			return classrooms;
		}

		// TODO Update this
		public ArrayList<Schedule> getSchedule() {
			return schedules;
		}

		public boolean isApproved() {
			return approved;
		}
		public void setApproved(boolean approved) {
			this.approved = approved;
		}

		public String getSemester() {
			return semester;
		}
		public void setSemester(String semester) {
			this.semester = semester;
		}

		
		public double getGrade() {
			return grade;
		}

		public void setGrade(double grade) {
			this.grade = grade;
		}	
	}
}
