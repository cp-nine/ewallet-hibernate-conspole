package services;

import entities.Wallet;

import java.util.List;

public interface IWalletAccount {

    boolean isAvailableWallet(Long accountNumber);
    boolean isRegister(Long accountNumber, Integer wid);

    Long getAcnNumber(Integer id);
    List<Wallet> getAllWalletId(Long acn);
    boolean addWalletAccount(String type, String description, Long acn); //?
    boolean unreg(Long nr, Integer wid);
}
