package org.example.DAO;
import org.example.Model.Seller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//2.23 adding SellerDAO
public class SellerDAO {

    Connection conn;

    public SellerDAO(Connection conn){
        this.conn = conn;
    }

    public List<Seller> getAllSeller(){
        List<Seller> sellerResults = new ArrayList<>();
        try{
            PreparedStatement ps = conn.prepareStatement("select * from Seller");
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                int sellerId = resultSet.getInt("seller_id");
                String sellerName = resultSet.getString("name");
                Seller s = new Seller(sellerId, sellerName);
                sellerResults.add(s);
                //System.out.println(s);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return sellerResults;
    }

    public void insertSeller(){
        //
    }
}
