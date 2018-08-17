package metro.test.metro_test.repositories;

import metro.test.metro_test.entities.impl.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CustomerRepo extends JpaRepository<Customer, Long> {

    //Безусловно все эти методы реализованы в интерфейсе. Но для примера SQL запросов реализовал свои.

    @Query(value = "SELECT * FROM customers WHERE id = :id", nativeQuery = true)
    Customer findCustomerById(@Param("id") Long id);

    @Query(value = "SELECT * FROM customers", nativeQuery = true)
    List<Customer> findAllCustomers();

    @Query(value = "SELECT * FROM customers WHERE name LIKE :c_name", nativeQuery = true)
    List<Customer> findCustomersByName(@Param("c_name") String name);

    @Query(value = "UPDATE customers SET id = :id, age = :age, mobile_no = :mobile_no, name = :c_name", nativeQuery = true)
    Customer updateCustomer(
            @Param("id") Long id,
            @Param("age") int age,
            @Param("mobile_no") String mobile_no,
            @Param("c_name") String name);

    @Query(value = "DELETE FROM customers WHERE id = :id", nativeQuery = true)
    void deleteCustomerById(@Param("id") Long id);

//    Пример HQL SELECT-а с JOIN-ом из моего проекта.
//    @Query(value = "SELECT contact FROM Contact contact " +
//            "left join contact.fullName as fullName " +
//            "where lower(contact.fullName.firstName) like concat('%',:search,'%') " +
//            "or lower(contact.fullName.surname)  like concat('%',:search,'%')")
//    Page<Contact> searchContactByName(@Param("search") String search, Pageable pageable);
}
