package controller;

import config.BorderPadding;
import config.Values;
import entities.TrxEntity;
import services.ITrx;
import services.impl.TrxImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TransactionController extends BorderPadding {

    private static ITrx trx = new TrxImpl();

    // transaction report
    public void transactionReport(Long acn) {

        List<TrxEntity> listtrx = trx.getTransReport(acn);
        int no = 1; // create list number
        System.out.println("=========== Transaction Report =================");
        border(51); // create horizontal border table
        System.out.println("| " + padRight("No", 5)
                + "| " + padRight("Transaction", 20)
                + "| " + padRight("Amount", 25)
                + "| " + padRight("Account", 20)
                + "| " + padRight("Time", 21)
                + " |"
        );
        border(51);

        if (listtrx.size() < 1) {
            System.out.println("| " + padRight(("You have not transaction yet"), 30)+ " |"
            );
        } else {
            for (TrxEntity tr : listtrx) {

                Map<String, String> trx = checkTransaction(tr.getTrxType(),tr.getAcnDebet());

                int num = no++;
                System.out.println("| " + padRight((String.valueOf(num)), 5)
                        + "| " + padRight(trx.get("transaction"), 20)
                        + "| " + padRight(Values.rupiah(tr.getAmount()), 25)
                        + "| " + padRight((trx.get("transaction").equals("Transfer"))? trx.get("trxaccount") : " - ", 20)
                        + "| " + padRight(String.valueOf(tr.getDate()), 21)
                        + " |"
                );
            }
        }
        border(51);

    }


    static Map<String, String> checkTransaction(String type, Long acnDebet){
        Map<String, String> maps = new HashMap<String, String>();

        String transaction="";
        String trxAccount="";
        if (type.equals("T0001")){
            transaction = "Top Up";
        } else if (type.equals("T0002")){
            transaction = "Transfer";
            trxAccount = acnDebet.toString();
        } else if (type.equals("T0003")){
            transaction = "Cash Withdrawal";
        } else if (type.equals("T0004")){
            transaction = "Payment";
        }

        maps.put("transaction", transaction);
        maps.put("trxaccount", trxAccount);

        return maps;

    }

}
