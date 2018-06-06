package resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;

import domain.Address;
import domain.Customer;
import domain.Product;


@SpringBootApplication
@EnableJpaRepositories("repository")
@CrossOrigin
public class ApplicationConfig implements CommandLineRunner 
{
	@Autowired
    ProductService prodService;

	
	public static void main(String[] args) throws Exception {
		
		System.out.println("Demo springboot");
		SpringApplication.run(ApplicationConfig.class, args);
	
	}
	
        @Override
        public void run(String... args) throws Exception 
        {
           System.out.println(" Running command lines");
           List<Product> products = new ArrayList<Product>();
           
           Product product = new Product("product1", "for sale", 12);
           Product product2 = new Product("product2", "for demo", 33);
           Product product3 = new Product("product3", "for sale", 15);
           Product product4 = new Product("product4", "for sale", 18);
          
           
           products.add(product);
           products.add(product2);
           products.add(product3);
           products.add(product4);
           
           prodService.persistProducts(products);
           
           Address address1 = new Address("2105 Apple valley", "75023", "US");
           
           Address address2= new Address("777 Dallas Medical City", "75082", "US");
           
           Address address3 = new Address("88 Arapaho", "75082", "US");
           
           Address address4 = new Address("123 Jupiter", "75082", "US");
           
           Address address5 = new Address("666 Beltline", "75082", "US");
           
           
           
           
           List<Address> addresses1 = new ArrayList<Address>();
           List<Address> addresses2 = new ArrayList<Address>();
           List<Address> addresses3 = new ArrayList<Address>();
           
           
           addresses1.add(address5);
           
           
           
           addresses2.add(address2);
           addresses2.add(address3);
           
           addresses3.add(address1);
           addresses3.add(address4);
           
           
           /*prodService.saveAddres(addresses2);
           prodService.saveAddres(addresses1);
           prodService.saveAddres(addresses3);*/
            
           
           
           List<Customer> customers = new ArrayList<Customer>();
           
           Customer customer1 = new Customer("thu", "nguyen","thu.nguyen@yahoo.com");
           
           Customer customer2 = new Customer("tiffany", "nguyen", "tiff.nguyen@yahoo.com");
           
           Customer customer3 = new Customer("conner", "nguyen", "tiff.nguyen@yahoo.com");
           
           customers.add(customer1);
           
           customers.add(customer2);
           
           customers.add(customer3);
           
           customer1.setAddress(addresses1);
           customer2.setAddress(addresses2);
           customer3.setAddress(addresses3);
           
           
           prodService.persistCustomers(customers);
           
           
        }


}


