package services.impl;

import data.DataWallet;
import entities.Wallet;
import entities.WalletAccount;
import services.IWallet;

import java.util.List;

public class WalletImpl implements IWallet {

    private static DataWallet dataWallet = new DataWallet();

    @Override
    public List<WalletAccount> getAllWallet(Long acn) {

        return dataWallet.getAllWallet(acn);
    }

    @Override
    public List<WalletAccount> getAllWalletForTrans(Long acn, Integer wallId) {
        return dataWallet.getAllWalletForTrans(acn, wallId);
    }

    @Override
    public Integer getLastBallance(Integer wid) {
        Integer value = null;
        try {
            Integer balance = dataWallet.getLastActiveBalance(wid);
            if (balance != null){
                value = balance;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return value;
    }

    @Override
    public String getType(Integer wid) {
        return dataWallet.getTypeWallet(wid);
    }

    public Wallet getWp(Integer wid) {
        Wallet wallet = null;
        try {
            Wallet walletWp = dataWallet.getWp(wid);
            if (walletWp != null){
                wallet = walletWp;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return wallet;
    }

}
