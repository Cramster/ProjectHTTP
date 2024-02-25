import org.example.DAO.ProductDAO;
import org.example.DAO.SellerDAO;
import org.example.Model.Product;
import org.example.Service.ProductService;
import org.example.Service.SellerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class ProductServiceTest {

    ProductService productService;

    @Before //Reset product service prior to each test
    public void setUp(){
        productService = new ProductService();
    }

    @Test //Product service should contain zero products when first created
    public void productServiceEmptyAtStart(){
        List<Product> productList = productService.getProductList();
        Assert.assertTrue(productList.isEmpty());
    }

}
