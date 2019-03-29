package controller;

import config.BorderPadding;
import config.Values;
import entities.TrxEntity;
import services.ITrx;
import services.impl.TrxImpl;

import java.util.List;


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
                int num = no++;
                if (tr.getTrxType().equals("T0001")) {
                    System.out.println("| " + padRight((String.valueOf(num)), 5)
                            + "| " + padRight("Top Up", 20)
                            + "| " + padRight(Values.rupiah(tr.getAmount()), 25)
                            + "| " + padRight(" - ", 20)
                            + "| " + padRight(String.valueOf(tr.getDate()), 21)
                            + " |"
                    );
                } else if (tr.getTrxType().equals("T0002")) {
                    System.out.println("| " + padRight((String.valueOf(num)), 5)
                            + "| " + padRight("Transfer", 20)
                            + "| " + padRight(Values.rupiah(tr.getAmount()), 25)
                            + "| " + padRight(Values.balance(String.valueOf(tr.getAcnDebet())), 20)
                            + "| " + padRight(String.valueOf(tr.getDate()), 21)
                            + " |"
                    );
                } else if (tr.getTrxType().equals("T0003")) {
                    System.out.println("| " + padRight((String.valueOf(num)), 5)
                            + "| " + padRight("With Draw", 20)
                            + "| " + padRight(Values.rupiah(tr.getAmount()), 25)
                            + "| " + padRight(" - ", 20)
                            + "| " + padRight(String.valueOf(tr.getDate()), 21)
                            + " |"
                    );
                } else if (tr.getTrxType().equals("T0004")) {
                    System.out.println("| " + padRight((String.valueOf(num)), 5)
                            + "| " + padRight("Payment", 20)
                            + "| " + padRight(Values.rupiah(tr.getAmount()), 25)
                            + "| " + padRight(" - ", 20)
                            + "| " + padRight(String.valueOf(tr.getDate()), 21)
                            + " |"
                    );
                }
            }
        }
        border(51);

    }

}
