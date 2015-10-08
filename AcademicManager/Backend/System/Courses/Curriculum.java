package System.Courses;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class Curriculum {

	@SuppressWarnings("rawtypes")
	private Dictionary<?, ?> approved = new Hashtable();
	private ArrayList<Course> curriculum;
	private StudyProgram studyProgram;
	
	public Curriculum(StudyProgram program) {
		
		this.curriculum = new ArrayList<Course>();
		this.studyProgram = program;
	}
	
	public ArrayList<Course> getCurriculum() {
		return this.curriculum;
	}
	public StudyProgram getStudyProgram() {
		return this.studyProgram;
	}
	
	public int getApprovedCredits() {
		
		int credits = 0;
		for (int i = 0; i < curriculum.size(); i++)
			if((boolean) approved.get(curriculum.get(i).getInitials()))
				credits += curriculum.get(i).getCredits();
		
		return credits;		
	}
	
	public Dictionary<?, ?> getApprovedCourses() {
		return this.approved;
	}
}
