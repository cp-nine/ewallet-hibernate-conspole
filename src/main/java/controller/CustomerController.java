package controller;

import config.BorderPadding;
import config.Values;
import entities.Account;
import entities.Customer;
import services.impl.CustomerImpl;

import java.util.List;

public class CustomerController extends BorderPadding {
    // instance of Customer Implement
    private static CustomerImpl customerImpl = new CustomerImpl();

    // get all customer as list
    public List<Customer> getAllCustomer() {
        return customerImpl.getAllCustomer();
    }

    // get name and cif
    public Customer getNameCif(String cif) {
        Customer data = customerImpl.getCustomerNameAndCif(cif);
        if (data != null) {
            return data;
        } else {
            return null;
        }
    }

    // add new customer
    public void addCustomer(Customer customer) {
        if (!customerImpl.addCustomer(customer)) {
            System.out.println("Failed to add Customer");
        } else {
            System.out.println("Add customer success");
        }
    }

    public Customer loginCustomer(String username, String password) {
        Customer isLogin = null;

        Customer login = customerImpl.login(username, password);
        if (login != null) {
            isLogin = login;
        }

        return isLogin;
    }

    public void getProfileCustomer(String cif) {
        Customer acn = customerImpl.getCustomer(cif);

        System.out.println("=============== Profiles ===================");
        System.out.println("Name           : " + acn.getFname() + " " + acn.getLname());
        System.out.println("Brith of Date  : " + acn.getbDate());
        System.out.println("Username       : " + acn.getUsername());
        System.out.println("============================================");
    }

    public void listAccount(String cif) {

        List<Account> listAcn = customerImpl.getAccountList(cif);
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

        if (listAcn.size() < 1) {
            System.out.println("| " + padRight(("You have not transaction yet"), 30) + " |"
            );
        } else {
            for (Account tr : listAcn) {
                int num = no++;
                System.out.println("| " + padRight((String.valueOf(num)), 5)
                        + "| " + padRight("Top Up", 20)
                        + "| " + padRight(tr.getAccountName(), 25)
                        + "| " + padRight(" - ", 20)
                        + "| " + padRight(tr.getAccountNumber().toString(), 21)
                        + " |"
                );

            }
        }
        border(51);

    }

}
