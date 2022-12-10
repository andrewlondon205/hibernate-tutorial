package operations;

import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.swing.text.Style;

public class CreateCourseAndStudentsDemo {

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

            // create a course
            Course course = new Course("Pacman - How to Score One Million Points");
            // add some reviews
            // save the course
            System.out.println("\n Saving the course...");
            session.save(course);
            System.out.println("Saved the course: " + course);

            //create the students
            Student student1 = new Student("Ramai","Alexandria","ralejandria@softstrategy.it");
            Student student2 = new Student("Katia","Sabatini","ksabatini@almaviva.it");
            // add students to the course
            course.addStudent(student1);
            course.addStudent(student2);
            // save the students
            System.out.println("\n Saving Students ...");
            session.save(student1);
            session.save(student2);
            System.out.println("Saved students: " + course.getStudents());
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
