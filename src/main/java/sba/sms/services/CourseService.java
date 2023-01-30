package sba.sms.services;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import sba.sms.dao.CourseI;
import sba.sms.models.Course;
import sba.sms.utils.HibernateUtil;

public class CourseService implements CourseI {

    @Override
    public List<Course> getAllCourses() {

        Session session = null;
        List<Course> coursesFromResultSet = null;

        try {

            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            Query nativeQuery = session.createNativeQuery("SELECT * FROM course", Course.class);

            coursesFromResultSet = nativeQuery.list();

            transaction.commit();

        } catch (HibernateException ex) {

            System.err.println("Error listing all courses");
            ex.printStackTrace();

        } finally {

            if (session != null && session.isOpen()) {
                session.close();
            }

        }

        return coursesFromResultSet;
    }

    @Override
    public Course getCourseById(int courseId) {

        Session session = null;
        Course course = null;

        try {

            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();

            Query query = sessionFactory.openSession().createQuery("from Course where id=:courseID");

            query.setParameter("courseID", courseId);

            course = (Course) query.uniqueResult();

        } catch (HibernateException ex) {

            System.err.println("Error Finding Course By ID");
            ex.printStackTrace();

        } finally {

            if (session != null && session.isOpen()) {
                session.close();
            }

        }

        return course;
    }

    @Override
    public void createCourse(Course course) {

        Session session = null;

        try {

            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.getCurrentSession();

            session.beginTransaction();

            session.save(course);

            session.getTransaction().commit();

        } catch (HibernateException ex) {

            System.err.println("Error creating new course");
            ex.printStackTrace();

        } finally {

            if (session != null && session.isOpen()) {
                session.close();
            }
        }

    }
}
