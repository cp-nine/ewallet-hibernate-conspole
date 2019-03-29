package services;

import entities.TrxEntity;

import java.util.List;

public interface ITrx {
    // get all trx account
    List<TrxEntity> getTransReport(Long acn);
    boolean transfer(TrxEntity trxEntity);
    boolean tariktunai(TrxEntity trxEntity);
    boolean tariktunai(TrxEntity trxEntity, Integer wid);
    void topUp(TrxEntity trxEntity, Integer forWallet, Integer byWallet);

    void topUp(TrxEntity trxEntity, Integer forWallet);
    void transferByWallet(TrxEntity trxEntity, Integer wid);
    void transferByWallet(TrxEntity trxEntity, Integer wid, Integer toWid);
}
