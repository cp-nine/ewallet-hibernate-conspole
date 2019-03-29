package services;

import entities.TrxEntity;

import java.util.List;

public interface ITrx {
    // get all trx account
    List<TrxEntity> getTransReport(Long acn);
    boolean transfer(TrxEntity trxEntity);
    boolean tariktunai(TrxEntity trxEntity);
    void topUp(TrxEntity trxEntity, Integer forWallet, Integer byWallet);

    void topUp(TrxEntity trxEntity, Integer forWallet);
}
