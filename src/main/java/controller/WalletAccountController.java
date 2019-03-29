package controller;

import entities.Wallet;
import services.IWalletAccount;
import services.impl.WalletAccountImpl;

public class WalletAccountController {

    private static IWalletAccount iwc = new WalletAccountImpl();

    public boolean isAvailableWallet(Long an) {

            if (iwc.isAvailableWallet(an)) {
                return true;
            } else {
                return false;
            }
    }
}
