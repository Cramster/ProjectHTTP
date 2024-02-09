package org.example.Service;

import org.example.Model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService {

    SellerService sellerService;

    List<Product> productList;

    public ProductService(SellerService sellerService){
        this.sellerService = sellerService;
        productList = new ArrayList<>();
    }

    public List<Product> getProductList(){
        return productList;
    }

    public void addProduct(Product p){
        productList.add(p);
    }


//from 2-6 car service example, above being from painter/author example
   public ProductService(){
        productList = new ArrayList<>();
    }
    public List<Product> getAllProducts(){
        return productList;
    }
    public void insertProduct(Product product){
        productList.add(product);
    }
}
