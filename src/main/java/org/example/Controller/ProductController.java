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
        //Health check
        Javalin api = Javalin.create();
        api.get("health", context -> {
            context.result("Server is running! :-)");
        });
        //Return list of Sellers
        api.get("seller", context -> {
            List<Seller> sellerList = sellerService.getSellerList();
            context.json(sellerList);
        });
        //Return list of Products
        api.get("product", context -> {
            List<Product> productList = productService.getProductList();
            context.json(productList);
        });
        //Post new Seller
        api.post("seller", context -> {
            try{
                ObjectMapper om = new ObjectMapper();
                Seller s = om.readValue(context.body(), Seller.class);
                Seller newSeller = sellerService.addSeller(s);
                //sellerService.addSeller(s);
                context.status(201);
                context.json("Seller added:\n" + newSeller);
            }catch(JsonProcessingException e){
                context.status(400);
            }catch (SellerException e){
                context.result(e.getMessage());
                context.status(400);}
        });
        //Post new Product
        api.post("product", context -> {
            try {
                ObjectMapper om = new ObjectMapper();
                Product product = om.readValue(context.body(), Product.class);
                // Associate the existing seller with the product
                // product.setName();
                // Add the product to the database
                Product newProduct = productService.addProduct(product);
                // Return success response
                context.status(201);
                context.json("Product added:\n" + newProduct);
            } catch (JsonProcessingException e) {
                context.status(400);
            } catch (ProductException e) {
                context.result(e.getMessage());
                context.status(400);
            }
        });
        //Retrieve product by ID
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
        //Retrieve seller by ID
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
        //Delete Product by ID
        api.delete("product/{id}", context -> {
            long id = Long.parseLong(context.pathParam("id"));
            Product p = productService.deleteProductById(id);
            //if (p == null){
                context.status(200);
                context.json("Product now removed.");
        });
        //Delete Seller by ID
        api.delete("seller/{id}", context -> {
            long id = Long.parseLong(context.pathParam("id"));
            Seller s = sellerService.deleteSellerById(id);
            context.status(200);
            context.json("Seller now removed.");
        });

        //UPDATE Product by ID
        api.put("product/{id}", ctx -> {
            try{
                ObjectMapper om = new ObjectMapper();
                Product p = om.readValue(ctx.body(), Product.class);
                Product updateProduct = productService.updateProductById(p);
                ctx.status(201);
                ctx.json(updateProduct);
            } catch (JsonProcessingException e) {
                ctx.result(e.getMessage());
                ctx.status(400);
            } catch (ProductException e){
                ctx.result(e.getMessage());
                ctx.status(400);
            }
        });
        return api;
    }
}
