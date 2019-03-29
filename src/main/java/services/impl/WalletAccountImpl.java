package services.impl;

import data.DataWalletAccount;
import entities.Wallet;
import entities.WalletAccount;
import services.IWalletAccount;

import java.util.List;

public class WalletAccountImpl implements IWalletAccount {

    private static DataWalletAccount dataWalletAccount = new DataWalletAccount();

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
    public Long getAcnNumber(Integer id) {
        return dataWalletAccount.getAcnNumber(id);
    }

    @Override
    public List<Wallet> getAllWalletId(Long acn) {
        return dataWalletAccount.getAllWalletId(acn);
    }


}
