package backend.courses;

import java.util.ArrayList;
import java.util.Date;

import backend.users.User;

public class ForumPost {

	User creator;
	Date createdAt;
	String title;
	ArrayList<ForumComment> comments;
	
	public ForumPost(User creator, String title) {
		this.creator = creator;
		createdAt = new Date();
		this.title = title;
		comments = new ArrayList<ForumComment>();
	}
	
	public ForumPost(User creator, String title, Date createdAt) {
		this.creator = creator;
		this.createdAt = createdAt;
		this.title = title;
		comments = new ArrayList<ForumComment>();
	}
	
	public void addComment(ForumComment comment) {
		comments.add(comment);
	}
}
