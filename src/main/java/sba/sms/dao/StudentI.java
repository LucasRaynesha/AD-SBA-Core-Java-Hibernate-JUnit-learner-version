package sba.sms.dao;

import java.util.List;
import sba.sms.models.Course;
import sba.sms.models.Student;

public interface StudentI {

    List<Student> getAllStudents();

    void createStudent(Student student);

    Student getStudentByEmail(String email);

    boolean validateStudent(String email, String password);

    void registerStudentToCourse(String email, int courseId);

    List<Course> getStudentCourses(String email);
}
