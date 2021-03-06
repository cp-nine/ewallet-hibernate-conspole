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
    public boolean tariktunai(TrxEntity trxEntity, Integer wid) {
        boolean trans = false;
        try {
            dataWallet.updateBalanceMinus(trxEntity.getAmount(), wid);
            dataTrx.transaction(trxEntity);
            trans = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return trans;
    }

    @Override
    public boolean topUp(TrxEntity trxEntity, Integer forWallet, Integer byWallet) {
        boolean trans = false;
        try {
            dataWallet.updateBalancePlus(trxEntity.getAmount(),forWallet);
            dataWallet.updateBalanceMinus(trxEntity.getAmount(), byWallet);
            dataTrx.transaction(trxEntity);
            trans = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return trans;
    }

    @Override
    public boolean topUp(TrxEntity trxEntity, Integer forWallet) {
        boolean trans = false;
        try {
            dataWallet.updateBalancePlus(trxEntity.getAmount(), forWallet);
            dataAccount.updateSaldoMinus(trxEntity.getAcnDebet(), trxEntity.getAmount());
            dataTrx.transaction(trxEntity);
            trans = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return trans;
    }

    @Override
    public boolean transferByWallet(TrxEntity trxEntity, Integer wid) {
        boolean trans = false;
        try {
            dataWallet.updateBalanceMinus(trxEntity.getAmount(), wid);
            dataAccount.updateSaldoPlus(trxEntity.getAcnDebet(), trxEntity.getAmount());
            dataTrx.transaction(trxEntity);
            trans = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return trans;
    }

    @Override
    public boolean transferByWallet(TrxEntity trxEntity, Integer wid, Integer toWid) {
        boolean trans = false;
        try {
            dataWallet.updateBalanceMinus(trxEntity.getAmount(), wid);
            dataWallet.updateBalancePlus(trxEntity.getAmount(), toWid);
            dataTrx.transaction(trxEntity);
            trans = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return trans;
    }
}
