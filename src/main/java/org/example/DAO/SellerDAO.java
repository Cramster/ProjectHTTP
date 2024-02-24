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
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return sellerResults;
    }

    public void insertSeller(Seller s){
        try{
            PreparedStatement ps = conn.prepareStatement("insert into " +
                    "Seller (seller_id, name) values (?, ?)");
            ps.setLong(1, s.getId()); //ps.setInt for artist version
            ps.setString(2, s.getName());
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    //2.23 DAO code
    public Seller getSellerById(int id){
        try{
            PreparedStatement ps = conn.prepareStatement(
                    "select * from seller where seller_id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                int sellerId = rs.getInt("seller_id");
                String name = rs.getString("name");
                Seller s = new Seller(sellerId, name);
                return s;
            }else {
                return null;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
