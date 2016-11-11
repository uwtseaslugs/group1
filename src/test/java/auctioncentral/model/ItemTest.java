package auctioncentral.model;

import org.junit.*;
import static org.junit.Assert.*;

public class ItemTest {

    @Test
    public void testNewItemPositiveMinimumBid() {
        Item item = new Item("name", ItemCondition.GOOD, ItemSize.SMALL, 50, null, null, null);
        assertEquals(item.getMinimumBid(), 50);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNewItemNegativeMinimumBid() {
        Item item = new Item("name", ItemCondition.GOOD, ItemSize.SMALL, -50, null, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNewItemZeroMinimumBid() {
        Item item = new Item("name", ItemCondition.GOOD, ItemSize.SMALL, 0, null, null, null);
    }
}
