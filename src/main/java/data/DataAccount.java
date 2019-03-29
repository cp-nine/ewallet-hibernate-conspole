package data;

import config.Code;
import config.HibernateConfig;
import config.Values;
import entities.Account;
import org.hibernate.*;
import java.sql.Statement;
import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

public class DataAccount {

	// get session factory connection (replacement DBConnection)
	private static SessionFactory factory = new HibernateConfig().getSessionFactory();

	public boolean addAccount(Account account) {
		Session sesn = factory.openSession();
		boolean isAdded = false;
		try {
//			account.setAccount_number(Long.valueOf(getCode()));
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
		{
			Session sesn = factory.openSession();
			Account users = new Account();
			try {
				Query query = (Query) sesn.createQuery("From Account Where username=:username and password=:password");
				query.setParameter("username", username);
				query.setParameter("password", password);
				users = (Account) query.uniqueResult();
			} catch (HibernateException e) {
				// sesn.close();
				e.printStackTrace();
			}
			return users;
		}
	}

//	public Account getProfileAccount(String accountName, String accountNumber, String balance) {
//		Session sesn = factory.openSession();
//		Account users = new Account();
//		
//		try {
//			Query query = (Query) sesn.createQuery("From Account Where accountname= account_name and accousernumber=accountNumber and balance=balance");
//			query.setParameter("accountname", accountName);
//			query.setParameter("accountnumber", accountNumber);
//			query.setParameter("balance", balance);
//			users = (Account) query.uniqueResult();
//		} catch (HibernateException e) {
//			// sesn.close();
//			e.printStackTrace();
//		}
//		return users;
//
//	}

	

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

	public Account getCustomer(String cif) {
		Session sesn = factory.openSession();
		Account users = new Account();
		
		try {
			Query query = (Query) sesn.createQuery("From Account Where cif.cif= :cif");
			query.setParameter("cif", cif);
			users = (Account) query.uniqueResult();
		} catch (HibernateException e) {
			// sesn.close();
			e.printStackTrace();
		}
		return users;
	}

}
