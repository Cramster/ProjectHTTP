import org.example.DAO.ProductDAO;
import org.example.DAO.SellerDAO;
import org.example.Exception.ProductException;
import org.example.Exception.SellerException;
import org.example.Model.Product;
import org.example.Model.Seller;
import org.example.Service.ProductService;
import org.example.Service.SellerService;
import org.example.DAO.SellerDAO;
import org.example.DAO.ProductDAO;
import org.example.Controller.ServiceController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.example.Util.ConnectionSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.example.Util.ConnectionSingleton.conn;
import static org.junit.Assert.assertNotNull;

public class HTTPTest {

    ProductService productService;
    SellerService sellerService;
    ProductDAO productDAO;
    SellerDAO sellerDAO;

/*
    @Before //Reset test database
    public void resetTestDb{
        resetTestDatabase;
    }
*/

    @Before //Reset product service prior to each test
    public void setUpProductService() {
        productService = new ProductService();
    }

    /*
    @Before //Reset product service prior to each test
    public void setUpSellerService() {
        sellerService = new SellerService();
    }
*/
    
    @Test //Initial test for empty product list
    public void productServiceEmptyAtStart() {
        List<Product> productList = productService.getProductList();
        Assert.assertTrue(productList.isEmpty());
    }

    @Test //Initial test for empty seller list
    public void sellerServiceEmptyAtStart() {
        List<Seller> sellerList = sellerService.getSellerList();
        Assert.assertTrue(sellerList.isEmpty());
    }

    @Test //Adding a product
    public void productServiceAddProduct() {
        //Arrange
        String brand = "brand";
        String model = "model";
        double price = 199.99;
        String name = "name";
        int id = 5;
        //Act
        try {
            productService.addProduct(brand, model, price, name, id);
        } catch (ProductException e) {
            e.printStackTrace();
            Assert.fail("product exception thrown");
        }
        //Assert
        List<Product> products = productService.getProductList();
        Product actual = products.get(0);
        Assert.assertEquals(brand, actual.getBrand());
        Assert.assertEquals(model, actual.getModel());
        Assert.assertEquals(Optional.of(price), actual.getPrice());
        Assert.assertEquals(name, actual.getName());
        Assert.assertEquals(id, actual.getId());
    }

    @Test //Adding a seller
    public void sellerServiceAddSeller() {
        //Arrange
        String name = "name";
        int id = 5;
        //Act
        try {
            sellerService.addSeller(name, id);
        } catch (ProductException e) {
            e.printStackTrace();
            Assert.fail("product exception thrown");
        }
        //Assert
        List<Seller> sellers = sellerService.getSellerList();
        Seller actual = sellers.get(0);
        Assert.assertEquals(name, actual.getName());
        Assert.assertEquals(id, actual.getId());
    }

    @Test //Product Invalid Price
    public void addProductInvalidPrice() {
        String brand = "brand";
        String model = "model";
        double price = -1;
        String name = "name";
        int id = 5;
        try {
            productService.addProduct(brand, model, price, name, id);
            Assert.fail();
        } catch (ProductException e) {
        }
    }

    @Test //Seller Invalid Name
    public void addSellerInvalidName() {
        int id = 5;
        String name = "";
        try {
            sellerService.addSeller(name, id);
            Assert.fail();
        } catch (SellerException e) {
        }
    }

    @Test //Delete Product
    public void productServiceDeleteProductById() {
        String brand = "title";
        String model = "model";
        double price = -1;
        String name = "name";
        int id = 5;
        try {
            productService.deleteProductById(brand, model, price, name, id);
            Assert.assertTrue();
        } catch (ProductException e) {
        }
    }

    @Test //Delete Seller
    public void sellerServiceDeleteSellerById() {
        String name = "name";
        int id = 5;
        try {
            sellerService.deleteSellerById(name, id);
            Assert.assertTrue();
        } catch (ProductException e) {
        }
    }

    @Test //verify product
    public void testProductMethod() {
        Product p = new Product();
        p.equals(new Product());
        p.hashCode();
        p.toString();
        assertNotNull(p.toString());
    }

    @Test //verify seller
    public void testSellerMethod() {
        Seller s = new Seller();
        s.equals(new Seller());
        s.hashCode();
        s.toString();
        assertNotNull(s.toString());
    }

    @Test //insert DAO product
    public void insertDAOProduct(){
        Product product = new Product();
        String brand = "brand";
        String model = "model";
        double price = -1;
        String name = "name";
        int id = 5;
        try{
            PreparedStatement ps = conn.prepareStatement("insert into " +
                    "Product (product_id, brand, model, price, name) values (?, ?, ?, ?, ?)");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    public void testGetProductByIdDao(){
        ProductDAO product = new ProductDAO();
        //exception scenario
        Product product = product.getProductById(1);
        Assert.assertNull(product);
        //standard scenario
        product = productDAO.getProductById(2);
        Assert.assertNotNull(product);
    }

    @Test
    public void testGetAllProductDAO(){
        ProductDAO productDAO = new ProductDAO();
        ArrayList<Product> productList = productDAO.getAllProduct();
        Assert.assertNotNull(productList);
        Assert.assertEquals(6, productList.size());
    }

}