package controller;

import entities.Account;
import services.IAccount;
import services.ICustomer;
import services.impl.AccountImpl;
import services.impl.CustomerImpl;

public class AccountController {

    private IAccount accn = new AccountImpl();
    private ICustomer cst = new CustomerImpl();

    // create new account
    public void createAccount(Account account) {

        if (accn.createNewAccount(account)) {
            System.out.println("New account has been created.");
        } else {
            System.out.println("Failed to create new account");
        }

    }

}
