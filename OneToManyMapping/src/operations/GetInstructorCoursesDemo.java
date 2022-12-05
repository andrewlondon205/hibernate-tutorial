package operations;

import entity.Course;
import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorCoursesDemo {

    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new Configuration()
                .configure("configuration/hibernate.onetomany.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();

        try {
            // start a transaction
            session.beginTransaction();

            //get the instructor id
            int id = 1;
            Instructor instructor = session.get(Instructor.class,id);

            System.out.println("Instructor: " + instructor);
            //get courses for instructor
            System.out.println("Courses: " + instructor.getCourses());

            //commit transaction
            session.getTransaction().commit();

        }
        finally {
            session.close();
            factory.close();
        }

    }

}
