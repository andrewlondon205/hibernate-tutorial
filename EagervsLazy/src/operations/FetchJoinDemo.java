package operations;


import entity.Course;
import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class FetchJoinDemo {

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
            //option2: Hibernate Query with HQL
            //get the instructor from db
            int id = 1;
            Query<Instructor> query = session.createQuery("select i from Instructor i " +
                                                             "JOIN FETCH i.courses " +
                                                             "where i.id=:theInstructorId",
                                                              Instructor.class);

            //set parameter on query
            query.setParameter("theInstructorId",id);
            // execute query and get instructor
            Instructor instructor = query.getSingleResult();
            System.out.println("luv2code: Instructor: " + instructor);

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
