package com.luv2code;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {

    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();

        try {
            // begin transaction
            session.beginTransaction();
            //query students
            List<Student> theStudents = session.createQuery("from Student").getResultList();
            //display the students
            for (Student tempStudent : theStudents) {
                System.out.println(tempStudent);
            }
            //commit the transaction
        }
        finally {
            factory.close();
        }
    }
}
