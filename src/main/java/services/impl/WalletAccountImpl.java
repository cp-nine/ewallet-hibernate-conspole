package services.impl;

import data.DataWallet;
import data.DataWalletAccount;
import entities.Wallet;
import entities.WalletAccount;
import services.IWalletAccount;

import java.util.List;

public class WalletAccountImpl implements IWalletAccount {

    private static DataWalletAccount dataWalletAccount = new DataWalletAccount();
    private static DataWallet dataWallet = new DataWallet();

    @Override
    public boolean isAvailableWallet(Long accountNumber) {
        boolean walletAccount = false;

        try {
            List<WalletAccount> getWa = dataWalletAccount.isAvailableWallet(accountNumber);
            if (getWa.size() > 0) {
                walletAccount = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return walletAccount;
    }

    @Override
    public boolean isRegister(Long accountNumber, Integer wid) {
        boolean walletAccount = false;

        try {
            int getWa = dataWalletAccount.isRegistered(accountNumber, wid);
            if (getWa > 0) {
                walletAccount = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return walletAccount;
    }

    @Override
    public boolean unreg(Long nr, Integer wid) {
       if(!dataWalletAccount.unreg(nr, wid)){
           return false;
       } else {
           return true;
       }
    }


    @Override
    public Long getAcnNumber(Integer id) {
        return dataWalletAccount.getAcnNumber(id);
    }

    @Override
    public List<Wallet> getAllWalletId(Long acn) {
        return dataWalletAccount.getAllWalletId(acn);
    }

    @Override
    public boolean addWalletAccount(String type, String description, Long acn) {
        boolean isAdded = false;
        try {
            Integer walletId = Integer.parseInt(dataWallet.getCode());
            Wallet wallet = new Wallet();
            wallet.setWallet_id(walletId);
            wallet.setDescription(description);
            wallet.setType(type);

            WalletAccount wac = new WalletAccount();
            wac.setAccount_number(acn);
            wac.setWallet_id(wallet);
            wac.setStatus("active");

            dataWalletAccount.addWalletAccount(wac);
            isAdded = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isAdded;
    }




}
