package resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Service;

import repository.AddressRepository;
import repository.CustomerRepository;
import repository.ProductRepository;
import domain.Address;
import domain.Customer;
import domain.Product;
import exception.DataAccessException;


@Service
@EntityScan("domain") 
public class ProductService 
{
   private ProductRepository prodRepo;
   private CustomerRepository  customerRepo;
   private AddressRepository  addressRepo;
   
   @Autowired
   public ProductService(ProductRepository prodRepo, CustomerRepository  customerRepo, AddressRepository  addressRepo)
   {
	   this.prodRepo = prodRepo;
	   this.customerRepo = customerRepo;
	   this.addressRepo = addressRepo;
   }
   
   
   
   public void persistProducts(List<Product> products)
   {
	   System.out.println("persistProducts ");
	   
	      for (Product prod:products)
	      {
		     prodRepo.save(prod);
	      }
	  
   }
   
   
   public void modifyProduct(Product prod, int id)
   {
	   System.out.println("modifyProduct");
	   Optional<Product> product = prodRepo.findById(new Long(Integer.toUnsignedLong(id)));
	   if (product.isPresent())
	   {
		   prodRepo.save(prod);
	   }
	   else
	   {
		   throw new DataAccessException("Product id " + id + " not found");
	   }
	  
   }
   
   public Iterable<Product>  findAllProducts()
   {
	   Iterable<Product> prods  = prodRepo.findAll();
	   
	   for(Product prod:prods)
	   {
		   System.out.println("Prod name " + prod.getName() + " description: " + prod.getDescription() + " price: " + prod.getPrice());
	   }
	   
	   return prods;
   }
   
   
   public Product findProduct(int id)
   {
	   Product prod = prodRepo.findById(id);
	   
	   System.out.println("Prod name " + prod.getName() + " description: " + prod.getDescription() + " price: " + prod.getPrice());
   
	   
	   return prod;
   }
   
   
   public void persistCustomers(List<Customer> customers)
   {
	   System.out.println("persistCustomer");
	   
	      for (Customer customer:customers)
	      {
		     customerRepo.save(customer);

	      }
   }
   
   
   
   public Iterable<Customer>  findAllCustomers()
   {
	   Iterable<Customer> customers  = customerRepo.findAll();
	   
	   for(Customer customer:customers)
	   {
		   System.out.println("Customer name " + customer.getFirstname() + " address: " + customer.getAddress()+ " email: " + customer.getEmailAddress());
	   }
	   
	   return customers;
   }
   
   
  public List<Customer>  findCustomerByEmail(String email)
   {
	   List<Customer> customers  = customerRepo.findByEmailAddress(email);
	   
	   for(Customer customer:customers)
	   {
		   System.out.println("Customer name " + customer.getFirstname() + " email: " + customer.getEmailAddress());
	   }
	   
	   return customers;
   }
   
    
  
   public List<Customer>  findCustomerAddress(String address)
   {
	   List<Customer> customers  = customerRepo.findByAddress(address);
	   
	   for(Customer customer:customers)
	   {
		   System.out.println("Customer name " + customer.getFirstname() + " address: " + customer.getAddress());
	   }
	   
	   return customers;
   }
   
   
   public List<Product> getProductMoreExpensiveThan(int price, String name) 
   {
	   System.out.println("get product for " + name + price);
	   List<Product> prods = prodRepo.getProductMoreExpensiveThan(price, name);
	   
	   return prods;
   }
   
   
   public  void saveAddres(List<Address> addresses)
   {
	   for(Address address:addresses)
	   {
	      addressRepo.save(address);
	   }
   }
   
   
   
   
   
   
}
