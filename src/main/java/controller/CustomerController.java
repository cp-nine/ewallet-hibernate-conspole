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
        Values.isSucces(customerImpl.addCustomer(customer), "Add Custommer");
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
        System.out.println("=========== Your Account =================");
        border(51); // create horizontal border table
        System.out.println("| " + padRight("No", 5)
                + "| " + padRight("Account Name", 20)
                + "| " + padRight("Account Number", 25)
                + "| " + padRight("Balance", 20)
                + "| " + padRight("Open Date", 21)
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
                        + "| " + padRight(tr.getAccountName(), 20)
                        + "| " + padRight(Values.balance(tr.getAccountNumber().toString()), 25)
                        + "| " + padRight(Values.rupiah(tr.getBalance()), 20)
                        + "| " + padRight(tr.getOpen_date().toString(), 21)
                        + " |"
                );

            }
        }
        border(51);

    }

    // check username is used by another account
    public boolean isUsedCustomer(String username){

        boolean isused = false;
        if (customerImpl.isUsed(username)){
            isused = true;
        }
        return isused;

    }

    public void updatePassword(String password, String cif) {

        Values.isSucces(customerImpl.updatePassword(password, cif), "Update Password");

    }

}
