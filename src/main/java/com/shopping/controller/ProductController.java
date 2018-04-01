package com.shopping.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.shopping.entity.Product;
import com.shopping.service.ProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
//import javax.enterprise.inject.Model;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class ProductController {
    @Resource
    private ProductService productService;



    @RequestMapping(value = "/getAllProducts")
    @ResponseBody
    public Map<String,Object> getAllProducts(){
        List<Product> productList = new ArrayList<>();
        productList = productService.getAllProduct();
        String allProducts = JSONArray.toJSONString(productList);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("allProducts",allProducts);
        return resultMap;
    }

    @RequestMapping(value = "/deleteProduct")
    public String deleteProduct(int id) {
         productService.deleteProduct(id);
        return "redirect:/showProductByType?type=衣服配饰";
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addProduct(String name,String Type,String picture,String description,String area,int state) {
        String result ="fail";
        Product product = new Product();
        product.setName(name);
        product.setType(Type);
        product.setPicture(picture);
        product.setDescription(description);
        product.setArea(area);
        product.setState(state);
        productService.addProduct(product);
        result = "success";
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",result);
        return resultMap;
    }

//////////////

/////////////////////////////查询类型接口，只查询state状态为上架的商品//////////////////////////////////////////////
    @RequestMapping(value = "/searchProduct")
    public List<Product> searchProduct(String serchType){
        List<Product> productList = new ArrayList<Product>();
        productList = productService.getProductsByTypeGuest(serchType);
        return productList;
    }
//////以iD查询商品
    @RequestMapping(value = "/getProductById")
    @ResponseBody
    public Map<String, Object> getProductById(int id) {
        Product product = productService.getProduct(id);
        String result = JSON.toJSONString(product);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",result);
        return resultMap;
    }
    //////////以状态查询商品
    @RequestMapping(value = "/getProductBystate")
    @ResponseBody
    public Map<String, Object> getProductByState(int state) {
        Product product = productService.getProductByState(state);
        String result = JSON.toJSONString(product);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",result);
        return resultMap;
    }
    ////////以名字查询商品

    @RequestMapping(value = "/getProductByName")
    @ResponseBody
    public Map<String, Object> getProductByName(String name) {
        Product product = productService.getProduct(name);
        String result = JSON.toJSONString(product);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",result);
        return resultMap;
    }

    /////////发布商品///////////////////////////////////////////////////

    @RequestMapping(value = "/postProduct")
    public String postProducte(int id) {
        productService.postProduct(id);
        return "redirect:/showProductByType";
    }

    ////////下架商品，state变为1，且记录日期////////////////////////////////////////////////////
    @RequestMapping(value = "/downProduct")
    public String downProducte(int id) {
         productService.downProduct(id);
        return "redirect:/showProductByType";
    }

    @Value("${productTime}")
    private String time;/////////////读取配置文件值，

    ///////////////定期清除下架商品/////////////////////////////////////
    @RequestMapping(value = "/dropProductInTime")
    public void dropProducteInTime() {
        System.out.print("从配置文件获取的时间为:"+time);
        int date=Integer.parseInt(time);
        productService.dropProductInTime(date);//////////////
    }

    @RequestMapping(value="/importProductInfo",method ={RequestMethod.GET})
    public String importProductInfo(Model model) throws Exception {
        return "importProductInfo";
    }


    //////////导入：上传excel文档，批量导入商品数据////////////////////////
    @RequestMapping("/import")
    @ResponseBody
    public String impotr(HttpServletRequest request, Model model) throws Exception {
        int adminId = 1;
        //获取上传的文件
        MultipartHttpServletRequest multipart = (MultipartHttpServletRequest) request;
        MultipartFile file = multipart.getFile("upfile");
        InputStream in = file.getInputStream();
        //数据导入
        productService.importExcelInfo(in,file);
        in.close();
        return "succece";
    }

    /////////////////////////////
    @RequestMapping(value = "/showProductByType")
    public String userManager(HttpServletRequest request,Model model) {
        String type=request.getParameter("Type");
        dropProducteInTime();// 查询前执行定期清除下架商品方法
        List<Product> list=null;
        list = productService.getProductsByType(type);
        model.addAttribute("productList", list);
        return "showProductByType";
    }

    // 修改商品信息页面显示
    @RequestMapping(value = "/editProduct", method = {RequestMethod.GET})
    public String editproductUI(Integer id, Model model) throws Exception {
        if (id == null) {

            return "redirect:/showProductByType";
        }
        Product product = productService.getProduct(id);
        model.addAttribute("product", product);
        return "/editProductInfo";
    }

    // 修改商品信息处理
    @RequestMapping(value = "/editProductInfo", method = {RequestMethod.POST})
    public String editproduct(@ModelAttribute Product product, HttpSession session) throws Exception {
        System.out.print("产品序号为："+product.getId());

        if(productService.updateProduct(product));
            System.out.print("产品名字为："+product.getName());
            String type=product.getType();
        //重定向
        return ("redirect:/showProductByType?Type="+type);
    }

    @RequestMapping(value = "/uploadFile")
    @ResponseBody
    public Map<String, Object> uploadFile(@RequestParam MultipartFile productImgUpload,String name, HttpServletRequest request) {
        String result = "fail";
        try{
            if(productImgUpload != null && !productImgUpload.isEmpty()) {
                String fileRealPath = request.getSession().getServletContext().getRealPath("/static/img");
                int id = productService.getProduct(name).getId();
                String fileName = String.valueOf(id)+".jpg";
                File fileFolder = new File(fileRealPath);
                System.out.println("fileRealPath=" + fileRealPath+"/"+fileName);
                if(!fileFolder.exists()){
                    fileFolder.mkdirs();
                }
                File file = new File(fileFolder,fileName);
                productImgUpload.transferTo(file);
                result = "success";
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result",result);
        return resultMap;
    }
}
