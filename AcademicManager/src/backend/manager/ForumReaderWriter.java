package backend.manager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;

import backend.courses.Course;
import backend.courses.Forum;
import backend.courses.ForumComment;
import backend.courses.ForumPost;
import backend.others.Utilities;
import backend.users.User;

public class ForumReaderWriter {

	/*
	 * |-> Forums
	 * 			|-> sigla-seccion					El foro de un curso con sigla: sigla y seccion: seccion
	 * 				|-> post1.txt
	 * 				|-> post2.txt
	 * File format
	 * Folder name: sigla-seccion
	 * File name: postn.txt						n es el numero del post
	 * content:
	 * Line 1:
	 * rutCreator&stringDateCreated&title		<= ForumPost
	 * Line 2 and beyond:
	 * rutCreator&stringDateCreated&comment		<= ForumComment
	 * rutCreator&stringDateCreated&comment		<= ForumComment
	 */
	
	public static void writeForums() {
		
	}

	public static void readForums(ArrayList<Course> allCourses, ArrayList<User> allUsers) {
		try {
			File forumsFolder = new File(FolderFileManager.rootForums);
			for (File forumFolder : forumsFolder.listFiles()) {
				if (forumFolder.getName().contains(".DS_")) {
					continue;
				}
				String initials = forumFolder.getName().split("-")[0];
				int section = Integer.valueOf(forumFolder.getName().split("-")[1].split(".txt")[0]);
				Course courseForForum = null;
				for (Course course : allCourses) {
					if (course.getInitials().equals(initials) && course.getSection() == section) {
						courseForForum = course;
						break;
					}
				}
				if (courseForForum == null) {
					continue;
				}
				Forum forum = new Forum();
				for (File forumFile : forumFolder.listFiles()) {
					if (forumFile.getName().contains(".DS_")) {
						continue;
					}
					ForumPost forumPost = null;
					courseForForum.setForum(forum);

					FileInputStream forumInputStream = new FileInputStream(forumFile);
					BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(forumInputStream));
					String forumLine = bufferedReader.readLine();
					Boolean createdPost = false;
					while (forumLine != null) {
						String[] rawData = forumLine.split("&");
						String rutCreator = rawData[0];
						Date createdAt = Utilities.getDateFromString(rawData[1]);
						String content = rawData[2];
						User creator = null;
						for (User user : allUsers) {
							if (user.getRut().equals(rutCreator)) {
								creator = user;
								break;
							}
						}
						if (creator == null) {
							bufferedReader.close();
							forumInputStream.close();
							return;
						}
						if (!createdPost) {
							ForumPost post = new ForumPost(creator, content, createdAt);
							forumPost = post;
							forum.addPost(forumPost);
							createdPost = true;
						} else if (createdPost) {
							ForumComment comment = new ForumComment(creator, content, createdAt);
							forumPost.addComment(comment);
						}
						forumLine = bufferedReader.readLine();
					}
					bufferedReader.close();
					forumInputStream.close();
				}
			}
		} catch (IOException ioException) {
			System.err.println("Unable to write to file");
			System.out.println(ioException);
		}
	}
}
