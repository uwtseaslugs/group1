package auctioncentral.model;
import org.junit.*;
import java.util.*;
public class AuctionTest {
    private Auction newAuction;

    public void setup(){
        Contact contact = new Contact("contactUsername", "contactName");
        newAuction = new Auction(contact,new Date(),"Test Auction",2);
        Item testItem  = new Item("name", ItemCondition.NEW, ItemSize.SMALL, 50, null, null, null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testNewAuctionNullItemNUmber(){
        new Auction(contact,new Date(), "Null Item",null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testNewAuctionNullContact(){
        new Auction(null,new Date(), "Null contact",1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNewAuctionNullDate(){
        new Auction(contact,null, "Null date",1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNewAuctionNullDate(){
        newAuction.addItem(testItem);
        newAuction.addItem(testItem);
    }


}
