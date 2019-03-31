package data;

import config.Code;
import config.HibernateConfig;
import config.Values;
import entities.Account;
import org.hibernate.*;


public class DataAccount {

    // get session factory connection (replacement DBConnection)
    private static SessionFactory factory = new HibernateConfig().getSessionFactory();

    public boolean addAccount(Account account) {
        Session sesn = factory.openSession();
        boolean isAdded = false;
        try {
            account.setAccountNumber(Long.parseLong(getCode()));
            Transaction trx = sesn.beginTransaction();
            sesn.save(account);
            trx.commit();
            isAdded = true;
        } catch (Exception sqlException) {
            if (sesn.getTransaction() != null) {
                sesn.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            sesn.close();
        }
        return isAdded;
    }

    public Account login(String username, String password) {
        Session sesn = factory.openSession();
        Account users = new Account();
        try {
            Query query = (Query) sesn.createQuery("From Account Where username.username=:username and password=:password");
            query.setParameter("username", username);
            query.setParameter("password", password);
            users = (Account) query.uniqueResult();
        } catch (HibernateException e) {
            sesn.close();
            e.printStackTrace();
        }
        return users;
    }


    public boolean getByUsername(String username) {
        Session sesn = factory.openSession();

        boolean account = false;
        try {
            Query query = sesn.createQuery("From Account Where username = :username");
            query.setParameter("username", username);
            int size = query.list().size();
            if (size > 0) {
                account = true;
            }
        } catch (HibernateException e) {
            sesn.close();
            e.printStackTrace();
        }
        return account;
    }

    // get last balace
    public Long getLastBalance(Long acNumber) {
        Long balance = null;
        Session sesn = factory.openSession();
        try {
            Query query = (Query) sesn.createQuery("SELECT balance From Account Where account_number= :acn");
            query.setParameter("acn", acNumber);
            balance = (Long) query.uniqueResult();
        } catch (HibernateException e) {
            sesn.close();
            e.printStackTrace();
        }
        return balance;
    }

    // get kode
    public String getCode() {
        String lastValue = "";
        // open new session
        Session sesn = factory.openSession();
        try {
            // try to execute query and insert result to users list
            Query query = sesn.createQuery("SELECT COUNT(account_number)+1 FROM Account");
            Object data = query.uniqueResult();
            int val1 = Values.getRandomNumberInRange(100000, 900000);
            int val2 = Values.getRandomNumberInRange(100000, 900000);
            int frontUniq = Values.getRandomNumberInRange(1, 9);
            String lastIndex = String.valueOf(val1 + "" + val2);
            lastIndex = lastIndex + data.toString();
            lastValue = Code.makeCode(String.valueOf(frontUniq).toUpperCase(), lastIndex, 10);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return lastValue;
    }

    public Account getCustomer(String cif, Long acnum) {
        Session sesn = factory.openSession();
        Account users = new Account();

        try {
            Query query = (Query) sesn.createQuery("From Account Where cif.cif= :cif AND account_number= :acnum");
            query.setMaxResults(1);
            query.setParameter("cif", cif);
            query.setParameter("acnum", acnum);
            users = (Account) query.uniqueResult();
        } catch (HibernateException e) {
             sesn.close();
            e.printStackTrace();
        }
        return users;
    }


    public boolean updatePin(String pin, Long acn) {
        Session sesn = factory.openSession();
        boolean isUpdated = false;
        try {
            Query query = (Query) sesn.createQuery("update Account set password= :password where accountNumber= :accountNumber");
            query.setParameter("password", pin);
            query.setParameter("accountNumber", acn);
            Transaction trx = sesn.beginTransaction();
            query.executeUpdate();
            trx.commit();
            isUpdated = true;
        } catch (HibernateException e) {
            sesn.close();
            e.printStackTrace();
        }
        return isUpdated;
    }

    public boolean updateSaldoPlus(Long acn, Long saldo) {
        Session sesn = factory.openSession();
        boolean isSaved = false;
        try {
            Long lastBalance = getLastBalance(acn);
            Transaction trx = sesn.beginTransaction();
            Query query = (Query) sesn.createQuery("UPDATE Account SET balance= :blc Where account_number= :acn");
            query.setParameter("blc", lastBalance + saldo);
            query.setParameter("acn", acn);
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

    public boolean updateSaldoMinus(Long acn, Long saldo) {
        Session sesn = factory.openSession();
        boolean isSaved = false;
        try {
            Long lastBalance = getLastBalance(acn);
            Transaction trx = sesn.beginTransaction();
            Query query = (Query) sesn.createQuery("UPDATE Account SET balance= :blc Where account_number= :acn");
            query.setParameter("blc", lastBalance - saldo);
            query.setParameter("acn", acn);
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
