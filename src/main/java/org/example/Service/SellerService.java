package org.example.Service;
import org.example.DAO.SellerDAO;
import org.example.Exception.ProductException;
import org.example.Exception.SellerException;
import org.example.Main;
import org.example.Model.Product;
import org.example.Model.Seller;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SellerService {

    SellerDAO sellerDAO;

    //Make a new ArrayList of sellers to CRUD with
    public SellerService(SellerDAO sellerDAO){
        this.sellerDAO = sellerDAO;
        this.sellerList = new ArrayList<>();
    }

    //Create new list of Sellers (sellerList)
    List<Seller> sellerList;
    //public static Set<String> sellerList = new HashSet<>();

    //2.23 DAO
    public void saveSeller(){

    }

    //Return all Sellers in the ArrayList
    public List<Seller> getSellerList(){
        List<Seller> sellerList = sellerDAO.getAllSeller(); //2.23 DAO code addition
        //System.out.println(sellerList);
        return sellerList; //in DAO demo 'return null;' (this.sellerList to revert)
    }

    //////////////////CRUD FOR SELLER LIST//////////////////
    //ADD SELLER s to sellerList
    public Seller addSeller(Seller s) throws SellerException {
        //add logic to check for existing seller
        String sName = s.getName().strip();
        if (nameReview(sName)){
            throw new SellerException("Seller name already exists, try again.");
        }
        if (s.getName() == null || s.getName().isEmpty()){
            throw new SellerException("Please enter a valid name for the seller.");
        }
        long id = (long) (Math.random() * Long.MAX_VALUE);
        s.setId(id);
        s.setName(sName);
        sellerList.add(s);
        return s;
    }

    //RETURN SELLER s by id
    public Seller getSellerById(Long id){
        for(int i = 0; i < sellerList.size(); i++){
            Seller currentSeller = sellerList.get(i);
            if(sellerList.get(i).getId() == id){
                return currentSeller;
            }
        }
        return null;
    }

    //DELETE SELLER s in sellerList
    public Seller deleteSellerById(Long id) {
        //Check the list for currentSeller
        for (int i = 0; i < sellerList.size(); i++) {
            Seller currentSeller = sellerList.get(i);
            //if currentSeller matches id of any existing in list
            if (sellerList.get(i).getId() == id) {
                //remove that currentSeller (i)
                sellerList.remove(i);
            }
        }
        return null;
    }

    //Check if seller being added is already in seller list
    public boolean nameReview(String name){
        //List<Seller> sellerList = new ArrayList<>(getSellerList());
        for (int i = 0; i < sellerList.size(); i++){
            //Seller currentSeller = sellerList.get(i);
            if (sellerList.get(i).getName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }

    //RETURN SELLER s by name
    public Seller getSellerByName(String name) {
        for (int i = 0; i < sellerList.size(); i++){
            Seller currentSeller = sellerList.get(i);
            if(sellerList.get(i).getName().equals(name))
                return currentSeller;
        }
        return null;
    }

    //NOT REQUIRED
    public Seller updateSellerById(Seller s){
        return s;
    }

}
