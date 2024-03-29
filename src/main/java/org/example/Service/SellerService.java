package org.example.Service;
import org.example.DAO.SellerDAO;
import org.example.Exception.SellerException;
import org.example.Exception.SellerNotFoundException;
import org.example.Model.Seller;
import java.util.ArrayList;
import java.util.List;

public class SellerService {

    SellerDAO sellerDAO;

    //Make a new ArrayList of sellers to CRUD with
    public SellerService(SellerDAO sellerDAO){
        this.sellerDAO = sellerDAO;
        this.sellerList = new ArrayList<>();
    }

    List<Seller> sellerList; //Create new list of Sellers (sellerList)
    //unused - public static Set<String> sellerList = new HashSet<>();

    //Return all Sellers in the ArrayList
    public List<Seller> getSellerList(){
        List<Seller> sellerList = sellerDAO.getAllSeller(); //2.23 DAO code addition
        return sellerList; //in DAO demo 'return null;' (this.sellerList to revert)
    }

    //2.23 for SellerDAO
    public void saveSeller(Seller s){
        sellerDAO.insertSeller(s);
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

    //2.23 code for DAO
    public Seller getSellerById(int id) throws SellerNotFoundException {
        Seller s = sellerDAO.getSellerById(id);
        if (s == null){
            throw new SellerNotFoundException("Seller ID was not found.");
        }else{
            return s;
        }
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
