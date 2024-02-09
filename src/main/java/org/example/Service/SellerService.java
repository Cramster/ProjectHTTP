package org.example.Service;

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

    public void addSeller(Seller s){
        sellerList.add(s);
    }

}
