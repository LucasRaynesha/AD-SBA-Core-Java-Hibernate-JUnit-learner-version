package sba.sms.dao;

import java.util.List;
import sba.sms.models.Course;

public interface CourseI {

    void createCourse(Course course);

    Course getCourseById(int courseId);

    List<Course> getAllCourses();

}
