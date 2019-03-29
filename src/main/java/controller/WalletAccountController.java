package controller;

import entities.TrxEntity;
import entities.Wallet;
import services.ITrx;
import services.IWalletAccount;
import services.impl.TrxImpl;
import services.impl.WalletAccountImpl;

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
        iTrx.transfer(trxEntity);
    }
}
