package controller;

import entities.Customer;
import services.impl.CustomerImpl;

import java.util.List;

public class CustomerController {
    // instance of Customer Implement
    private static CustomerImpl customerImpl = new CustomerImpl();

    // get all customer as list
    public List<Customer> getAllCustomer() {
        return customerImpl.getAllCustomer();
    }

    // get name and cif
    public Customer getNameCif(String cif){
        Customer data = customerImpl.getCustomerNameAndCif(cif);
        if ( data != null){
            return data;
        } else {
            return null;
        }
    }

    // add new customer
    public void addCustomer(Customer customer){
        if (!customerImpl.addCustomer(customer)){
            System.out.println("Failed to add Customer");
        } else {
            System.out.println("Add customer success");
        }
    }


}
