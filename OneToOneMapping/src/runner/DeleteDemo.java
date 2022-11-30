package runner;

import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteDemo {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("/configuration/hibernate.onetoone.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();

        try {
            //start a transaction
            session.beginTransaction();
            // get instructor by id
            int id = 1;
            Instructor tempInstructor = session.get(Instructor.class,id);
            System.out.println("Found instructor: " + tempInstructor);
            // delete the instructor
            if (tempInstructor != null) {
                System.out.println("Deleting: " + tempInstructor);
                //Note: will ALSO delete associated "details"
                // because of CascadeType.ALL

                session.delete(tempInstructor);
            }
            // commit the transaction
            session.getTransaction().commit();
        }
        finally {
            factory.close();
        }
    }
}
