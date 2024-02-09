package org.example;
import io.javalin.Javalin;
import org.example.Controller.ProductController;
import org.example.Model.Seller;
import org.example.Service.ProductService;
import org.example.Service.SellerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    public static void main(String[] args) {
//instantiate + inject all dependencies-
        SellerService sellerService = new SellerService();
        ProductService productService = new ProductService(sellerService);
        ProductController productController = new ProductController(sellerService, productService);
        Javalin api = productController.getAPI();
        api.start(9004); //increment from previous iteration 9003


        /* code from car service
        ProductController productController = new ProductController();
        Javalin api = productController.getAPI();
        api.start(9003);
        */
    }
}