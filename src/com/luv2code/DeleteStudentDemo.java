package com.luv2code;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteStudentDemo {

    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();

        try {

            int studentId = 6;
            // now get a new session and start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            // use the session object to save Java object
            System.out.println("\n Getting student with id: " + studentId);
            // create a student object
            Student myStudent = session.get(Student.class,studentId);

/*            //delete the student
            System.out.println("Deleting student: " + myStudent);
            session.delete(myStudent);*/

            //delete student id=2
            System.out.println("Deleting student id=5");
            session.createQuery("delete from Student where id=5").executeUpdate();

            //commit the transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        }
        finally {
            factory.close();
        }
    }
}
