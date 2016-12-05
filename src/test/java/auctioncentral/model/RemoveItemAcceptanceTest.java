package auctioncentral.model;

import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Date;

/**
 * Created by Jon on 12/5/2016.
 */
public class RemoveItemAcceptanceTest {

    private Calendar emptyCalendar;
    private Contact contact1;

    private Item testItem;

    private Auction auction2Weeks1;
    private Auction auctionToday;
    private Auction auctionTomorrow;
    private Auction auctionTwoDays;
    private Auction auction2WeeksAgo;
    private Date date2Weeks;
    @Before
    public void setup(){

        contact1 = new Contact("username1", "name1");
        emptyCalendar = new Calendar();

        java.util.Calendar cthreeDays = Calendar.getJavaCalendar();
        cthreeDays.add(java.util.Calendar.DATE, 3);

        java.util.Calendar ctwoDays = Calendar.getJavaCalendar();
        ctwoDays.add(java.util.Calendar.DATE, 2);
        ctwoDays.add(java.util.Calendar.HOUR, -1);

        java.util.Calendar calendar2Weeks = Calendar.getJavaCalendar();
        calendar2Weeks.add(java.util.Calendar.DATE, 14);
        date2Weeks = calendar2Weeks.getTime();

        auctionToday = new Auction(contact1, new Date(), null, null);
        auction2Weeks1 = new Auction(new Contact("1u", "1n"), date2Weeks, "Co comment", null);

        testItem = new Item("name", ItemCondition.GOOD, ItemSize.SMALL, 50, null, null, null);

        java.util.Calendar twoWeeksAgo = Calendar.getJavaCalendar();
        twoWeeksAgo.setTime(new Date());
        twoWeeksAgo.add(java.util.Calendar.DATE, -14);
        auction2WeeksAgo = new Auction(contact1, twoWeeksAgo.getTime(), "", 5);

        java.util.Calendar tomorrow = Calendar.getJavaCalendar();
        tomorrow.setTime(new Date());
        tomorrow.add(java.util.Calendar.DATE, 1);
        auctionTomorrow = new Auction(contact1, tomorrow.getTime(), "", 5);

        java.util.Calendar twoDays = Calendar.getJavaCalendar();
        twoDays.setTime(new Date());
        twoDays.add(java.util.Calendar.DATE, 2);
        twoDays.add(java.util.Calendar.HOUR, 1);
        auctionTwoDays = new Auction(contact1, twoDays.getTime(), "", 5);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveItemOnNull() {
        auction2Weeks1.removeItem(null);
    }
    /**
     *   BR: no auction may be canceled less than 2 days from the date on which it occurs.
            today - not allowed
            in past - not allowed
            tomorrow - not allowed
            in two days - allowed
            in more than two days - allowed
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveItemToday() {
        auctionToday.addItem(testItem);
        auctionToday.removeItem(testItem);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveItemInPast() {
        auction2WeeksAgo.addItem(testItem);
        auction2WeeksAgo.removeItem(testItem);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveItemTomorrow() {
        auctionTomorrow.addItem(testItem);
        auctionTomorrow.removeItem(testItem);
    }
    @Test
    public void testRemoveItemInTwoDays() {
        auctionTwoDays.addItem(testItem);
        assertTrue(auctionTwoDays.canRemoveItem(testItem));
    }
    @Test
    public void testRemoveItemInTwoWeeks() {
        auction2Weeks1.addItem(testItem);
        assertTrue(auction2Weeks1.canRemoveItem(testItem));
    }


}
