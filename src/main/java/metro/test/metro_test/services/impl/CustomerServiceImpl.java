package metro.test.metro_test.services.impl;

import metro.test.metro_test.entities.impl.Customer;
import metro.test.metro_test.repositories.CustomerRepo;
import metro.test.metro_test.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomerServiceImpl implements CustomerService {

    //Не добавлял работу с этими методами (кроме createCustomer(Customer customer)) в контроллер что бы не перегружать его.

    @Autowired
    CustomerRepo customerRepo;

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepo.findCustomerById(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepo.findAllCustomers();
    }

    @Override
    public List<Customer> getCustomersByName(String name) {
        return customerRepo.findCustomersByName(name);
    }

    @Override
    public Customer updateCustomer(Customer customer, Long id) {
        return customerRepo.updateCustomer(
                id, customer.getAge(), customer.getMobile_no(), customer.getName());
// Если не использовать нативные запросы, так мне удобней.
//        customer.setId(id);
//        customerRepo.save(customer);
//        return customer;
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepo.deleteCustomerById(id);
//        customerRepo.deleteById(id);
    }
}
