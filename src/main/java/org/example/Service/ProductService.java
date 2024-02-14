package org.example.Service;
import org.example.Exception.ProductException;
import org.example.Model.Product;
import java.util.ArrayList;
import java.util.List;

//SELLER SERVICE to be USED by PRODUCT CONTROLLER
public class ProductService {

    SellerService sellerService; //imported so we can use the instance of sellerService
    List<Product> productList; //Create new list of Products (productList)

    //Make a new ArrayList of products to interact with + import the instance of sellerService
    public ProductService(SellerService sellerService) {
        this.productList = new ArrayList<>();
        this.sellerService = sellerService;
    }

    //Method to return the list of products in this instance of ProductService
    public List<Product> getProductList() {
        return productList;
    }

    //////////////////CRUD FOR PRODUCT LIST//////////////////
    //ADD PRODUCT P to LIST
    public Product addProduct(Product p) throws ProductException {
        if (p.getBrand() == null || p.getModel() == null || p.getPrice() <= 0 || p.getName() == null) {
            throw new ProductException("Brand, Model, Price, and Name fields must be non-null.");
        }
        //check for existing seller?
        long id = (long) (Math.random() * Long.MAX_VALUE);
        //Set ID to product
        p.setId(id);
        //Add product to Array
        productList.add(p);
        //return product
        return p;
    }

    //GET PRODUCT P (by ID) from LIST
    public Product getProductById(Long id) {
        for (int i = 0; i < productList.size(); i++) {
            Product currentProduct = productList.get(i);
            if (productList.get(i).getId() == id) {
                return currentProduct;
            }
        }
        return null;
    }

    //DELETE PRODUCT P in LIST
    public Product deleteProductById(Long id) {
        for (int i = 0; i < productList.size(); i++) {
            Product currentProduct = productList.get(i);
            if (productList.get(i).getId() == id) {
                productList.remove(i);
            }
        }
        return null;
    }

    //UPDATE PRODUCT P in LIST
    public Product updateProductInList(Product p){
        return p;
    }

}
