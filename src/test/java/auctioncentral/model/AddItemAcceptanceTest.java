/*
    Deliverable 2
    TCSS 360
    Created by: Sea Slugs

    Test all the logic in the auction class.
 */
package auctioncentral.model;
import org.junit.*;

import java.math.BigDecimal;
import java.util.*;
import static org.junit.Assert.*;

/*
    As the contact person for the non-profit organization associated with an auction,
    I want to add an inventory item for this auction.
 */
public class AddItemAcceptanceTest {

    private Auction newAuction;
    private Contact contact;
    private Item testItem;
    private Item item50MinBid;
    private Bidder testBidder;

    @Before
    public void setup(){
        contact = new Contact("contactUsername", "contactName");
        newAuction = new Auction(contact,new Date(),"Test Auction",2);
        testItem  = new Item("name", ItemCondition.NEW, ItemSize.SMALL, 50, null, null, null);
        item50MinBid = new Item("name", ItemCondition.GOOD, ItemSize.SMALL, 50, null, null, null);
        testBidder = new Bidder("username1", "name1");
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

    @Test
    public void testAddItemFirst() {
        newAuction.addItem(testItem);
        assertTrue(newAuction.getItems().contains(testItem));
    }

    /*
        Business rule: all required fields must be specified at the time an inventory item is added.
        These fields are: Item Name (string), Condition (one of: acceptable, good, very good, like new, new),
        Size (one of: small (no dimension is greater than one foot),
         medium (at least one dimension is greater than one foot but no dimension is greater than three feet),
         large (at least one dimension is greater than three feet) and minimum acceptable bid (positive integer).
         Optional (non-required) fields are: Donor Name (string), Item Description for Bidders (string),
         and Comment for Auction Central staff (string).
            One of the required fields is not specified -- Not Allowed
            All of the required fields are specified -- Allowed
     */
    @Test
    public void testNewItemWithPositiveMinimumBid() {
        assertEquals(item50MinBid.getMinimumBid(), 50);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNewItemWithNegativeMinimumBid() {
        new Item("name", ItemCondition.GOOD, ItemSize.SMALL, -50, null, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNewItemWithZeroMinimumBid() {
        new Item("name", ItemCondition.GOOD, ItemSize.SMALL, 0, null, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNewItemWithMissingRequired() {
        new Item("name", null, ItemSize.SMALL, 50, null, null, null);
    }

    @Test
    public void testNewItemWithAllRequired() {
        assertNotNull(item50MinBid.getName());
        assertNotNull(item50MinBid.getMinimumBid());
        assertNotNull(item50MinBid.getCondition());
        assertNotNull(item50MinBid.getSize());
    }
}
