package com.shopping.dao;

import com.shopping.entity.Product;

import java.util.List;


public interface ProductDao {
    public Product getProduct(int id);

    public Product getProductByState(int state);

    public Product getProduct(String name);

    public Product getMaxId();

    public void addProduct(Product product);

    //public void addProductNew(Product product);

    //public void insertInfoBatch(List<Product> productList);

    public boolean deleteProduct(int id);

    public boolean updateProduct(Product product);

    public boolean updateProductStateUp(int id);

    public boolean updateProductStateDown(int id);

    public void saveProduct(Product product);

    public List<Product> getProductsByType(String type);

    public List<Product> getProductsByTypeGuest(String type);

    public List<Product> getProductsByState(int state);

    public List<Product> getAllProduct();

}
