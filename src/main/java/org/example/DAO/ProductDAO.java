package org.example.DAO;
import org.example.Model.Product;
import org.example.Model.Seller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//2.24 adding ProductDAO
public class ProductDAO {

    Connection conn;

    public ProductDAO(Connection conn){
        this.conn = conn;
    }

    public List<Product> getAllProduct(){
        List<Product> productResults = new ArrayList<>();
        try{
            PreparedStatement ps = conn.prepareStatement("select * from Product");
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("product_id");
                String brand = resultSet.getString("brand");
                String model = resultSet.getString("model");
                double price = resultSet.getDouble("price");
                String name = resultSet.getString("name");
                Product p = new Product(brand, model, price, id, name);
                productResults.add(p);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return productResults;
    }

    public void insertProduct(Product p){
        try{
            PreparedStatement ps = conn.prepareStatement("insert into " +
                    "Product (product_id, brand, model, price, name) values (?, ?, ?, ?, ?)");
            ps.setLong(1, p.getId()); //ps.setInt for artist version
            ps.setString(2, p.getBrand());
            ps.setString(3, p.getModel());
            ps.setDouble(4, p.getPrice());
            ps.setString(5, p.getName());
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    //2.24 DAO code
    public Product getProductById(int id){
        try{
            PreparedStatement ps = conn.prepareStatement(
                    "select * from product where product_id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                int productId = rs.getInt("product_id");
                String brand = rs.getString("brand");
                String model = rs.getString("model");
                double price =  rs.getDouble("price");
                String name = rs.getString("name");
                Product p = new Product(brand, model, price, productId, name);
                return p;
            }else {
                return null;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
