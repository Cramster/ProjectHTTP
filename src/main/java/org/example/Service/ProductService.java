package org.example.Service;
import org.example.Exception.ProductException;
import org.example.Main;
import org.example.Model.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductService {

    SellerService sellerService;
    List<Product> productList;

    public ProductService(SellerService sellerService) {
        this.sellerService = sellerService;
        productList = new ArrayList<>();
    }

    public List<Product> getProductList() {
        return productList;
    }

    public Product addProduct(Product p) throws ProductException {
        Main.log.info("Attempting to add Product: " + p);
        if(p.getMake() == null || p.getModel() == null){
            throw new ProductException("Make and Model fields must be non-null.");
        }
        long id = (long) (Math.random() * Long.MAX_VALUE);
        p.setId(id);
        productList.add(p);
        return p;
    }

    public Product getProductById(Long id){
        for(int i = 0; i < productList.size(); i++){
            Product currentProduct = productList.get(i);
            if(productList.get(i).getId() == id){
                return currentProduct;
            }
        }
        return null;
    }

    //ProductService method to delete posted product
    public Product deleteProductById(Long id) {
        for (int i = 0; i < productList.size(); i++) {
            Product currentProduct = productList.get(i);
            if (productList.get(i).getId() == id) {
                productList.remove(i);
            }
        }
        return null;
    }

/* from 2-6 car service example, above being from painter/author example
    public ProductService(){
        productList = new ArrayList<>();
    }
    public List<Product> getAllProducts(){
        return productList;
    }
    public void insertProduct(Product product){
        productList.add(product);
    }
 */


}