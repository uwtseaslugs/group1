package auctioncentral.model;
import org.junit.*;
import java.util.*;
import static org.junit.Assert.*;

public class AuctionTest {

    private Auction newAuction;
    private Contact contact;
    private Item testItem;

    @Before
    public void setup(){
        contact = new Contact("contactUsername", "contactName");
        newAuction = new Auction(contact,new Date(),"Test Auction",2);
        testItem  = new Item("name", ItemCondition.NEW, ItemSize.SMALL, 50, null, null, null);
    }
    @Test
    public void testNewAuctionNullItemNUmber(){
        assertNotNull(new Auction(contact,new Date(), "Null Item",null));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testNewAuctionNegativeNumberOfItems() {
        new Auction(contact,new Date(), "Negative Items",-3); 
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
    public void testAddItemTwice(){
        newAuction.addItem(testItem);
        newAuction.addItem(testItem);
    }
}
