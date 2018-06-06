package repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import domain.Product;

@Service
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom
{
	@PersistenceContext
	private final EntityManager entityManager;

	ProductRepositoryCustomImpl(EntityManager entityManager) 
	{
	   
	    this.entityManager = entityManager;
	}

	
	@Override
	@Transactional
	public List<Product> getProductMoreExpensiveThan(int price) 
	{
		String sql = "select u from Product u where u.price > :price";
        TypedQuery<Product> query = entityManager.createQuery(sql, Product.class);
        
        query.setParameter("price", price);
        
        List<Product> prods = query.getResultList();
        
        return prods;
		
	}
	
	

	
}
