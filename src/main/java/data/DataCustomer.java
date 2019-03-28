package data;

import config.Code;
import config.HibernateConfig;
import config.Values;
import entities.Customer;
import org.hibernate.*;

import java.util.ArrayList;
import java.util.List;

public class DataCustomer {

    // get session factory connection (replacement DBConnection)
    private static SessionFactory factory = new HibernateConfig().getSessionFactory();

    // get all customer as list
    public List<Customer> listCustomer(){
        // open new session
        Session sesn = factory.openSession();

        // create temp variable for list user as array list
        List<Customer> users = new ArrayList<Customer>();
        try {
            // try to execute query and insert result to users list
            Query query = sesn.createQuery("FROM Customer");
            users = query.list();
        } catch (HibernateException e){
            e.printStackTrace();
        } finally {
            sesn.close();
        }
        return users;
    }

    // get suctomer name and cif
    public Customer getNameCif(String cif){
        // open new session
        Session sesn = factory.openSession();

        // create temp variable for list user as array list
        Customer customer = new Customer();
        try {
            // try to execute query and insert result to users list
            Query query = sesn.createQuery("FROM Customer WHERE cif = :cif");
            query.setParameter("cif", cif);
            customer = (Customer) query.uniqueResult();
        } catch (HibernateException e){
            e.printStackTrace();
        }
        return customer;
    }

    // add new customer with return value boolean
    // this method has Object parameter from Customer
    public boolean addCustomer(Customer customer){
        Session sesn = factory.openSession();
        boolean isAdded = false;
        try {
            Transaction trx = sesn.beginTransaction();
            sesn.save(customer);
            trx.commit();
            isAdded = true;
        } catch(Exception sqlException) {
            if(sesn.getTransaction()!=null) {
                sesn.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            sesn.close();
        }
        return isAdded;
    }

    // get Customer Number (CIF)
    public String getCode(String fname){
        Session sesn = factory.openSession();
        String lastValue = "";
        try {
            Query query = sesn.createQuery("SELECT COUNT(cif)+1 FROM Customer");
            Long last = (Long) query.uniqueResult();
            int val1 = Values.getRandomNumberInRange(1000,9000);
            int val2 = Values.getRandomNumberInRange(1000,9000);
            String lastIndex = String.valueOf(val1+""+val2);

            lastIndex = lastIndex+last;

            lastValue = Code.makeCode(fname.substring(0,1).toUpperCase(),lastIndex,8);

        } finally {
            sesn.close();
        }

        return lastValue;
    }
}
