package data;

import config.Code;
import config.HibernateConfig;
import config.Values;
import entities.Wallet;
import entities.WalletAccount;
import org.hibernate.*;

import java.util.ArrayList;
import java.util.List;

public class DataWalletAccount {

    // get session factory connection (replacement DBConnection)
    private static SessionFactory factory = new HibernateConfig().getSessionFactory();


    public boolean addWalletAccount(WalletAccount walletAccount){
        Session sesn = factory.openSession();
        boolean isAdded = false;
        try {
            Transaction trx = sesn.beginTransaction();
            sesn.save(walletAccount);
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
            Query query = sesn.createQuery("SELECT wallet_id FROM WalletAccount WHERE account_number= :acn AND status='active'");
            query.setParameter("acn", acn);
            wallets = query.list();
        } catch (HibernateException e){
            e.printStackTrace();
        } finally {
            sesn.close();
        }
        return wallets;
    }

    public int isRegistered(Long accountNumber, Integer wid) {

        Session sesn = factory.openSession();
        int lastValue = 0;
        try {
            Query query = sesn.createQuery("SELECT COUNT(wallet_id)+1 FROM WalletAccount");
            Long last = (Long) query.uniqueResult();

            if (last > 0){
                lastValue = Integer.parseInt(last.toString());
            }

        } finally {
            sesn.close();
        }
        return lastValue;

    }

    public boolean unreg(Long nr, Integer wid) {
        Session sesn = factory.openSession();
        boolean lastValue = false;
        try {
            Transaction trx = sesn.beginTransaction();
            Query query = sesn.createQuery("UPDATE WalletAccount SET status= :sts WHERE account_number= :acn AND wallet_id.wallet_id= :wid");
            query.setParameter("sts", "nonactive");
            query.setParameter("acn", nr);
            query.setParameter("wid", wid);
            if (query.executeUpdate() > 0){
                lastValue = true;
            }
            trx.commit();

        } finally {
            sesn.close();
        }
        return lastValue;
    }
}
