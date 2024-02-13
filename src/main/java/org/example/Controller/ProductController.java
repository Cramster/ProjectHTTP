package org.example.Controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;
import org.example.Exception.ProductException;
import org.example.Exception.SellerException;
import org.example.Model.Seller;
import org.example.Service.ProductService;
import org.example.Service.SellerService;
import org.example.Model.Product;
import io.javalin.Javalin;
import java.util.List;

public class ProductController {

    SellerService sellerService;
    ProductService productService;

    public ProductController(SellerService sellerService, ProductService productService) {
        this.sellerService = sellerService;
        this.productService = productService;
    }

    public Javalin getAPI() {
        Javalin api = Javalin.create();
        api.get("health", context -> {
            context.result("Server is running! :-)");
        });

        //GET + POST for both seller/product
        api.get("seller", context -> {
            List<Seller> sellerList = sellerService.getSellerList();
            context.json(sellerList);
        });
        api.get("product", context -> {
            List<Product> productList = productService.getProductList();
            context.json(productList);
        });

        api.post("seller", context -> {
            try{
                ObjectMapper om = new ObjectMapper();
                Seller s = om.readValue(context.body(), Seller.class);
                Seller newSeller = sellerService.addSeller(s);
                //sellerService.addSeller(s);
                context.status(201);
                context.json("Seller added:\n"+newSeller);
            }catch(JsonProcessingException e){
                context.status(400);
            }catch (SellerException e){
                context.result(e.getMessage());
                context.status(400);}
        });
        api.post("product", context -> {
            try {
                ObjectMapper om = new ObjectMapper();
                Product p = om.readValue(context.body(), Product.class);
                Product newProduct = productService.addProduct(p);
                //productService.addProduct(p);
                context.status(201);
                context.json("Product added:\n"+newProduct);
            }catch (JsonProcessingException e){
                context.status(400);
            }catch (ProductException e){
                context.result(e.getMessage());
                context.status(400);
            }
        });

        //1: product ID found
        //2: product ID not found
        api.get("product/{id}", context -> {
            long id = Long.parseLong(context.pathParam("id"));
            Product p = productService.getProductById(id);
            if (p == null){
                context.status(404);
                context.json("Product not found.");
            }else{
                context.json("Product found:\n"+p);
                context.status(200);
            }
        });
        //Get Seller by ID
        api.get("seller/{id}", context -> {
            long id = Long.parseLong(context.pathParam("id"));
            Seller s = sellerService.getSellerById(id);
            if (s == null){
                context.status(404);
                context.json("Seller not found.");
            }else{
                context.json("Seller found:\n"+s);
                context.status(200);
            }
        });

        //Add Delete Product
        api.delete("product/{id}", context -> {
            long id = Long.parseLong(context.pathParam("id"));
            Product p = productService.deleteProductById(id);
            //if (p == null){
                context.status(200);
                context.json("Product now removed.");
            //}else{
            //    context.json(p);
            //    context.status(200);
            //    context.json("Product removed.");
            //}
        });

        //-Add Update Product/Seller
        //code here for Product/Seller Updater
        return api;
    }


/*
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
 */


}