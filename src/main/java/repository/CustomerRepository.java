package repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import domain.Customer;


public interface CustomerRepository extends CrudRepository<Customer,Long>

{
   List<Customer> findByEmailAddress(String email);
   
   List<Customer> findByAddress(String address);
   

}


