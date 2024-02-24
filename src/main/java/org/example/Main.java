package org.example;
import io.javalin.Javalin;
import org.example.Controller.ServiceController;
import org.example.DAO.SellerDAO;
import org.example.DAO.ProductDAO;
import org.example.Service.ProductService;
import org.example.Service.SellerService;
import org.example.Util.ConnectionSingleton;
import java.sql.Connection;

public class Main {

    //public static Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        //For DAO Services
        Connection conn = ConnectionSingleton.getConnection();

        //2.23 Seller DAO
        SellerDAO sellerDAO = new SellerDAO(conn);
        ProductDAO productDAO = new ProductDAO(conn);

        //instantiate + inject all dependencies-
        SellerService sellerService = new SellerService(sellerDAO); //2.23 (sellerDAO)
        ProductService productService = new ProductService(sellerService, productDAO);
        ServiceController serviceController = new ServiceController(sellerService, productService);

        Javalin api = serviceController.getAPI();

        api.start(9004); //increment from previous iteration 9003

    }
}