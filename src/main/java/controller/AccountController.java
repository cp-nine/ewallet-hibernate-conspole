package controller;

import config.BorderPadding;
import config.Values;
import entities.Account;
import entities.Customer;
import services.IAccount;
import services.ICustomer;
import services.impl.AccountImpl;
import services.impl.CustomerImpl;

import java.util.List;

public class AccountController extends BorderPadding {

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

    // check username is used by another account
    public boolean isUsed(String username){
        boolean isused = false;
        if (accn.isUsed(username)){
            isused = true;
        }
        return isused;
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

	public void updatePin(String pin, Long acn) {
		if (accn.updatePin(pin, acn)){
            System.out.println("Update pin success");
        } else {
            System.out.println("Update pin failed");
        }
		
	}

		
	} 



