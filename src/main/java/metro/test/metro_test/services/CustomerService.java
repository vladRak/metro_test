package metro.test.metro_test.services;

import metro.test.metro_test.entities.impl.Customer;

import java.util.List;

public interface CustomerService {
    Customer createCustomer(Customer customer);

    Customer getCustomerById(Long id);

    List<Customer> getAllCustomers();

    List<Customer> getCustomersByName(String name);

    Customer updateCustomer(Customer customer, Long id);

    void deleteCustomer(Long id);
}
