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

}
