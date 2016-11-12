package auctioncentral.model;

import org.junit.*;
import static org.junit.Assert.*;


import java.util.Date;

public class CalendarTest {
    private ICalendar emptyCalendar;
    private ICalendar fullCalendar;
    private ICalendar avgCalendar;
    
    private Auction auctionToday;

    @Before
    public void setup() {
        emptyCalendar = new Calendar();
        fullCalendar = new Calendar();
        auctionToday = new Auction("Test auction", new Date(), "10:30", "Co comment");

        for (int i = 1; i < emptyCalendar.getNumberOfDaysForCurrentMonth(); i++) {
            Auction tmpAuction = new Auction("Test auction " + i,
                                             Calendar.addDaysToDate(new Date(), i),
                                             "10:30", "No comment");
            fullCalendar.addAuction(tmpAuction);
        }
    }

    @Test
    public void testCanAddAuctionToEmptyCalendar() {
        assertEquals(true, emptyCalendar.canAddAuction(auctionToday));
        assertEquals(true, emptyCalendar.addAuction(auctionToday));
    }

    @Test
    public void testCannotAddAuctionToFullCalendar() {
        assertEquals(false, fullCalendar.canAddAuction(auctionToday));
        assertEquals(false, fullCalendar.addAuction(auctionToday));
    }

    @Test
    public void testCanRemoveAuctionWhenExists() {
        assertEquals(true, fullCalendar.removeAuction(auctionToday));
    }

    @Test
    public void testCannotRemoveAuctionWhenDoesNotExist() {
        assertEquals(false, emptyCalendar.removeAuction(auctionToday));
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
