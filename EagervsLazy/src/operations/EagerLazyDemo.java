package operations;


import entity.Course;
import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EagerLazyDemo {

    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new Configuration()
                .configure("configuration/hibernate.lazyeager.xml")
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

            System.out.println("luv2code: Instructor: " + instructor);
            System.out.println("luv2code: Courses: " + instructor.getCourses());
            //get courses for instructor

            //commit transaction
            session.getTransaction().commit();
            // close the session
            session.close();
            System.out.println("\nluv2code: The session is now closed\n");
            // since the courses are lazy loaded, this should fail
            System.out.println("luv2code: Courses: " + instructor.getCourses());
            System.out.println("luv2code: Done!");

        }
        finally {
            session.close();
            factory.close();
        }

    }

}
