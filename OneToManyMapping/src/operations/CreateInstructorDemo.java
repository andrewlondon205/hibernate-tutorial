package operations;

import entity.Course;
import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstructorDemo {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                                .configure("/configuration/hibernate.onetomany.xml")
                                .addAnnotatedClass(Instructor.class)
                                .addAnnotatedClass(InstructorDetail.class)
                                .addAnnotatedClass(Course.class)
                                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            //create the objects
            Instructor instructor = new Instructor("Charles","Xavier","cxavier@gmail.com");
            InstructorDetail instructorDetail = new InstructorDetail("www.youtube.com/xavier","Reading Minds");

            instructor.setInstructorDetail(instructorDetail);
            System.out.println("Saving instructor: " + instructor);
            session.beginTransaction();
            session.save(instructor);
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
