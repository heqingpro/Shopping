package com.shopping.dao;

import com.shopping.entity.ProductDown;

public interface ProductDownDao {
    public ProductDown getProductDownByName(String name);

    public void setProductDownById(int product_id);

    public ProductDown getProductDownByDate(int time);

    public void updateProdductDownByName(String name);

    public ProductDown getProductDownById(int product_id);

    public void deleteProductDownById(int product_id);

}
