package com.luv2code;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateStudentDemo {

    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();

        try {
            // use the session object to save Java object
            System.out.println("Creating a new student object");
            // create a student object
            Student tempStudent = new Student("Paul","Wall","paul@luv2code.com");
            // start a transaction
            session.beginTransaction();
            // save the student object
            System.out.println("Saving the student...");
            session.save(tempStudent);
            // commit the transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        }
        finally {
            factory.close();
        }
    }
}
