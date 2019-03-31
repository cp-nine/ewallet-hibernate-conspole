package services;

import entities.TrxEntity;

import java.util.List;

public interface ITrx {
    // get all trx account
    List<TrxEntity> getTransReport(Long acn);
    boolean transfer(TrxEntity trxEntity);
    boolean tariktunai(TrxEntity trxEntity);
    boolean tariktunai(TrxEntity trxEntity, Integer wid);
    boolean topUp(TrxEntity trxEntity, Integer forWallet, Integer byWallet);
    boolean topUp(TrxEntity trxEntity, Integer forWallet);
    boolean transferByWallet(TrxEntity trxEntity, Integer wid);
    boolean transferByWallet(TrxEntity trxEntity, Integer wid, Integer toWid);
}
