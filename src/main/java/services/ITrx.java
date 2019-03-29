package services;

import entities.TrxEntity;

import java.util.List;

public interface ITrx {
    // get all trx account
    List<TrxEntity> getTransReport(Long acn);
    boolean transfer(TrxEntity trxEntity);

}
