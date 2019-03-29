package services;

import entities.Account;

public interface IAccount {

    // create new account
    boolean createNewAccount(Account acc);

    // login account
    Account login(String cif, String password);

    // check is used by another account
    boolean isUsed(String username);

}
