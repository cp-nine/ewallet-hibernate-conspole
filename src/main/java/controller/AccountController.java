package controller;

import config.Values;
import entities.Account;
import entities.Customer;
import services.IAccount;
import services.ICustomer;
import services.impl.AccountImpl;
import services.impl.CustomerImpl;

public class AccountController {

    private IAccount accn = new AccountImpl();
    private ICustomer da = new CustomerImpl();
    // create new account
    public void createAccount(Account account) {

        if (accn.createNewAccount(account)) {
            System.out.println("New account has been created.");
        } else {
            System.out.println("Failed to create new account");
        }

    }

    public Account login(String username, String password) {
        Account isLogin = null;

        Account login = accn.login(username, password);
        if (login != null) {
            isLogin = login;
        }

        return isLogin;
    }

	public void getProfileAccount(String cif) {
		Account acn = accn.getAccount(cif);

		        System.out.println("=============== Profiles ===================");
		        System.out.println("Name           : " + acn.getAccountname());
		        System.out.println("Account Number : " + Values.balance(String.valueOf(acn.getAccountNumber())));
		        System.out.println("Balance        : " + Values.rupiah(acn.getBalance()));
		        System.out.println("============================================");
		    }

		
	}



