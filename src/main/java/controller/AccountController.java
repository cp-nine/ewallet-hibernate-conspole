package controller;

import config.BorderPadding;
import config.Values;
import entities.Account;
import services.IAccount;
import services.ICustomer;
import services.impl.AccountImpl;
import services.impl.CustomerImpl;

import java.util.List;

public class AccountController extends BorderPadding {

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

    public Integer getLastBalance(Long acn) {
        Integer value = 0;
        try {

            Long balance = accn.getLastBallance(acn);
            if (balance != null) {
                value = balance.intValue();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }
}
