package com.shopping.service;

import com.shopping.dao.ProductDao;
import com.shopping.dao.ProductDownDao;
import com.shopping.entity.Product;
import com.shopping.entity.ProductDown;
import com.shopping.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


@Service
public class ProductServiceImplement implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductDownDao productDownDao;

    @Override
    public Product getProduct(int id) {
        return productDao.getProduct(id);
    }

    @Override
    public Product getProductByState(int state) {
        return productDao.getProductByState(state);
    }

    @Override
    public Product getProduct(String name) {
        return productDao.getProduct(name);
    }

    @Override
    public void addProduct(Product product) {
        productDao.addProduct(product);
    }

    @Override
    public boolean deleteProduct(int id) {
        return productDao.deleteProduct(id);
    }

    /////////////
    @Override
    public void dropProductInTime(int time) {
        if (null!=productDownDao.getProductDownByDate(time)) {
            ProductDown productDown = productDownDao.getProductDownByDate(time);
            System.out.print("下架超时商品：" + productDown.getName());
            String name = productDown.getName();
            Product product = productDao.getProduct(name);
            productDao.deleteProduct(product.getId());
            productDownDao.updateProdductDownByName(name);
        }else System.out.print("没有下架超时商品");
    }

    @Override
    public boolean postProduct(int id) {
        Product product=productDao.getProduct(id);
        if(null!=productDownDao.getProductDownById(product.getId())){
            productDownDao.deleteProductDownById(product.getId());
        }
        return productDao.updateProductStateUp(id);
    }

    @Override
    public void downProduct(int id) {
        Product product = productDao.getProduct(id);
        System.out.print("需要下架的商品id"+id);
//        String name = product.getName();
       productDownDao.setProductDownById(id); //////将下架商品上传记录
        productDao.updateProductStateDown(id);
    }

    @Override
    public boolean updateProduct(Product product) {
        return productDao.updateProduct(product);
    }

    @Override
    public List<Product> getProductsByType(String type) {
        return productDao.getProductsByType(type);
    }

    @Override
    public List<Product> getProductsByTypeGuest(String type) {
        return productDao.getProductsByTypeGuest(type);
    }

    @Override
    public List<Product> getProductsByState(int state) {
        return productDao.getProductsByState(state);
    }

    @Override
    public List<Product> getAllProduct() {
        return productDao.getAllProduct();
    }

    @Override
    public void importExcelInfo(InputStream in, MultipartFile file) throws Exception {
        List<List<Object>> listob = ExcelUtil.getBankListByExcel(in, file.getOriginalFilename());

        List<Product> productList = new ArrayList<Product>();
        //遍历listob数据，把数据放到List中
        for (int i = 0; i < listob.size(); i++) {
            List<Object> ob = listob.get(i);


            Product product = new Product();
            //设置编号
            //productList.setSerial(SerialUtil.salarySerial());
            //通过遍历实现把每一列封装成一个model中，再把所有的model用List集合装载
            //SurveyTitle temp = new SurveyTitle();
            //product.setId(productDao.getMaxId().getId()+1);
            product.setName(String.valueOf(ob.get(0)));
            System.out.print(String.valueOf((ob.get(0))));
            product.setType(String.valueOf(ob.get(1)));
            product.setPicture(String.valueOf(ob.get(2)));
            product.setDescription(String.valueOf(ob.get(3)));
            product.setArea(String.valueOf(ob.get(4)));
            product.setState(Integer.parseInt(ob.get(5).toString()));
            productDao.saveProduct(product);

            //productList.add(product);
        }
        //批量插入
        //productDao.insertInfoBatch(productList);
    }
}
