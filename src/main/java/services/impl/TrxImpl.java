package services.impl;

import data.DataTrx;
import entities.TrxEntity;
import services.ITrx;

import java.util.List;

public class TrxImpl implements ITrx {

    private static DataTrx dataTrx = new DataTrx();

    // get all transaction report by account number
    public List<TrxEntity> getTransReport(Long acn) {
        return dataTrx.getListTrans(acn);
    }
}
