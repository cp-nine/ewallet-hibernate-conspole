package services.impl;

import data.DataAccount;
import data.DataTrx;
import entities.TrxEntity;
import services.ITrx;

import java.util.List;

public class TrxImpl implements ITrx {

    private static DataTrx dataTrx = new DataTrx();
    private static DataAccount dataAccount = new DataAccount();

    // get all transaction report by account number
    @Override
    public List<TrxEntity> getTransReport(Long acn) {
        return dataTrx.getListTrans(acn);
    }

    @Override
    public boolean transfer(TrxEntity trxEntity) {
        boolean trans = false;
        try {
            dataAccount.updateSaldoMinus(trxEntity.getAcnCredit(), trxEntity.getAmount());
            dataAccount.updateSaldoPlus(trxEntity.getAcnDebet(), trxEntity.getAmount());
            dataTrx.transfer(trxEntity);
            trans = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return trans;
    }
}
