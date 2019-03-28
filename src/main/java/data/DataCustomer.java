package data;

import config.Code;
import config.HibernateConfig;
import config.Values;
import entities.Customer;
import org.hibernate.*;

import java.util.ArrayList;
import java.util.List;

public class DataCustomer {

    private static SessionFactory factory = new HibernateConfig().getSessionFactory();

    public List<Customer> listCustomer(){
        Session sesn = factory.openSession();

        List<Customer> users = new ArrayList<Customer>();
        try {
            Query query = sesn.createQuery("FROM Customer");
            users = query.list();
        } catch (HibernateException e){
            e.printStackTrace();
        } catch(Exception sqlException) {
            if(null != sesn.getTransaction()) {
                sesn.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            sesn.close();
        }
        return users;
    }

    public boolean addCustomer(Customer customer){
        Session sesn = factory.openSession();
        boolean isAdded = false;
        try {
            Transaction trx = sesn.beginTransaction();
            sesn.save(customer);
            trx.commit();
            isAdded = true;
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
