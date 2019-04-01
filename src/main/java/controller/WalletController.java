package controller;

import config.BorderPadding;
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

public class WalletController extends BorderPadding {

    private static IAccount iAccount = new AccountImpl();
    private static IWalletAccount iwc = new WalletAccountImpl();
    private static IWallet iWallet = new WalletImpl();

    public void getAllWallet(Long acn) {

        try {
            List<WalletAccount> wallets = iWallet.getAllWallet(acn);
            if (wallets.size() < 1) {
                System.out.println("Cannot show wallets");
            } else {
                int no = 1; // create list number
                System.out.println("=========== Your Wallet =================");
                border(41); // create horizontal border table
                System.out.println("| " + padRight("No", 5)
                        + "| " + padRight("Wallet Id", 10)
                        + "| " + padRight("Wallet Name", 15)
                        + "| " + padRight("Account Number", 20)
                        + "| " + padRight("Open Date", 21)
                        + " |"
                );
                border(41);

                    for (WalletAccount tr : wallets) {
                        int num = no++;
                        System.out.println("| " + padRight((String.valueOf(num)), 5)
                                + "| " + padRight(tr.getWallet_id().getWallet_id().toString(), 10)
                                + "| " + padRight(tr.getWallet_id().getDescription(), 15)
                                + "| " + padRight(Values.balance(tr.getAccount_number().toString()), 20)
                                + "| " + padRight(tr.getWallet_id().getCreatedAt().toString(), 20)
                                + " |"
                        );

                }
                border(41);
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
        if (walletId.equals("") || walletId.equals(null)) {
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

    public boolean walletValidate(Integer wid, String toWalletId){
        boolean isYour = false;
        try {
            Wallet wallet = iWallet.getWp(wid);
            if(wallet.getWallet_id() != Integer.parseInt(toWalletId)){
                isYour = true;
            } else {
                isYour = false;
            }

        } catch (Exception e){
            return false;
        }
        return isYour;
    }

}
