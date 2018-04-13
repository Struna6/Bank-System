import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Scanner;

public class BankingSystem
{
    public static void main(String[] args)
    {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Account.class)
                .buildSessionFactory();

        Scanner scanner = new Scanner(System.in);

        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        Customer c1 = (Customer) context.getBean("customer1");

        Session session = sessionFactory.openSession();

        session.beginTransaction();
        session.save(c1);
        session.getTransaction().commit();

        System.out.println("Hello, who are you?\nGive your name:");
        String name = scanner.next();
        System.out.println("Surname:");
        String surname = scanner.next();

        boolean newRegister = false;

        session.beginTransaction();
        List<Customer> customers = session.createQuery("FROM Customer WHERE name= '"+name+"' and surname=  '" + surname +"'").getResultList();
        session.getTransaction().commit();

        Customer customerTmp = null;
        if(customers.isEmpty())
        {
            System.out.println("Please register:");
            System.out.println("Your name:");
            String nameInsert = scanner.next();
            System.out.println("Your Surname:");
            String surnameInsert = scanner.next();
            System.out.println("Your Address:");
            String address = scanner.next();
            customerTmp = new Customer(nameInsert,surnameInsert,address);
            session.beginTransaction();
            session.save(customerTmp);
            session.getTransaction().commit();
            newRegister = true;
        }
        else
        {
            System.out.println(customers);
            newRegister = false;
        }

        System.out.println("Do you wanna new account?\n1.Yes\n0.No");
        int choice = scanner.nextInt();

        if(choice==1)
        {
            System.out.println("What type of account?\n1.SAVING,\n" +
                    "    2.CREDIT,\n" +
                    "    3.USD,\n" +
                    "    4.PLN,\n" +
                    "    5.GBP,\n" +
                    "    6.NORMAL ?");
            int choose = scanner.nextInt();
            Account_Type type;
            switch(choose)
            {
                case 0:
                    type = Account_Type.SAVING;
                    break;
                case 1:
                    type = Account_Type.CREDIT;
                    break;
                case 2:
                    type = Account_Type.USD;
                    break;
                case 3:
                    type = Account_Type.PLN;
                    break;
                case 4:
                    type = Account_Type.GBP;
                    break;
                case 5:
                    type = Account_Type.NORMAL;
                    break;

                default:
                    return;
            }


            if(newRegister)
            {
                Account account = new Account(customerTmp,type);
                customerTmp.addToList(account);
                session.beginTransaction();
                session.saveOrUpdate(customerTmp);
                session.getTransaction().commit();
            }
            else
            {
                Customer c = customers.get(0);
                Account account = new Account(c,type);
                c.addToList(account);
                session.beginTransaction();
                session.saveOrUpdate(c);
                session.getTransaction().commit();
            }
        }
        else return;

        session.close();
        sessionFactory.close();
    }
}
