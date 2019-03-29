package services;

import entities.Wallet;

import java.util.List;

public interface IWalletAccount {

    boolean isAvailableWallet(Long accountNumber);

    Long getAcnNumber(Integer id);
    List<Wallet> getAllWalletId(Long acn);
    boolean addWalletAccount(String type, String description, Long acn); //?
}
