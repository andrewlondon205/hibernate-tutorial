package operations;

import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class GetInstructorDetailDemo {

    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new Configuration()
                                .configure("/configuration/hibernate.onetoonebi.xml")
                                .addAnnotatedClass(Instructor.class)
                                .addAnnotatedClass(InstructorDetail.class)
                                .buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();

        try {
            // start a transaction
            session.beginTransaction();
            // get the instructor detail object
            int id = 1232;
            // print the instructor detail
            InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class,id);
            // print the associated instructor
            System.out.println("tempInstructorDetail: " + tempInstructorDetail);
            System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());

            session.getTransaction().commit();
            System.out.println("Done!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            // handle connection leak issue
            session.close();
            factory.close();
        }
    }
}
