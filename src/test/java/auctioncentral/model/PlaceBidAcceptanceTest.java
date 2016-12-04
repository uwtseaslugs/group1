package auctioncentral.model;
import org.junit.*;

import java.math.BigDecimal;
import java.util.*;
import static org.junit.Assert.*;
/**
 *
 * As a bidder, I want to bid for an item in an auction.
    Additional test: Auction date
        Auction is on current day or in the past -- Not Allowed
        Auction is in the future -- Allowed
 */
public class PlaceBidAcceptanceTest {
    private ICalendar emptyCalendar;
    private Item item50MinBid;
    private Bidder testBidder;
    private User testUser;
    private Contact contact;


    @Before
    public void setup() {
        emptyCalendar = new Calendar();
        item50MinBid = new Item("name", ItemCondition.GOOD, ItemSize.SMALL, 50, null, null, null);
        testBidder = new Bidder("username1", "name1");
        contact = new Contact("username2","name2");


    }
    /*
    Business rule: The user must be registered
        Unregistered user tries to place a bid -- Not Allowed
        Registered user tries to place a bid -- Allowed
     */
    @Test(expected = IllegalArgumentException.class)
    public void testPlaceBidAsUnregistered() {
        item50MinBid.placeBid((Bidder)testUser, new BigDecimal("65"));
    }
    @Test
    public void testPlaceBidAsRegistered() {
        item50MinBid.placeBid(testBidder, new BigDecimal("60"));
        assertEquals(item50MinBid.getBid(testBidder), new BigDecimal("60"));
    }
    /*
        Business rule: No Auction Central staff person or contact person for the non-profit associated with the auction may make a bid
        User is neither an Auction Central staff person or a contact person for the non-profit associated with this auction -- Allowed
            Our model completely doesn't allow for any other users other than bidders to input a bid or a Incompatible types
            error will happen and our UI doesn't have an option for a staff or a contact to bid.
        User is an Auction Central staff person -- Not Allowed
        User is a contact person for the non-profit associated with this auction -- Not Allowed
     */
    @Test
    public void testPlaceBidAsBidder() {
        item50MinBid.placeBid(testBidder, new BigDecimal("71"));
        assertEquals(item50MinBid.getBid(testBidder), new BigDecimal("71"));
    }
   /* Additional test: Previous bids for this bidder
        Bidder has already placed a bid on the item and tries to bid again -- Not Allowed
        Bidder has not yet placed a bid on the item and tries to place a bid -- Allowed
    */

   @Test(expected = IllegalArgumentException.class)
   public void testPlaceBidTwice() {
       item50MinBid.placeBid(testBidder, new BigDecimal("60"));
       item50MinBid.placeBid(testBidder, new BigDecimal("65"));
   }
   /*
    Additional test: Valid price
        Bidder places a bid for $0 -- Not Allowed
        Bidder places a bid for a negative price -- Not Allowed
        Bidder places a bid greater than the minimum acceptable bid -- Allowed
        Bidder places a bid equal to the minimum acceptable bid -- Allowed
        Bidder places a bid for a positive price less than the minimum acceptable bid -- Not Allowed
    */
   @Test(expected = IllegalArgumentException.class)
   public void testPlaceBidForZero() {
       item50MinBid.placeBid(testBidder, new BigDecimal("0"));
   }
   @Test(expected = IllegalArgumentException.class)
    public void testPlaceBidForNegative() {
        item50MinBid.placeBid(testBidder, new BigDecimal("-50"));
    }
    @Test
    public void testPlaceBidForEqualToMin() {
        item50MinBid.placeBid(testBidder, new BigDecimal("50"));
        assertEquals(item50MinBid.getBid(testBidder), new BigDecimal("50"));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testPlaceBidForPositiveLessThanMin() {
        item50MinBid.placeBid(testBidder, new BigDecimal("40"));
    }
    /*
        Additional test: Auction date
        Auction is on current day or in the past -- Not Allowed
        Auction is in the future -- Allowed

        .......
     */


}
