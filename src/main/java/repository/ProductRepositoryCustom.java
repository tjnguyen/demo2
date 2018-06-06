package repository;

import java.util.List;

import domain.Product;

public interface ProductRepositoryCustom 
{
	List<Product>  getProductMoreExpensiveThan(int price);
	
	
}

