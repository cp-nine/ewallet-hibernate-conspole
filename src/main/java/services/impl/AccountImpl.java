package services.impl;

import data.DataAccount;
import entities.Account;
import services.IAccount;

public class AccountImpl implements IAccount {

    private static DataAccount dataAccount = new DataAccount();

    public boolean createNewAccount(Account acc) {
        boolean isAdded = false;

        try {
            dataAccount.addAccount(acc);
            isAdded = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isAdded;
    }

    public Account login(String cif, String password) {
        Account logAccount = null;
        try {
            Account account = dataAccount.login(cif, password);
            if( account != null){
                logAccount = account;
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return logAccount;

    }

    @Override
    public boolean isUsed(String username) {
        boolean isUsed = false;
            if (dataAccount.getByUsername(username)){
                isUsed = true;
            }
        return isUsed;
    }
}
