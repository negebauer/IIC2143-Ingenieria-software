package backend.courses;

import java.util.ArrayList;

/**
 * Class that represents the students' Curriculum.
 */
public class Curriculum {

	private Semester currentSemester;
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
	public void addCoursedCourse(Course course, boolean approved, double grade, int year) {
		this.coursedCourses.add(new Coursed(course, approved, grade, currentSemester.getSemester(), year));
	}
	
	public void addCoursedCourse(Coursed course) {
		coursedCourses.add(course);
	}
	
	/**
	 * Calculates the curricular advance of a specific study program.
	 * @param studyProgram The study program you want to see it's curricular advance.
	 * @return The approved and unapproved courses, separately.
	 */
	public ApprovedUnapprovedCoursesTuple getCurricularAdvance(StudyProgram studyProgram){
		ArrayList<Course> unapprovedCourses = new ArrayList<Course>();
		ArrayList<Coursed> approvedCourses = new ArrayList<Coursed>();
		
		for (Semester semester : studyProgram.getSemesters()){
			for (Course course : semester.getCourses()){
				unapprovedCourses.add(course);
			}
		}
		
		for (Course course : unapprovedCourses){
			for (Coursed coursed : coursedCourses){
				if (coursed.getInitials() == course.getInitials() && coursed.isApproved()){
						approvedCourses.add(coursed);
						unapprovedCourses.remove(course);
				}
			}
		}
			
		if (studyPrograms.contains(studyProgram)){
			return new ApprovedUnapprovedCoursesTuple(approvedCourses, unapprovedCourses);
		} else {
			return new ApprovedUnapprovedCoursesTuple(new ArrayList<Coursed>(), unapprovedCourses);
		}
	}
	
	public Semester getCurrentSemester() {
		return currentSemester;
	}

	public void setCurrentSemester(Semester currentSemester) {
		this.currentSemester = currentSemester;
	}
	
	/**
	 * Returns all the coursed semesters of this curriculum.
	 * @return A list of CoursedSemester objects.
	 */
	public ArrayList<CoursedSemester> getCoursedSemesters() {
		ArrayList<CoursedSemester> coursedSemesters = new ArrayList<CoursedSemester>();
		for (Coursed coursedCourse : coursedCourses) {
			Boolean shouldCreateNewCoursedSemester = false;
			for (CoursedSemester coursedSemester : coursedSemesters) {
				if (coursedSemester.getYear() == coursedCourse.getYear() && coursedCourse.getSemester() == coursedSemester.getSemester()) {
					coursedSemester.addCoursedCourse(coursedCourse);
					shouldCreateNewCoursedSemester = false;
					break;
				} else {
					shouldCreateNewCoursedSemester = true;
				}
			}
			if (shouldCreateNewCoursedSemester) {
				CoursedSemester coursedSemester = new CoursedSemester(coursedCourse.getSemester(), coursedCourse.getYear());
				coursedSemester.addCoursedCourse(coursedCourse);
			}
		}
		return coursedSemesters;
	}

	/**
	 * Class used as a data container for the curricular advance response.
	 */
	public class ApprovedUnapprovedCoursesTuple {
		public ArrayList<Coursed> approvedCourses;
		public ArrayList<Course> unapprovedCourses;
		
		/**
		 * Creates a new instance of ApprovedUnapprovedCoursesTuple.
		 * @param approvedCourses The ArrayList that contains all the Coursed courses that have been approved.
		 * @param unapprovedCourses The ArrayList that contains all the courses that haven't been approved yet.
		 */
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
