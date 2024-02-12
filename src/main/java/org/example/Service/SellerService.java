package org.example.Service;
import org.example.Exception.ProductException;
import org.example.Exception.SellerException;
import org.example.Main;
import org.example.Model.Product;
import org.example.Model.Seller;
import java.util.ArrayList;
import java.util.List;

public class SellerService {

    List<Seller> sellerList;

    public SellerService(){
        this.sellerList = new ArrayList<>();
    }
    public List<Seller> getSellerList(){
        return sellerList;
    }

//Code prior to adding unique ID
//    public void addSeller(Seller s){
//        sellerList.add(s);
//    }

    public Seller addSeller(Seller s) throws SellerException {
        Main.log.info("Attempting to add Seller: " + s);
        long id = (long) (Math.random() * Long.MAX_VALUE);
        s.setId(id);
        sellerList.add(s);
        return s;
    }

    public Seller getSellerById(Long id){
        for(int i = 0; i < sellerList.size(); i++){
            Seller currentSeller = sellerList.get(i);
            if(sellerList.get(i).getId() == id){
                return currentSeller;
            }
        }
        return null;
    }

}
