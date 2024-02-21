package org.example;
import io.javalin.Javalin;
import org.example.Controller.ProductController;
import org.example.Service.ProductService;
import org.example.Service.SellerService;
import org.example.Util.ConnectionSingleton;
import java.sql.Connection;

public class Main {

    //public static Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        //For DAO Services
        Connection connection = ConnectionSingleton.getConnection();

        //instantiate + inject all dependencies-
        SellerService sellerService = new SellerService();
        ProductService productService = new ProductService(sellerService);
        ProductController ProductController = new ProductController(sellerService, productService);
        Javalin api = ProductController.getAPI();
        api.start(9004); //increment from previous iteration 9003

    }
}