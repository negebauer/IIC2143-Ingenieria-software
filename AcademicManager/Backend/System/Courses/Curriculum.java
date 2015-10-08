package System.Courses;

import java.util.ArrayList;
import System.Users.Professor;
import Tools.Interfaces.ICourse;

public class Curriculum {

	private ArrayList<Coursed> curriculum;
	private StudyProgram studyProgram;
	
	public Curriculum(StudyProgram program) {
		
		this.curriculum = new ArrayList<Coursed>();
		this.studyProgram = program;
	}
	
	public void addCoursed(Course course, boolean approved, double gpa, String semester) {
		
		this.curriculum.add(new Coursed(course, approved, gpa, semester));
	}
	
	public ArrayList<Coursed> getCurriculum() {
		return this.curriculum;
	}
	public StudyProgram getStudyProgram() {
		return this.studyProgram;
	}
	
	public int getApprovedCredits() {
		
		int credits = 0;
		for (int i = 0; i < curriculum.size(); i++)
			if(curriculum.get(i).isApproved())
				credits += curriculum.get(i).getCredits();
		
		return credits;		
	}
		
	public class Coursed implements ICourse {

		private String name;
		private String initials;
		private int credits;
		
		private ArrayList<Professor> professors;
		private Classroom classroom;
		private Schedule schedule;	
		
		private boolean approved;
		private double gpaStudent;
		private String semester;
		
		public Coursed(Course course, boolean approved, double gpa, String semester) {
			
			this.name = course.getName();
			this.initials = course.getInitials();
			this.credits = course.getCredits();	
			
			this.professors = course.getProfessors();
			this.classroom = course.getClassroom();
			this.schedule = course.getSchedule();
			
			this.setApproved(approved);
			this.setSemester(semester);
			this.setGpaStudent(gpa);
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
		
		@Override
		public Classroom getClassroom() {
			return classroom;
		}
		@Override
		public void setClassroom(Classroom classroom) {
			this.classroom = classroom;
		}

		@Override
		public Schedule getSchedule() {
			return schedule;
		}
		@Override
		public void setSchedule(Schedule schedule) {
			this.schedule = schedule;
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

		
		public double getGpaStudent() {
			return gpaStudent;
		}

		public void setGpaStudent(double gpaStudent) {
			this.gpaStudent = gpaStudent;
		}	
	}
}
