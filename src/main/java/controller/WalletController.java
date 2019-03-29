package controller;

import entities.Wallet;
import entities.WalletAccount;
import services.IWallet;
import services.IWalletAccount;
import services.impl.WalletAccountImpl;
import services.impl.WalletImpl;

import java.util.List;

public class WalletController {

    private static IWalletAccount iwc = new WalletAccountImpl();
    private static IWallet iWallet = new WalletImpl();

    public void getAllWallet(Long acn) {

        try {
            List<WalletAccount> wallets = iWallet.getAllWallet(acn);
            if (wallets.size() < 1){
                System.out.println("Cannot show wallets");
            } else {
                System.out.println("======== Your Wallet =========");
                int no = 1;
                for (WalletAccount w : wallets) {
                    System.out.println((no++) + ". " + w.getWallet_id().getDescription()+" "+w.getAccount_number());
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<WalletAccount> getAllWallet(Long acn, Integer wallId) {
        return iWallet.getAllWalletForTrans(acn, wallId);
    }

    public List<Wallet> getAllWalletId(Long acn) {
        List<Wallet> wallId = null;

        try {
            List<Wallet> wallets = iwc.getAllWalletId(acn);
            if (wallets.size() < 1){
                System.out.println("Cannot show wallets");
            } else {
                wallId = wallets;
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return wallId;
    }

    public String getWalletDescType(Integer wid){
        return iWallet.getType(wid);
    }

    public Wallet getWp(Integer wid) {
        Wallet value = null;
        try {
            Wallet wallet = iWallet.getWp(wid);
            if (wallet != null) {
                value = wallet;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public Integer getLastBalance(Integer wid) {
        Integer value = 0;
        try {
            Integer balance = iWallet.getLastBallance(wid);
            if (balance != null) {
                value = balance.intValue();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

}
