package com.shopping.dao;

import com.shopping.entity.Product;
import com.shopping.entity.ProductDown;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.annotation.Resource;

@Repository
public class ProductDownDaoImplement implements ProductDownDao {

    @Resource
    private SessionFactory sessionFactory;

    @Autowired
    private ProductDao productDao;

    @Override
    public void setProductDownById(int product_id) {
        Product product=productDao.getProduct(product_id);
        String sql="insert into product_down (product_id,name,date)values(?,?,?)";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        query.setParameter(0,product.getId());
        query.setParameter(1,product.getName());
        Date currentDate = new Date(System.currentTimeMillis());
        query.setParameter(2,currentDate);
        query.executeUpdate();
    }

    @Override
    public ProductDown getProductDownByName(String name){
        String hql = "from ProductDown where name=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, name);
        return (ProductDown) query.uniqueResult();
    }

    @Override
    public ProductDown getProductDownByDate(int time){
        int newTime=0-time;
        Calendar calendar =new GregorianCalendar();
        Date currentDate=new Date(System.currentTimeMillis());
        calendar.setTime(currentDate);
        calendar.add(calendar.DATE, newTime);
        java.util.Date utilDate = (java.util.Date)calendar.getTime();
        utilDate = (java.util.Date)calendar.getTime();
        Date newDate =new Date(utilDate.getTime());
        String hql="from ProductDown where date<=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, newDate);
        return (ProductDown) query.uniqueResult();
    }
    @Override
    public ProductDown getProductDownById(int product_id){
        String hql="from ProductDown where product_id=?";
        Query query=sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,product_id);
        return (ProductDown)query.uniqueResult();

    }
    @Override
    public void deleteProductDownById(int product_id){
        String hql = "delete ProductDown where product_id=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, product_id);
        query.executeUpdate();
    }

    @Override
    public void updateProdductDownByName(String name) {
        String hql = "delete ProductDown where name=?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, name);
        query.executeUpdate();
    }
}