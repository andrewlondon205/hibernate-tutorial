package com.luv2code;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.sound.midi.SysexMessage;
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
            displayStudents(theStudents);

            //query students: lastName = 'Doe'
            theStudents = session.createQuery("from Student s where s.lastName='Vaccari'").getResultList();

            //display the students
            System.out.println("\nStudents who have last name of Vaccari\n");
            displayStudents(theStudents);

            theStudents = session.createQuery("from Student s where s.lastName='Vaccari' OR s.firstName='Ramai'").getResultList();
            System.out.println("\nStudents who have last name of Vaccari or first name of Ramai\n");
            displayStudents(theStudents);

            //query students where email LIKE '%gmail.com'
            theStudents = session.createQuery("from Student s where s.email LIKE '%gmail.com'").getResultList();
            System.out.println("\nStudents whose email address ends with @gmail.com");
            displayStudents(theStudents);

            //commit the transaction
            session.getTransaction().commit();
        }
        finally {
            factory.close();
        }
    }

    private static void displayStudents(List<Student> theStudents) {
        for (Student tempStudent : theStudents) {
            System.out.println(tempStudent);
        }
    }
}
