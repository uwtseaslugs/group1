package auctioncentral.model;

import org.junit.*;

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
 * Business rule: Maximum of two auctions per day
 * Prior to submitting the auction, there are no auctions in the system scheduled for this day -- Allowed
 * Prior to submitting the auction, there is exactly one auction in the system scheduled for this day -- Allowed
 * Prior to submitting the auction, there are exactly two auctions in the system scheduled for this day -- Not Allowed
 * Prior to submitting the auction, there are more than two auctions in the system scheduled for this day -- Not Allowed
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
    private ICalendar fullCalendar;
    private ICalendar avgCalendar;
    
    private Auction auctionToday;
    private Auction auctionTomorrow;

    @Before
    public void setup() {
        emptyCalendar = new Calendar();
        fullCalendar = new Calendar();
//        Contact contact = new Contact("contactUsername", "contactName");
//        auctionToday = new Auction(contact, "Test auction", new Date(), "10:30", "Co comment");
//        auctionTomorrow = new Auction("Test auction",
//                                      Calendar.addDaysToDate(new Date(), 1),
//                                      "10:30", "Co comment");

//        for (int i = 1; i < emptyCalendar.getNumberOfDaysForCurrentMonth(); i++) {
//            Auction tmpAuction = new Auction("Test auction " + i,
//                                             Calendar.addDaysToDate(new Date(), i),
//                                             "10:30", "No comment");
//            fullCalendar.addAuction(tmpAuction);
//        }
    }

    @Test
    public void testCanAddAuctionToEmptyCalendar() {
        assertEquals(true, emptyCalendar.canAddAuction(auctionTomorrow));
        assertEquals(true, emptyCalendar.addAuction(auctionTomorrow));
    }

    @Test
    public void testCannotAddAuctionToFullCalendar() {
        assertEquals(false, fullCalendar.canAddAuction(auctionTomorrow));
        assertEquals(false, fullCalendar.addAuction(auctionTomorrow));
    }

    @Test
    public void testCannotAddAuctionBeforeToday() {
//        Auction auctionTmp = new Auction("Test auction",
//                                      Calendar.addDaysToDate(new Date(), -1),
//                                      "10:30", "Co comment");
//        assertEquals(false, fullCalendar.canAddAuction(auctionTmp));
//        assertEquals(false, fullCalendar.addAuction(auctionTmp));
    }

    @Test
    public void testCannotAddAuctionToday() {
        assertEquals(false, fullCalendar.canAddAuction(auctionToday));
        assertEquals(false, fullCalendar.addAuction(auctionToday));
    }

    @Test
    public void testCanRemoveAuctionWhenExists() {
        assertEquals(true, fullCalendar.removeAuction(auctionTomorrow));
    }

    @Test
    public void testCannotRemoveAuctionWhenDoesNotExist() {
        assertEquals(false, emptyCalendar.removeAuction(auctionTomorrow));
    }

    @Test
    public void testGetAuctionsPastDateReturnsEmptyWhenCalIsEmpty() {
        java.util.Calendar c = Calendar.getJavaCalendar();
        c.set(java.util.Calendar.DAY_OF_MONTH, 1);
        assertEquals(0, emptyCalendar.getAuctionsPastDate(c.getTime()));
    }

    @Test
    public void testGetAuctionsPastDateReturnsNonEmptyWhenCalIsNonEmpty() {
        java.util.Calendar c = Calendar.getJavaCalendar();
        c.set(java.util.Calendar.DAY_OF_MONTH, 1);
        assertNotEquals(0, fullCalendar.getAuctionsPastDate(c.getTime()));
    }

    @Test
    public void testGetNumberOfDaysForCurrentMonthIsCorrect() {
        assertEquals(Calendar.getJavaCalendar().getActualMaximum(java.util.Calendar.DAY_OF_MONTH),
                     emptyCalendar.getNumberOfDaysForCurrentMonth());
    }
}
