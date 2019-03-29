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

    public void topup(TrxEntity trxEntity, Integer forWallet) {
        iTrx.topUp(trxEntity, forWallet);
    }

    public void topup(TrxEntity trxEntity, Integer forWallet, Integer byWallet) {
        iTrx.topUp(trxEntity, forWallet, byWallet);
    }
}
