package auctioncentral.model;

import java.util.*;
public class Auction {
    private Date date;
    private String nameOfAuction;
    private String Comments;
    private List<Item> Items;



    public Auction(String nameOfAuction, Date date, String Time, String Comments){
        this.nameOfAuction = nameOfAuction;
        this.date = date;
        this.Comments = Comments;

    }
    
    public String getNameOfAuction(){return nameOfAuction;}

    public Date getDate(){return date;}


    public List<Item> getItems(){return Items;}

    public void addAuction(Item newItem){
        Items.add(newItem);
    }

}

