package runner;

import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
public class CreateDemo {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("/configuration/hibernate.onetoone.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();

        try {
            //create the objects
          /*  Instructor tempInstructor = new Instructor("Chad","Darby","darby@luv2code.com");

            InstructorDetail tempInstructorDetail =
                    new InstructorDetail("http://www.luv2code.com/youtube",
                                         "Luv 2 code!!!");*/

            Instructor tempInstructor = new Instructor("Ramai","Alexandria","ralejandria@softstrategy.com");

            InstructorDetail tempInstructorDetail =
                    new InstructorDetail("www.youtube.com/alexandria",
                            "Programming");
            // associate the objects
            tempInstructor.setInstructorDetail(tempInstructorDetail);
            //start a transaction
            session.beginTransaction();
            //save the instructor
            //NOTE: this will ALSO save the details object
            //because of CascadeType.ALL
            System.out.println("Saving Instructor: " + tempInstructor);
            session.save(tempInstructor);
            //commit transaction
            session.getTransaction().commit();
            System.out.println("Well Done");
        }
        finally {
            factory.close();
        }
    }
}
