package repository;

import org.springframework.data.repository.CrudRepository;

import domain.Address;

public interface AddressRepository extends CrudRepository<Address,Long>
{
    
}
