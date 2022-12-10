package operations;

import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AddCoursesForKatiaDemo {

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
            // get the student Katia from database
            int studentId = 2;
            Student student = session.get(Student.class,studentId);

            System.out.println("\nLoaded student: " + student);
            System.out.println("Course: " + student.getCourses());

            // create more courses
            Course course1 = new Course("React - Complete Bootcamp");
            Course course2 = new Course("Spring and Hibernate");
            // add student to courses
            course1.addStudent(student);
            course2.addStudent(student);
            // save the courses
            System.out.println("\n Saving the courses...");
            session.save(course1);
            session.save(course2);
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
