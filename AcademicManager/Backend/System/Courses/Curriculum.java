package System.Courses;

import java.util.ArrayList;

import Tools.Enums.AcademicSemester;

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
	
	public ApprovedUnapprovedCoursesTuple getCurricularAdvance(StudyProgram studyProgram){
		if (studyPrograms.contains(studyProgram)){
			ArrayList<Coursed> approvedCourses = new ArrayList<Coursed>();
			ArrayList<Course> unapprovedCourses = new ArrayList<Course>();
			
			for (Semester semester : studyProgram.getSemesters()){
				for (Course course : semester.getCourses()){
					unapprovedCourses.add(course);
					for (Coursed coursed : coursedCourses){
						if (coursed.getInitials() == course.getInitials()){
							if (coursed.isApproved()){
								approvedCourses.add(coursed);
								unapprovedCourses.remove(course);
							}
						}
					}
				}
			}
			return new ApprovedUnapprovedCoursesTuple(approvedCourses, unapprovedCourses);
		} else {
			return null;
		}
		
	}
	
	public class ApprovedUnapprovedCoursesTuple {
		public ArrayList<Coursed> approvedCourses;
		public ArrayList<Course> unapprovedCourses;
		
		public ApprovedUnapprovedCoursesTuple(ArrayList<Coursed> approvedCourses, ArrayList<Course> unapprovedCourses){
			this.approvedCourses = approvedCourses;
			this.unapprovedCourses = unapprovedCourses;
		}
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
		for (int i = 0; i < coursedCourses.size(); i++) {
			if(coursedCourses.get(i).isApproved()) {
				credits += coursedCourses.get(i).getCredits();
			}
		}
		return credits;		
	}
}
