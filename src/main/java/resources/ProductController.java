package resources;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import domain.Customer;
import domain.ErrorInfo;
import domain.Product;
import exception.DataAccessException;

@RestController
@CrossOrigin(origins = "http://aces.usfornax.ifornax.ray.com:8089")
@RequestMapping(value="/demo")
public class ProductController 
{
   
   @Autowired
   private ProductService prodService;
   
   @Autowired
   private ProductValidator prodValidator;
   
   
   
   @InitBinder("product") 
   public void setupBinder(WebDataBinder binder) 
   { 
      binder.addValidators(prodValidator); 
   } 

   @RequestMapping(method=RequestMethod.GET, value="/Hello/{name}", produces = "application/json")
   public String helloText(@PathVariable("name") String name)
   {
	   System.out.println("calling helloText");
	   String resp = "How are you " + name;
	   return resp;
   }
   
   @RequestMapping(method=RequestMethod.GET, value="/getAllProduct")
    public Iterable<Product> getProducts () throws DataAccessException
    {
	   Iterable<Product> products  = null;
	   try
	   {
	      System.out.println("retrieve all products");
	      products = prodService.findAllProducts();
	   }
	   catch(Exception ex)
	   {
		   throw new DataAccessException("Error retriving products from database");
	   }
    	
    	
    	return products;
    }  
   
   /*@RequestMapping(method=RequestMethod.GET, value="/getProduct/{id}")
   public Product getProduct(@PathVariable("id") int id) throws DataAccessException
   {
	   
	   System.out.println("retrieve product for " + id);
	   Product product = prodService.findProduct(id);
	   
   	
   	return product;
   }  */
   
   @RequestMapping(method=RequestMethod.GET, value="/getCustomers")
   public Iterable<Customer> getCustomer () throws DataAccessException
   {
	   
	   Iterable<Customer> customers  = null;
	   
	   System.out.println("retrieve customers");
	   customers = prodService.findAllCustomers();
	   
	   return customers;
	   
   }
   
   
   @RequestMapping(method=RequestMethod.GET, value="/getCustomerByEmail/{email}")
   public Iterable<Customer> getCustomerByEmail (@PathVariable("email") String email) throws DataAccessException
   {
	   
	   Iterable<Customer> customers  = null;
	   
	   System.out.println("retrieve customers by email");
	   customers = prodService.findCustomerByEmail(email);
	   
	   return customers;
	   
   }
   
   
   
   
   @RequestMapping(method=RequestMethod.GET, value="/getCustomerByAddress/{address}")
   public Iterable<Customer> getCustomerByAddress(@PathVariable("address") String address) throws DataAccessException
   {
	   
	   Iterable<Customer> customers  = null;
	   
	   System.out.println("retrieve customers by email");
	   customers = prodService.findCustomerAddress(address);
	   
	   return customers;
	   
   }
   
   
   @RequestMapping(method=RequestMethod.GET, value="/getProductMoreExpensive")
   public List<Product> getProductMoreExpensiveThan(@RequestParam("price") int price,
		                          @RequestParam("name") String name) throws DataAccessException
   {
	   List<Product> prods = prodService.getProductMoreExpensiveThan(price, name);
	   
	   return prods;
   }
   
   
   @RequestMapping(method=RequestMethod.POST, value="/saveProducts")
   public void saveProducts(@Valid List<Product> prods)
   {
	   prodService.persistProducts(prods);
	   
   }
   
   @RequestMapping(method=RequestMethod.PUT, value="/modifyProduct/{id}")
   public void modifyProduct(@Valid Product prod, @PathVariable("id") int id)
   {
	   prodService.modifyProduct(prod, id);
	   
   }
   
   @RequestMapping(method=RequestMethod.GET, value="/displayerror")
   public String showError()
   {
	   return "displayerror";
   }
   
   @RequestMapping(method=RequestMethod.GET, value="/throwException")
   public void throwException ()
   {
	   throw new DataAccessException("Error accessing from database");
   }
   
   @ExceptionHandler(DataAccessException.class)
   @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
   @ResponseBody ErrorInfo
   handleProductException (DataAccessException ex, HttpServletRequest req)
   {
	   System.out.println("calling handleProductException");
	   req.setAttribute("javax.servlet.error.status_code",
						HttpStatus.INTERNAL_SERVER_ERROR.value()); 
	   req.setAttribute("exceptionMessage", ex.getMessage());
	   
	   ErrorInfo errorInfo = new ErrorInfo("/displayerror", ex);
	   
	   return errorInfo;
	   

   }
   
	   
}
