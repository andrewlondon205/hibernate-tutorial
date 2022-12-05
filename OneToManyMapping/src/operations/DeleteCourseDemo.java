package operations;

import entity.Course;
import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCourseDemo {

    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new Configuration()
                            .configure("configuration/hibernate.onetomany.xml")
                            .addAnnotatedClass(Instructor.class)
                            .addAnnotatedClass(InstructorDetail.class)
                            .addAnnotatedClass(Course.class)
                            .buildSessionFactory();

        //create a session
        Session session = factory.getCurrentSession();

        try {
            //start a transaction
            session.beginTransaction();
            //get a course
            int id = 10;
            Course course = session.get(Course.class,id);

            //delete course
            System.out.println("Deleting course: " + course);
            session.delete(course);
            //commit the transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        }
        finally {
            session.close();
            factory.close();
        }
    }
}
