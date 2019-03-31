package services.impl;

import data.DataCustomer;
import entities.Account;
import entities.Customer;
import services.ICustomer;

import java.util.List;

public class CustomerImpl implements ICustomer {

    private static DataCustomer dataCustomer = new DataCustomer();

    // implement method add
    public boolean addCustomer(Customer customer) {
        boolean isAdded = false;
        try {
            String cCode = dataCustomer.getCode(customer.getFname());
            customer.setCif(cCode);
            if (dataCustomer.addCustomer(customer)){
                isAdded =true;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return isAdded;
    }

    // get all customer
    public List<Customer> getAllCustomer() {
        return dataCustomer.listCustomer();
    }

    // get customer name and cif
    public Customer getCustomerNameAndCif(String cif) {
        Customer customer = dataCustomer.getNameCif(cif);
        return customer;

    }

	public Account getAccount(String cif) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Customer getCustomer(String cif) {
        return dataCustomer.getCustomer(cif);
    }

    @Override
    public List<Account> getAccountList(String cif) {
        return dataCustomer.getAccountList(cif);
    }

    public Customer login(String username, String password) {
        Customer logAccount = null;
        try {
            Customer account = dataCustomer.login(username, password);
            if (account != null) {
                logAccount = account;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return logAccount;

    }

    @Override
    public boolean isUsed(String username) {
        boolean isUsed = false;
        if (dataCustomer.getByUsername(username)) {
            isUsed = true;
        }
        return isUsed;
    }

    @Override
    public boolean updatePassword(String password, String cif) {
        boolean isUpdated = false;
        if (dataCustomer.updatePassword(password, cif)) {
            isUpdated = true;
        }
        return isUpdated;
    }

}
