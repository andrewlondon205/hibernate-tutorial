package dboperations;

import entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Operations {

    public static void saveEmployee(SessionFactory sessionFactory, Session session) {

        try{
            System.out.println("Creating new employees");

            Employee e1 = new Employee("Petronela","Costianu","almaviva");
            Employee e2 = new Employee("Ramai","Alexandria","softstrategy");
            Employee e3 = new Employee("Valerio","Mezzoprete","softstrategy");
            Employee e4 = new Employee("Ionut","Grecu","softstrategy");
            Employee e5 = new Employee("Nicusor","Grecu","almaviva");
            Employee e6 = new Employee("Arianna","Biricotti","Accenture");
            Employee e7 = new Employee("Justine","Biscocho","Accenture");

            session.beginTransaction();
            System.out.println("Saving the employe: " + e1.getFirstName() + " " + e1.getLastName());
            session.save(e1);
            System.out.println("Saving the employe: " + e2.getFirstName() + " " + e2.getLastName());
            session.save(e2);
            System.out.println("Saving the employe: " + e3.getFirstName() + " " + e3.getLastName());
            session.save(e3);
            System.out.println("Saving the employe: " + e4.getFirstName() + " " + e4.getLastName());
            session.save(e4);
            System.out.println("Saving the employe: " + e5.getFirstName() + " " + e5.getLastName());
            session.save(e5);
            System.out.println("Saving the employe: " + e6.getFirstName() + " " + e6.getLastName());
            session.save(e6);
            System.out.println("Saving the employe: " + e7.getFirstName() + " " + e7.getLastName());
            session.save(e7);

            session.getTransaction().commit();

            System.out.println("Done!");

        }
       finally {
            sessionFactory.close();
        }
    }

    public static void getEmployeeById(SessionFactory sessionFactory, Session session, Integer id) {

        try {
            session.beginTransaction();
            System.out.println("Retrieving employees");
            List<Employee> employeeList = session.createQuery("from Employee code where code.id="+id).getResultList();
            displayEmployees(employeeList);
            session.getTransaction().commit();
        }
        finally {
            sessionFactory.close();
        }
    }

    public static void getEmployeeByCompanyName(SessionFactory sessionFactory, Session session) {
    try {
        session.beginTransaction();
        System.out.println("Retrieving employees by company name");
        List<Employee> employees = session.createQuery("from Employee n where n.company='almaviva'").getResultList();
        displayEmployees(employees);
        session.getTransaction().commit();
    }
    finally {
        sessionFactory.close();
        }
    }

    public static void deleteEmployeeById(SessionFactory sessionFactory, Session session, Integer id) {
        try {
            session.beginTransaction();
            System.out.println("Deleting employee with id: "+id);
            session.createQuery("delete from Employee where id="+id).executeUpdate();
            session.getTransaction().commit();
            System.out.println("Well Done!");
        }
        finally {
            sessionFactory.close();
        }
    }

    private static void displayEmployees(List<Employee> employeeList) {
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
    }

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("/configuration/hibernate.config.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();

       // saveEmployee(factory,session);
       // getEmployeeById(factory,session,1);
       // getEmployeeByCompanyName(factory,session);
        // deleteEmployeeById(factory,session,7);

    }


}
