package controller;

import config.Values;
import entities.Wallet;
import entities.WalletAccount;
import services.IAccount;
import services.IWallet;
import services.IWalletAccount;
import services.impl.AccountImpl;
import services.impl.WalletAccountImpl;
import services.impl.WalletImpl;

import java.util.List;

public class WalletController {

    private static IAccount iAccount = new AccountImpl();
    private static IWalletAccount iwc = new WalletAccountImpl();
    private static IWallet iWallet = new WalletImpl();

    public void getAllWallet(Long acn) {

        try {
            List<WalletAccount> wallets = iWallet.getAllWallet(acn);
            if (wallets.size() < 1) {
                System.out.println("Cannot show wallets");
            } else {
                System.out.println("======== Your Wallet =========");
                int no = 1;
                for (WalletAccount w : wallets) {
                    System.out.println((no++) + ". " + w.getWallet_id().getDescription() + " " + w.getAccount_number());
                }
            }
        } catch (Exception e) {
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
            if (wallets.size() < 1) {
                System.out.println("Cannot show wallets");
            } else {
                wallId = wallets;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return wallId;
    }

    public String getWalletDescType(Integer wid) {

        String walletId = iWallet.getType(wid);
        if (!walletId.equals("") || walletId.equals(null)) {
            System.out.println("Wallet not found.");
        }

        return walletId;
    }

    public void getWp(Integer wid, Long sessAccountNumber) {

        Long balance = Long.valueOf(0);
        Wallet wallet = iWallet.getWp(wid);
        if (wallet.getType().equals("e-banking")) {
            balance = iAccount.getLastBallance(sessAccountNumber);
        } else {
            balance = wallet.getActiveBalance();
        }

        System.out.println("=============== Profiles ===================");
        System.out.println("Wallei Id      : " + wallet.getWallet_id());
        System.out.println("Description    : " + wallet.getDescription());
        System.out.println("Type           : " + wallet.getType());
        System.out.println("Active Balance : " + Values.rupiah(balance));
        System.out.println("============================================");
        System.out.println();

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
