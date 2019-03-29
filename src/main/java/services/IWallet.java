package services;

import entities.Wallet;
import entities.WalletAccount;

import java.util.List;

public interface IWallet {

    List<WalletAccount> getAllWallet(Long acn);
    String getType(Integer wid);
    Wallet getWp(Integer wid);

}
