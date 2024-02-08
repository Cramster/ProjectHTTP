package org.example.Controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;
import org.example.Service.ProductService;
import org.example.Model.Product;

import io.javalin.Javalin;
import java.util.List;

public class ProductController {

    static ProductService productService;

    public ProductController() {
        this.productService = new ProductService();
    }

    public Javalin getAPI(){
        Javalin api = Javalin.create();

        api.get("/health/", context -> {
            context.result("The server is running!");
            } );

        api.get("/product/", ProductController::getAllProductHandler);
        api.post("/product/", ProductController::postProductHandler);

        return api;
    }

    public static void getAllProductHandler(Context context){
        List<Product> productList = productService.getAllProducts();
        context.json(productList);
    }

    public static void postProductHandler(Context context){
        ObjectMapper om = new ObjectMapper();
        try{
            Product p = om.readValue(context.body(), Product.class);
            //Product newProduct = new Product("default", "default", 0);
            productService.insertProduct(p);
            context.status(201);
            //Resource created from 201
        } catch (JsonProcessingException e){
            context.status(400);
            //Jackson unable to parseJSON or user error so 400
        }
    }
}