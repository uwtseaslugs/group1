package auctioncentral.model;

import org.junit.*;

import java.math.*;

import static org.junit.Assert.*;

public class ItemTest {

    private Item item50MinBid;
    private Bidder testBidder;

    @Before
    public void setup() {
        item50MinBid = new Item("name", ItemCondition.GOOD, ItemSize.SMALL, 50, null, null, null);
        testBidder = new Bidder("username1", "name1");
    }

    @Test
    public void testNewItemPositiveMinimumBid() {
        assertEquals(item50MinBid.getMinimumBid(), 50);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNewItemNegativeMinimumBid() {
        new Item("name", ItemCondition.GOOD, ItemSize.SMALL, -50, null, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNewItemZeroMinimumBid() {
        new Item("name", ItemCondition.GOOD, ItemSize.SMALL, 0, null, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlaceBidZero() {
        item50MinBid.placeBid(testBidder, new BigDecimal("0"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlaceBidNegative() {
        item50MinBid.placeBid(testBidder, new BigDecimal("-50"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlaceBidPositiveLessThanMin() {
        item50MinBid.placeBid(testBidder, new BigDecimal("40"));
    }

    @Test
    public void testPlaceBidGreaterThanMin() {
        item50MinBid.placeBid(testBidder, new BigDecimal("60"));
        assertEquals(item50MinBid.getBid(testBidder), new BigDecimal("60"));
    }

    @Test
    public void testPlaceBidEqualTonMin() {
        item50MinBid.placeBid(testBidder, new BigDecimal("50"));
        assertEquals(item50MinBid.getBid(testBidder), new BigDecimal("50"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlaceBidTwice() {
        item50MinBid.placeBid(testBidder, new BigDecimal("60"));
        item50MinBid.placeBid(testBidder, new BigDecimal("65"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNewItemMissingRequired() {
        new Item("name", null, ItemSize.SMALL, 50, null, null, null);
    }

    @Test
    public void testNewItemAllRequired() {
        assertNotNull(item50MinBid.getName());
        assertNotNull(item50MinBid.getMinimumBid());
        assertNotNull(item50MinBid.getCondition());
        assertNotNull(item50MinBid.getSize());
    }
}
