package org.example.Service;
import org.example.DAO.ProductDAO;
import org.example.Exception.ProductException;
import org.example.Exception.ProductNotFoundException;
import org.example.Model.Product;

import java.util.ArrayList;
import java.util.List;

//SELLER SERVICE to be USED by PRODUCT CONTROLLER
public class ProductService {

    ProductDAO productDAO;

    SellerService sellerService; //imported so we can use the instance of sellerService

    //Make a new ArrayList of products to interact with + import the instance of sellerService
    public ProductService(SellerService sellerService, ProductDAO productDAO) {
        this.productDAO = productDAO;
        this.sellerService = sellerService;
        this.productList = new ArrayList<>();
    }

    List<Product> productList; //Create new list of Products (productList)

    public ProductService() {

    }

    //Method to return the list of products in this instance of ProductService
    public List<Product> getProductList() {
        List<Product> productList = productDAO.getAllProduct(); //2.24 DAO code addition
        return productList; //in DAO demo 'return null;' (this.productList to revert)
    }

    //2.24 for ProductDAO
    public void saveProduct(Product p){
        productDAO.insertProduct(p);
    }

    //////////////////CRUD FOR PRODUCT LIST//////////////////
    //ADD PRODUCT P to LIST
    public Product addProduct(Product p) throws ProductException {
        if (p.getBrand() == null || p.getModel() == null || p.getPrice() <= 0 || p.getName() == null)
        {
            throw new ProductException("Brand, Model, Price, and Name fields can't be null.");
        }
        String sName = p.getName().strip();
        if (!sellerService.nameReview(sName)){
            throw new ProductException("Please add this seller first.");
        }
        long id = (long) (Math.random() * Long.MAX_VALUE);
        //Set ID to product
        p.setId(id);
        //Add product to Array
        productList.add(p);
        //return product
        return p;
    }

    //2.24 code for DAO
    public Product getProductById(int id) throws ProductNotFoundException {
        Product p = productDAO.getProductById(id);
        if (p == null) {
            throw new ProductNotFoundException("Product ID was not found.");
        } else {
            return p;
        }
    }

    //RETURN PRODUCT p by id
    public Product getProductById(Long id) {
        for (int i = 0; i < productList.size(); i++) {
            Product currentProduct = productList.get(i);
            if (productList.get(i).getId() == id) {
                return currentProduct;
            }
        }
        return null;
    }

    //DELETE PRODUCT  in productList
    public Product deleteProductById(Long id) {
        for (int i = 0; i < productList.size(); i++) {
            //Product currentProduct = productList.get(i);
            if (productList.get(i).getId() == id) {
                productList.remove(i);
            }
        }
        return null;
    }

    //UPDATE PRODUCT P by ID
    public Product updateProductById(Product p) throws ProductException {
        if (!sellerService.nameReview(p.name.strip())){
            throw new ProductException("Please add this seller first.");
        }
        //productInputCheck if logic pending
        Product currentProduct = getProductById(p.getId());
        currentProduct.setBrand(p.brand);
        currentProduct.setModel(p.model);
        currentProduct.setPrice(p.price);
        currentProduct.setName(p.name.strip());
        return p;
    }

}
