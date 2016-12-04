package auctioncentral.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Date;

/*
    As a contact person for a non-profit organization, I want to cancel an auction request.
 */
public class CancelAuctionAcceptanceTest {

    private Calendar emptyCalendar;
    private Contact contact1;

    private Auction auction2Weeks1;
    private Auction auctionToday;
    private Auction auctionTomorrow;
    private Auction auctionTwoDays;
    private Auction auction2WeeksAgo;
    private Date date2Weeks;

    @Before
    public void setup() {
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

    /*
        Auction is null - not allowed
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCancelAuctionOnNull() {
        emptyCalendar.cancelAuction(null);
    }

    /*
        Auction has not been added - not allowed
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCancelAuctionOnUnadded() {
        Auction unadded = new Auction(contact1, date2Weeks, "", null);
        emptyCalendar.cancelAuction(unadded);
    }

    /*
        BR: no auction may be canceled less than 2 days from the date on which it occurs.
            today - not allowed
            in past - not allowed
            tomorrow - not allowed
            in two days - allowed
            in more than two days - allowed
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCancelAuctionOnToday() {
        emptyCalendar.faddAuction(auctionToday);
        emptyCalendar.cancelAuction(auctionToday);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCancelAuctionInPast() {
        emptyCalendar.faddAuction(auction2WeeksAgo);
        emptyCalendar.cancelAuction(auction2WeeksAgo);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCancelAuctionTomorrow() {
        emptyCalendar.faddAuction(auctionTomorrow);
        emptyCalendar.cancelAuction(auctionTomorrow);
    }

    @Test
    public void testCancelAuctionInTwoDays() {
        emptyCalendar.faddAuction(auctionTwoDays);
        assertTrue(emptyCalendar.containsAuction(auctionTwoDays));
        emptyCalendar.cancelAuction(auctionTwoDays);
        assertFalse(emptyCalendar.containsAuction(auctionTwoDays));
    }

    @Test
    public void testCancelAuctionInTwoWeeks() {
        emptyCalendar.faddAuction(auction2Weeks1);
        assertTrue(emptyCalendar.containsAuction(auction2Weeks1));
        emptyCalendar.cancelAuction(auction2Weeks1);
        assertFalse(emptyCalendar.containsAuction(auction2Weeks1));
    }
}
