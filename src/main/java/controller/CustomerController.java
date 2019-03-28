package controller;

import entities.Customer;
import services.impl.CustomerImpl;

import java.util.List;

public class CustomerController {

    private static CustomerImpl customerImpl = new CustomerImpl();

    public List<Customer> getAllCustomer() {
        return customerImpl.getAllCustomer();
    }

    public void addCustomer(Customer customer){
        if (!customerImpl.addCustomer(customer)){
            System.out.println("Failed to add Customer");
        } else {
            System.out.println("Add customer success");
        }
    }


}
