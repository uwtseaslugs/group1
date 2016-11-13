package auctioncentral.model;

import org.junit.*;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Still need to test:
 * <p>
 * Maximum of one future auction for this non-profit
 * Prior to submitting the auction, the non-profit has no future auctions in the system -- Allowed
 * Prior to submitting the auction, the non-profit has one future auction in the system -- Not Allowed
 * Prior to submitting the auction, the non-profit has more than one future auction in the system -- Not Allowed
 * <p>
 * <p>
 * No auctions within the past year for this non-profit
 * Prior to submitting the auction, the non-profit has one auction in the system that occurred less than one year minus one day ago -- Not Allowed
 * Prior to submitting the auction, the non-profit has one auction that is exactly one year minus one day in the past -- Not Allowed.
 * Prior to submitting the auction, the non-profit has more than one auction in the system that occurred less than one year ago -- Not Allowed
 * Prior to submitting the auction, the non-profit has no auctions in the past year in the system but one auction that is greater than one year in the past -- Allowed.
 * Prior to submitting the auction, the non-profit has no auctions in the past year in the system but one auction that is exactly one year in the past -- Allowed.
 * Prior to submitting the auction, the non-profit has no auctions in the past in the system -- Allowed.
 * <p>
 * Business rule: Maximum of twenty-five upcoming auctions in the system
 * Prior to submitting the auction, there are less than 24 auctions in the system -- Allowed
 * Prior to submitting the auction, there are exactly 24 auctions in the system -- Allowed
 * Prior to submitting the auction, there are exactly 25 auctions in the system -- Not Allowed
 * Prior to submitting the auction, there are greater than 25 auctions in the system -- Not Allowed
 * Business rule: Auction cannot be scheduled for more than one month into the future
 * The scheduled date is exactly one month in the future, i.e. the same day of the month one month from today -- Allowed
 * The scheduled date is less than one month in the future -- Allowed
 * The scheduled date is exactly one month plus one day in the future -- Not Allowed
 * The scheduled date is greater than one month plus one day in the future -- Not Allowed
 * <p>
 * <p>
 * Business rule: auction date is at least one week from the day in which it is being added.
 * The auction date is exactly one week from the day on which it is being added -- Allowed
 * The auction date is greater than one week from the day on which it is being added -- Allowed
 * The auction date is exactly six days from the day on which it is being added -- Not Allowed
 * The auction date is less than six days from the day on which it is being added -- Not Allowed
 * The auction date is the day on which it is being added -- Not Allowed
 * The auction date is in the past -- Not Allowed
 */

public class CalendarTest {
    private ICalendar emptyCalendar;
    private Contact contact1;
    
    private Auction auction2Weeks1;
    private Auction auctionTomorrow;
    private Auction auctionToday;
    private Auction auction2Weeks2;
    private Auction auction2Weeks3;
    private Auction auction25Days;
    private Date date2Weeks;

    private ICalendar calendarFullMinusOne;
    private ICalendar calendarFull;
    
    @Before
    public void setup() {
        contact1 = new Contact("username1", "name1");
        emptyCalendar = new Calendar();
        calendarFull = new Calendar();
        calendarFullMinusOne = new Calendar();

        java.util.Calendar calendar2Weeks = Calendar.getJavaCalendar();
        calendar2Weeks.add(java.util.Calendar.DATE, 14);
        date2Weeks = calendar2Weeks.getTime();

        auctionToday = new Auction(contact1, new Date(), null, null);
        auction2Weeks1 = new Auction(new Contact("1u", "1n"), date2Weeks, "Co comment", null);
        auction2Weeks2 = new Auction(new Contact("2u", "2n"), date2Weeks, "Co comment", null);
        auction2Weeks3 = new Auction(new Contact("3u", "3n"), date2Weeks, "Co comment", null);
        auctionTomorrow = new Auction(new Contact("2u", "2n"), Calendar.addDaysToDate(new Date(), 1), null, null);
        auction25Days = new Auction(new Contact("2u", "2n"), Calendar.addDaysToDate(new Date(), 25), null, null);

        java.util.Calendar c = java.util.Calendar.getInstance();
        c.add(java.util.Calendar.DATE, 8);
        for (int i = 0; i < 12; i++) {
            calendarFullMinusOne.addAuction(new Auction(new Contact("" + i, "" + i), c.getTime(), null, null));
            calendarFull.addAuction(new Auction(new Contact("" + i, "" + i), c.getTime(), null, null));
            calendarFullMinusOne.addAuction(new Auction(new Contact("" + i, "" + i), c.getTime(), null, null));
            calendarFull.addAuction(new Auction(new Contact("" + i, "" + i), c.getTime(), null, null));
            c.add(java.util.Calendar.DATE, 1);
        }
        c.add(java.util.Calendar.DATE, 1);
        calendarFull.addAuction(new Auction(new Contact("last", "last"), c.getTime(), null, null));
    }


    @Test
    public void testCanAddAuctionToEmptyCalendar() {
        assertEquals(true, emptyCalendar.canAddAuction(auction2Weeks1));
        assertEquals(true, emptyCalendar.addAuction(auction2Weeks1));
    }

    @Test
    public void testCannotAddAuctionToFullCalendar() {
        assertEquals(false, calendarFull.canAddAuction(auction25Days));
        assertEquals(false, calendarFull.addAuction(auction25Days));
    }
    @Test
    public void testAdd25thAuction() {
        assertEquals(true, calendarFullMinusOne.canAddAuction(auction25Days));
        assertEquals(true, calendarFullMinusOne.addAuction(auction25Days));
    }

    @Test
    public void testCannotAddAuctionBeforeToday() {
        Auction auctionTmp = new Auction(new Contact("", ""),
                                      Calendar.addDaysToDate(new Date(), -1),
                                      "Co comment", null);
        assertEquals(false, emptyCalendar.canAddAuction(auctionTmp));
        assertEquals(false, emptyCalendar.addAuction(auctionTmp));
    }

    @Test
    public void testCannotAddAuctionToday() {
        assertEquals(false, emptyCalendar.canAddAuction(auctionToday));
        assertEquals(false, emptyCalendar.addAuction(auctionToday));
    }

    @Test
    public void testCanRemoveAuctionWhenExists() {
        emptyCalendar.addAuction(auction2Weeks1);
        assertEquals(true, emptyCalendar.removeAuction(auction2Weeks1));
    }

    @Test
    public void testCannotRemoveAuctionWhenDoesNotExist() {
        assertEquals(false, emptyCalendar.removeAuction(auctionTomorrow));
    }

    @Test
    public void testGetAuctionsPastDateReturnsEmptyWhenCalIsEmpty() {
        assertEquals(Collections.emptyList(), emptyCalendar.getAuctionsPastDate(new Date()));
    }

    @Test
    public void testGetAuctionsPastDateReturnsNonEmptyWhenCalIsNonEmpty() {
        java.util.Calendar c = Calendar.getJavaCalendar();
        c.set(java.util.Calendar.DAY_OF_MONTH, 1);
        assertNotEquals(Collections.emptyList(), calendarFull.getAuctionsPastDate(c.getTime()));
    }

//    @Test
//    public void testGetNumberOfDaysForCurrentMonthIsCorrect() {
//        assertEquals(Calendar.getJavaCalendar().getActualMaximum(java.util.Calendar.DAY_OF_MONTH),
//                     emptyCalendar.getNumberOfDaysForCurrentMonth());
//    }
    @Test
    public void testCanAddOneAuctionInOneDay(){
        assertEquals(true, emptyCalendar.canAddAuction(auction2Weeks1));
        assertEquals(true, emptyCalendar.addAuction(auction2Weeks1));
    }
    @Test
    public void testCanAddTwoAuctionsInOneDay(){
        emptyCalendar.addAuction(auction2Weeks1);
        assertEquals(true, emptyCalendar.canAddAuction(auction2Weeks2));
        assertEquals(true, emptyCalendar.addAuction(auction2Weeks2));
    }
    @Test
    public void testCannotAddMoreThanTwoAuctionsInOneDay(){
        emptyCalendar.addAuction(auction2Weeks1);
        emptyCalendar.addAuction(auction2Weeks2);
        assertEquals(false, emptyCalendar.canAddAuction(auction2Weeks3));
        assertEquals(false, emptyCalendar.addAuction(auction2Weeks3));
    }

    @Test
    public void testAddAuctionInEightDays() {
        java.util.Calendar c = Calendar.getJavaCalendar();
        c.add(java.util.Calendar.DATE, 8);
        assertTrue(emptyCalendar.canAddAuction(new Auction(contact1, c.getTime(), null, null)));
    }

    @Test
    public void testAddAuctionInSevenDays() {
        java.util.Calendar c = Calendar.getJavaCalendar();
        c.add(java.util.Calendar.DATE, 7);
        c.add(java.util.Calendar.HOUR, 1);
        assertTrue(emptyCalendar.canAddAuction(new Auction(contact1, c.getTime(), null, null)));
    }

    @Test
    public void testAddAuctionInSixDays() {
        java.util.Calendar c = Calendar.getJavaCalendar();
        c.add(java.util.Calendar.DATE, 6);
        assertFalse(emptyCalendar.canAddAuction(new Auction(contact1, c.getTime(), null, null)));
    }

    @Test
    public void testAddAuctionTomorrow() {
        java.util.Calendar c = Calendar.getJavaCalendar();
        c.add(java.util.Calendar.DATE, 1);
        assertFalse(emptyCalendar.canAddAuction(new Auction(contact1, c.getTime(), null, null)));
    }

    @Test
    public void testAddAuctionInOneMonth() {
        java.util.Calendar c = Calendar.getJavaCalendar();
        c.add(java.util.Calendar.MONTH, 1);
        assertTrue(emptyCalendar.canAddAuction(new Auction(contact1, c.getTime(), null, null)));
    }

    @Test
    public void testAddAuctionIn25Days() {
        java.util.Calendar c = Calendar.getJavaCalendar();
        c.add(java.util.Calendar.DATE, 25);
        assertTrue(emptyCalendar.canAddAuction(new Auction(contact1, c.getTime(), null, null)));
    }

    @Test
    public void testAddAuctionInOneMonthAndOneDay() {
        java.util.Calendar c = Calendar.getJavaCalendar();
        c.add(java.util.Calendar.DATE, 1);
        c.add(java.util.Calendar.MONTH, 1);
        assertFalse(emptyCalendar.canAddAuction(new Auction(contact1, c.getTime(), null, null)));
    }

    @Test
    public void testAddAuctionInTwoMonths() {
        java.util.Calendar c = Calendar.getJavaCalendar();
        c.add(java.util.Calendar.MONTH, 2);
        assertFalse(emptyCalendar.canAddAuction(new Auction(contact1, c.getTime(), null, null)));
    }

    @Test
    public void testAddAuctionNoFutureAuctions() {
        assertTrue(emptyCalendar.canAddAuction(new Auction(contact1, date2Weeks, null, null)));
    }

    @Test
    public void testAddAuctionOneFutureAuction() {
        emptyCalendar.addAuction(new Auction(contact1, date2Weeks, null, null));
        assertFalse(emptyCalendar.canAddAuction(new Auction(contact1, Calendar.addDaysToDate(date2Weeks, 1), null, null)));
    }
}
