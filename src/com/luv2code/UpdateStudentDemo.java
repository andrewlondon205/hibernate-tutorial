package com.luv2code;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateStudentDemo {

    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();

        try {

            int studentId = 1;
            // now get a new session and start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            // use the session object to save Java object
            System.out.println("\n Getting student with id: " + studentId);
            // create a student object
            Student myStudent = session.get(Student.class,studentId);
            System.out.println("Updating student...");
            myStudent.setFirstName("Scooby");

            //commit the transaction
            session.getTransaction().commit();

            //NEW CODE
            session = factory.getCurrentSession();
            session.beginTransaction();

            //update email for all students;
            System.out.println("Update email for all students");
            session.createQuery("update Student set email='foo@gmail.com'").executeUpdate();
            //commit the transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        }
        finally {
            factory.close();
        }
    }
}
