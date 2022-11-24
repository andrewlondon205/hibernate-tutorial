package com.luv2code;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo {
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
            System.out.println("Creating three student objects");
            // create three student objects
            Student tempStudent1 = new Student("Ramai","Alexandria","vyasadev931@gmail.com");
            Student tempStudent2 = new Student("Erica","Vaccari","evaccari@gmail.com");
            Student tempStudent3 = new Student("Livia","Ramos","lramos@gmail.com");
            // start a transaction
            session.beginTransaction();
            // save the student object
            System.out.println("Saving the students...");
            session.save(tempStudent1);
            session.save(tempStudent2);
            session.save(tempStudent3);

            // commit the transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        }
        finally {
            factory.close();
        }
    }
}
