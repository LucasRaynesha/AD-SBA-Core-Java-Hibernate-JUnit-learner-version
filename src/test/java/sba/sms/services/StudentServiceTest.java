package sba.sms.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import sba.sms.models.Student;
import sba.sms.utils.CommandLine;

@FieldDefaults(level = AccessLevel.PRIVATE)
class StudentServiceTest {

    static CourseService courseService;

    static StudentService studentService;

    @BeforeAll
    static void beforeAll() {
        courseService = new CourseService();
        studentService = new StudentService();

        CommandLine.addData();

    }

    @Test
    void verifyExpectedCourseCountNumbers() {

        Integer expectedCourseCount = 5;

        assertEquals(courseService.getAllCourses().size(), expectedCourseCount);

    }

    @Test
    void verifyExpectedStudentAccountNumbers() {

        List<Student> expected = new ArrayList<>(Arrays.asList(
                new Student("reema@gmail.com", "reema brown", "password"),
                new Student("annette@gmail.com", "annette allen", "password"),
                new Student("anthony@gmail.com", "anthony gallegos", "password"),
                new Student("ariadna@gmail.com", "ariadna ramirez", "password"),
                new Student("bolaji@gmail.com", "bolaji saibu", "password")
        ));

        System.out.println();
        System.out.println("Get all students");
        System.out.println("-------------------------");

        System.out.println();
        System.out.println(studentService.getAllStudents());
        System.out.println();

        System.out.println();
        System.out.println("Get all expected");
        System.out.println("-------------------------");

        System.out.println();
        System.out.println(expected);
        System.out.println();

        //System.out.println("expected and actual", expected.size())
        assertEquals(studentService.getAllStudents().size(), expected.size());

    }
}
