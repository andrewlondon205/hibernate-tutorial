package operations;

import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeletePacmanCourseDemo {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("/configuration/hibernate.manytomany.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            // start a transaction
            session.beginTransaction();
            // get the pacman course from db
            int courseId =  10;
            Course course = session.get(Course.class,courseId);
            // delete the course
            System.out.println("Deleting course: " + course);
            session.delete(course);
            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        }
        finally {
            // add clean up code
            session.close();
            factory.close();
        }
    }
}
