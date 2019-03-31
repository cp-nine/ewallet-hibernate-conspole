package controller;

import config.Values;
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

        if (!iwc.isAvailableWallet(an)) {
            return false;
        } else {
            return true;
        }
    }

    public void transfer(TrxEntity trxEntity) {
        Values.isSucces(iTrx.transfer(trxEntity), "Transfer");
    }

    public void tariktunai(TrxEntity trxEntity) {
        Values.isSucces(iTrx.tariktunai(trxEntity), "Cash Withdrawal");
    }

    public void tariktunai(TrxEntity trxEntity, Integer wid) {
        Values.isSucces(iTrx.tariktunai(trxEntity, wid), "Cash Withdrawal");
    }

    public void topup(TrxEntity trxEntity, Integer forWallet) {
        Values.isSucces(iTrx.topUp(trxEntity, forWallet), "Top Up");
    }

    public void topup(TrxEntity trxEntity, Integer forWallet, Integer byWallet) {
        Values.isSucces(iTrx.topUp(trxEntity, forWallet, byWallet), "Top Up");
    }

    public void transferByWallet(TrxEntity trxEntity, Integer wid) {
        Values.isSucces(iTrx.transferByWallet(trxEntity, wid), "Transfer");
    }

    public void transferByWallet(TrxEntity trxEntity, Integer wid, Integer toWallet) {
        Values.isSucces(iTrx.transferByWallet(trxEntity, wid, toWallet), "Transfer");
    }

    public void addWalletAccount(String type, String description, Long accountNumber) {
        Values.isSucces(iwc.addWalletAccount(type, description, accountNumber), "Create E-Wallet");
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
        Values.isSucces(iwc.unreg(nr, wid), "Unreg Wallet");
    }
}
