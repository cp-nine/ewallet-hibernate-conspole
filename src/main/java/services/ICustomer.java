package services;

import entities.Account;
import entities.Customer;

import java.util.List;

public interface ICustomer {

    boolean addCustomer(Customer customer);
    List<Customer> getAllCustomer(); // ?

    Customer getCustomerNameAndCif(String cif);
	Account getAccount(String cif);



}
