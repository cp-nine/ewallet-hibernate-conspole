package services.impl;

import data.DataAccount;
import data.DataTrx;
import data.DataWallet;
import entities.TrxEntity;
import services.ITrx;

import java.util.List;

public class TrxImpl implements ITrx {

    private static DataTrx dataTrx = new DataTrx();
    private static DataAccount dataAccount = new DataAccount();
    private static DataWallet dataWallet = new DataWallet();

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
            dataTrx.transaction(trxEntity);
            trans = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return trans;
    }

    @Override
    public boolean tariktunai(TrxEntity trxEntity) {
        boolean trans = false;
        try {
            dataAccount.updateSaldoMinus(trxEntity.getAcnCredit(), trxEntity.getAmount());
            dataTrx.transaction(trxEntity);
            trans = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return trans;
    }

    @Override
    public void topUp(TrxEntity trxEntity, Integer forWallet, Integer byWallet) {
        dataWallet.updateBalancePlus(trxEntity.getAmount(),forWallet);
        dataWallet.updateBalanceMinus(trxEntity.getAmount(), byWallet);
        dataTrx.transaction(trxEntity);
    }

    @Override
    public void topUp(TrxEntity trxEntity, Integer forWallet) {
        dataWallet.updateBalancePlus(trxEntity.getAmount(), forWallet);
        dataAccount.updateSaldoMinus(trxEntity.getAcnDebet(), trxEntity.getAmount());
        dataTrx.transaction(trxEntity);
    }
}
