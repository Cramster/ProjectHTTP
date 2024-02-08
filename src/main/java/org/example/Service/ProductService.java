package org.example.Service;

import org.example.Model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService {

    List<Product> productList;
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
