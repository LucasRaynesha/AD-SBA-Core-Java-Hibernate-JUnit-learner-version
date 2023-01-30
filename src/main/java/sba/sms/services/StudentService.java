package sba.sms.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import sba.sms.dao.StudentI;
import sba.sms.models.Course;
import sba.sms.models.Student;
import sba.sms.utils.HibernateUtil;

public class StudentService implements StudentI {

    @Override
    public boolean validateStudent(String email, String password) {

        Session session = null;
        Student student = null;

        try {

            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();

            Query query = sessionFactory.openSession().createQuery("from Student where email=:email and password=:password");

            query.setParameter("email", email);
            query.setParameter("password", password);

            student = (Student) query.uniqueResult();

        } catch (HibernateException ex) {

            System.err.println("Error validating student");
            ex.printStackTrace();

        } finally {

            if (session != null && session.isOpen()) {
                session.close();
            }

        }

        if (student != null) {

            // System.out.println("username and password are valid");
            return true;

        } else {

            // System.out.println("username and password are not valid");
            return false;

        }
    }

    @Override
    public Student getStudentByEmail(String email) {

        Student student = null;
        Session session = null;

        try {

            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();

            session = HibernateUtil.getSessionFactory().openSession();
            student = (Student) session.load(Student.class, email);
            Hibernate.initialize(student);

        } catch (HibernateException ex) {

            System.err.println("Error finding student by email");
            ex.printStackTrace();

        } finally {

            if (session != null && session.isOpen()) {
                session.close();
            }

        }

        return student;
    }

    @Override
    public void registerStudentToCourse(String userEmail, int courseID) {

        Session session = null;

        try {

            Student student = getStudentByEmail(userEmail);

            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();

            Transaction transaction = session.beginTransaction();

            String query = "INSERT INTO `student_courses` (`student_email`, `courses_id`) VALUES ('" + student.getEmail() + "', '" + courseID + "')";

            Query nativeQuery = session.createNativeQuery(query, Object.class);

            nativeQuery.executeUpdate();
            transaction.commit();

        } catch (HibernateException ex) {

            System.err.println("Error registering student for course");
            ex.printStackTrace();

        } finally {

            if (session != null && session.isOpen()) {
                session.close();
            }

        }

    }

    @Override
    public List<Course> getStudentCourses(String userEmail) {

        Session session = null;
        List<Course> coursesFromResultSet = null;

        try {

            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();

            Query nativeQuery = session.createNativeQuery("SELECT * FROM course where id in (select courses_id from student_courses where student_email=:studentEmail)", Course.class);
            nativeQuery.setParameter("studentEmail", userEmail);

            coursesFromResultSet = nativeQuery.list();

        } catch (HibernateException ex) {

            System.err.println("Error getting list of student courses");
            ex.printStackTrace();

        } finally {

            if (session != null && session.isOpen()) {
                session.close();
            }

        }

        return coursesFromResultSet;
    }

    @Override
    public void createStudent(Student student) {

        Session session = null;

        try {

            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();

            session.beginTransaction();

            session.save(student);

            session.getTransaction().commit();

        } catch (HibernateException ex) {

            System.err.println("Error creating student");
            ex.printStackTrace();

        } finally {

            if (session != null && session.isOpen()) {
                session.close();
            }

        }
    }

    @Override
    public List<Student> getAllStudents() {

        Session session = null;
        List<Student> studentList = null;

        try {

            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();

            Query query = sessionFactory.openSession().createQuery("from Student s");

            List<Student> listOfStudents = query.list();

            Iterator iterator = listOfStudents.iterator();

            studentList = new ArrayList();

            while (iterator.hasNext()) {

                Object iteratorObject = (Object) iterator.next();

                Student student = (Student) iteratorObject;

                // System.out.println("Student Email : " + student.getEmail());
                // System.out.println("Student name : " + student.getName());
                // System.out.println("Student Password : " + student.getPassword());
                // System.out.println("----------------------");
                studentList.add(student);

            }

        } catch (HibernateException ex) {

            System.err.println("Error listing all students");
            ex.printStackTrace();

        } finally {

            if (session != null && session.isOpen()) {
                session.close();
            }

        }
        return studentList;
    }
}
