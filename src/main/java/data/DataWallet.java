package data;

import config.HibernateConfig;
import entities.Wallet;
import entities.WalletAccount;
import org.hibernate.*;

import java.util.ArrayList;
import java.util.List;

public class DataWallet {

    // get session factory connection (replacement DBConnection)
    private static SessionFactory factory = new HibernateConfig().getSessionFactory();

    // get all wallet by account
    public List<WalletAccount> getAllWallet(Long acn){

        List<WalletAccount> wallets = new ArrayList<WalletAccount>();
        Session sesn = factory.openSession();
        try {
            // try to execute query and insert result to users list
            Query query = sesn.createQuery("FROM WalletAccount WHERE account_number= :acn ORDER BY wa_id DESC");
            query.setParameter("acn", acn);
            wallets = query.list();
        } catch (HibernateException e){
            e.printStackTrace();
        } finally {
            sesn.close();
        }
        return wallets;
    }

    // get wallet desc
    public String getTypeWallet(Integer wid){

        String type = "";
        Session sesn = factory.openSession();
        try {
            // try to execute query and insert result to users list
            Query query = sesn.createQuery("FROM WalletAccount WHERE wallet_id.wallet_id= :wid");
            query.setParameter("wid", wid);
            WalletAccount wallet = (WalletAccount) query.uniqueResult();
            type = String.valueOf(wallet.getWallet_id().getType());
        } catch (HibernateException e){
            e.printStackTrace();
        } finally {
            sesn.close();
        }
        return type;
    }

    // get wallet profile
    public Wallet getWp(Integer wid){
        Wallet wallet = new Wallet();
        Session sesn = factory.openSession();
        try {
            // try to execute query and insert result to users list
            Query query = sesn.createQuery("FROM Wallet WHERE wallet_id= :wid");
            query.setParameter("wid", wid);
            wallet = (Wallet) query.uniqueResult();
        } catch (HibernateException e){
            e.printStackTrace();
        } finally {
            sesn.close();
        }
        return wallet;
    }

    public List<WalletAccount> getAllWalletForTrans(Long acn, Integer wallId){
;
        List<WalletAccount> wallets = new ArrayList<WalletAccount>();
        Session sesn = factory.openSession();
        try {
            // try to execute query and insert result to users list
            Query query = sesn.createQuery("FROM WalletAccount WHERE account_number= :acn AND wallet_id.wallet_id!= :wallId ");
            query.setParameter("acn", acn);
            query.setParameter("wallId", wallId);
            wallets = query.list();
        } catch (HibernateException e){
            e.printStackTrace();
        } finally {
            sesn.close();
        }
        return wallets;
    }

    public Integer getLastActiveBalance(Integer wid) {
        Integer balance = 0;
        Session sesn = factory.openSession();
        try {
            Query query = (Query) sesn.createQuery("SELECT activeBalance From Wallet Where wallet_id= :wid");
            query.setParameter("wid", wid);
            balance =  Integer.parseInt(query.uniqueResult().toString());
        } catch (HibernateException e) {
            sesn.close();
            e.printStackTrace();
        }
        return balance;
    }

    public boolean updateBalancePlus(Long ammount, Integer wid){
        Session sesn = factory.openSession();
        boolean isSaved = false;
        try {
            Integer lastBalance = getLastActiveBalance(wid);
            Transaction trx = sesn.beginTransaction();
            Query query = (Query) sesn.createQuery("UPDATE Wallet SET activeBalance= :blc Where wallet_id= :wid");
            query.setParameter("blc", lastBalance+ammount);
            query.setParameter("wid", wid);
            query.executeUpdate();
            trx.commit();
            isSaved = true;
        } catch (Exception sqlException) {
            if (sesn.getTransaction() != null) {
                sesn.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            sesn.close();
        }
        return isSaved;
    }

    public boolean updateBalanceMinus(Long ammount, Integer wid){
        Session sesn = factory.openSession();
        boolean isSaved = false;
        try {
            Integer lastBalance = getLastActiveBalance(wid);
            Transaction trx = sesn.beginTransaction();
            Query query = (Query) sesn.createQuery("UPDATE Wallet SET active_balance= :blc Where wallet_id= :wid");
            query.setParameter("blc", lastBalance-ammount);
            query.setParameter("wid", wid);
            query.executeUpdate();
            trx.commit();
            isSaved = true;
        } catch (Exception sqlException) {
            if (sesn.getTransaction() != null) {
                sesn.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            sesn.close();
        }
        return isSaved;
    }
}
