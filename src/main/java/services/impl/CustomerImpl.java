package services.impl;

import data.DataCustomer;
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

}
