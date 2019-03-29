package data;

import config.HibernateConfig;
import entities.Wallet;
import entities.WalletAccount;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class DataWalletAccount {

    // get session factory connection (replacement DBConnection)
    private static SessionFactory factory = new HibernateConfig().getSessionFactory();

    public List<WalletAccount> isAvailableWallet(Long acnumber) {
        Session sesn = factory.openSession();

        // create temp variable for list user as array list
        List<WalletAccount> users = new ArrayList<WalletAccount>();
        try {
            // try to execute query and insert result to users list
            Query query = sesn.createQuery("FROM WalletAccount WHERE account_number= :acn AND status='active'");
            query.setParameter("acn", acnumber);
            users = query.list();
        } catch (HibernateException e){
            e.printStackTrace();
        } finally {
            sesn.close();
        }
        return users;
    }

    public Long getAcnNumber(Integer id){
        Long acnnumber = null;

        Session sesn = factory.openSession();
        try {
            // try to execute query and insert result to users list
            Query query = sesn.createQuery("SELECT account_number FROM WalletAccount WHERE wallet_id.wallet_id= :id AND status='active'");
            query.setParameter("id", id);
            query.setMaxResults(1);
            acnnumber = (Long) query.uniqueResult();
        } catch (HibernateException e){
            e.printStackTrace();
        } finally {
            sesn.close();
        }
        return acnnumber;
    }

    public List<Wallet> getAllWalletId(Long acn){

        List<Wallet> wallets = new ArrayList<Wallet>();
        Session sesn = factory.openSession();
        try {
            // try to execute query and insert result to users list
            Query query = sesn.createQuery("SELECT wallet_id FROM WalletAccount WHERE account_number= :acn");
            query.setParameter("acn", acn);
            wallets = query.list();
        } catch (HibernateException e){
            e.printStackTrace();
        } finally {
            sesn.close();
        }
        return wallets;
    }

}
