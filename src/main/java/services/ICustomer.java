package services;

import entities.Customer;

import java.util.List;

public interface ICustomer {

    boolean addCustomer(Customer customer);
    List<Customer> getAllCustomer(); // ?

    Customer getCustomerNameAndCif(String cif);

}
