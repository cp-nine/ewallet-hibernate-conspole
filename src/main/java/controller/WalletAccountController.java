package controller;

import entities.TrxEntity;
import entities.Wallet;
import services.ITrx;
import services.IWalletAccount;
import services.impl.TrxImpl;
import services.impl.WalletAccountImpl;

import java.util.List;

public class WalletAccountController {

    private static IWalletAccount iwc = new WalletAccountImpl();
    private static ITrx iTrx = new TrxImpl();

    public boolean isAvailableWallet(Long an) {

        if (iwc.isAvailableWallet(an)) {
            return true;
        } else {
            return false;
        }
    }

    public void transfer(TrxEntity trxEntity) {
        if (!iTrx.transfer(trxEntity)){
            System.out.println("Transfer success");
        } else {
            System.out.println("Transfer failed");
        }
    }

    public void tariktunai(TrxEntity trxEntity) {
        if (!iTrx.tariktunai(trxEntity)){
            System.out.println("Cash Withdrawal success");
        } else {
            System.out.println("Cash Withdrawal failed");
        }
    }

    public void tariktunai(TrxEntity trxEntity, Integer wid) {
        if (!iTrx.tariktunai(trxEntity, wid)){
            System.out.println("Cash Withdrawal success");
        } else {
            System.out.println("Cash Withdrawal failed");
        }
    }

    public void topup(TrxEntity trxEntity, Integer forWallet) {
        iTrx.topUp(trxEntity, forWallet);
    }

    public void topup(TrxEntity trxEntity, Integer forWallet, Integer byWallet) {
        iTrx.topUp(trxEntity, forWallet, byWallet);
    }

    public void transferByWallet(TrxEntity trxEntity, Integer wid) {
        iTrx.transferByWallet(trxEntity, wid);
    }

    public void transferByWallet(TrxEntity trxEntity, Integer wid, Integer toWallet) {
        iTrx.transferByWallet(trxEntity, wid, toWallet);
    }

    public void addWalletAccount(String type, String description, Long accountNumber) {
        iwc.addWalletAccount(type, description, accountNumber);
    }

    public boolean isRegister(Long an, Integer wid) {
        boolean isregister = false;

        try {
            if (iwc.isRegister(an, wid)) {
                isregister = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isregister;
    }

    public void unreg(Long nr, Integer wid) {
        if(!iwc.unreg(nr, wid)){
            System.out.println("Failled to unreg");
        } else {
            System.out.println("Un reg success");
        }
    }
}
