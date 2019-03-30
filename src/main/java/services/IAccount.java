package services;

import entities.Account;

public interface IAccount {

    // create new account
    boolean createNewAccount(Account acc);

    // login account
    Account login(String cif, String password);
    Account getAccount(String cif);

    // check is used by another account
    boolean isUsed(String username);

    Long getLastBallance(Long acn);

    //check update pin
    boolean updatePin(String pin, Long accn);

}
