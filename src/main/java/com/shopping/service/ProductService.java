package com.shopping.service;

import com.shopping.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;


public interface ProductService {
    public Product getProduct(int id);

    public Product getProductByState(int state);

    public Product getProduct(String name);

    public void addProduct(Product product);

    public boolean deleteProduct(int id);

    public boolean postProduct(int id);

    public void downProduct(int id);

    public void dropProductInTime(int time);

    public boolean updateProduct(Product product);

    public List<Product> getProductsByType(String type);

    public List<Product> getProductsByTypeGuest(String type);

    public List<Product> getProductsByState(int state);

    public List<Product> getAllProduct();
///////////导入Excel//////////////////////////////////////////////////////////////////////////////////////////
    public void importExcelInfo(InputStream in, MultipartFile file) throws Exception;
}
