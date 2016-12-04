package auctioncentral.model;

import org.junit.*;
import java.util.*;
import static org.junit.Assert.*;

/**
 * Created by Jon on 12/4/2016.
 * As the contact person for the non-profit organization associated with an auction, I want to add an inventory item for this auction.

    Business rule: all required fields must be specified at the time an inventory item is added. These fields are: Item Name (string), Condition (one of: acceptable, good, very good, like new, new), Size (one of: small (no dimension is greater than one foot), medium (at least one dimension is greater than one foot but no dimension is greater than three feet), large (at least one dimension is greater than three feet) and minimum acceptable bid (positive integer). Optional (non-required) fields are: Donor Name (string), Item Description for Bidders (string), and Comment for Auction Central staff (string).
        One of the required fields is not specified -- Not Allowed
        All of the required fields are specified -- Allowed

 */
public class AddInventoryAcceptanceTest {

    private Auction newAuction;
    private Contact contact1;
    private Item testItem;
    @Before
    public void setup(){
        contact1 = new Contact("contactUsername1", "contactName1");
        newAuction = new Auction(contact1,new Date(),"Test Auction",2);
        testItem  = new Item("name", ItemCondition.NEW, ItemSize.SMALL, 50, null, null, null);
    }
    /*
        Business rule: the same item cannot be entered twice
        Prior to adding the inventory item, the inventory item is not in the system -- Allowed
        Prior to adding the inventory item, the inventory item is in the system -- Not Allowed
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddItemTwice(){
        newAuction.addItem(testItem);
        newAuction.addItem(testItem);
    }
    /*
        Business rule: all required fields must be specified at the time an inventory item is added. These fields are: Item Name (string), Condition (one of: acceptable, good, very good, like new, new), Size (one of: small (no dimension is greater than one foot), medium (at least one dimension is greater than one foot but no dimension is greater than three feet), large (at least one dimension is greater than three feet) and minimum acceptable bid (positive integer). Optional (non-required) fields are: Donor Name (string), Item Description for Bidders (string), and Comment for Auction Central staff (string).
        One of the required fields is not specified -- Not Allowed
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNewItemMissingRequired() {
        new Item("name", null, ItemSize.SMALL, 50, null, null, null);
    }
}
