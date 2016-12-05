/*
    Deliverable 2 
    TCSS 360
    Created by: Sea Slugs
    
    Test all the logic/business rules in the calendar class.
 */
package auctioncentral.model;

import org.junit.*;

import java.util.*;

import static org.junit.Assert.*;

public class CalendarTest {
    private ICalendar emptyCalendar;
    private Contact contact1;
    
    private Auction auction2Weeks1;
    private Auction auctionTomorrow;
    private Auction auctionToday;
    private Auction auction2Weeks2;
    private Auction auction2Weeks3;
    private Auction auction25Days;
    private Auction auction3Days;
    private Auction auction2Days;
    private Date date2Weeks;
    private Date threeDays;
    private Date twoDays;

    private ICalendar calendarFullMinusOne;
    private ICalendar calendarFull;
    
    @Before
    public void setup() {
        contact1 = new Contact("username1", "name1");
        emptyCalendar = new Calendar();
        calendarFull = new Calendar();
        calendarFullMinusOne = new Calendar();

        java.util.Calendar cthreeDays = Calendar.getJavaCalendar();
        cthreeDays.add(java.util.Calendar.DATE, 3);
        threeDays = cthreeDays.getTime();

        java.util.Calendar ctwoDays = Calendar.getJavaCalendar();
        ctwoDays.add(java.util.Calendar.DATE, 2);
        ctwoDays.add(java.util.Calendar.HOUR, -1);
        twoDays = ctwoDays.getTime();

        java.util.Calendar calendar2Weeks = Calendar.getJavaCalendar();
        calendar2Weeks.add(java.util.Calendar.DATE, 14);
        date2Weeks = calendar2Weeks.getTime();

        auctionToday = new Auction(contact1, new Date(), null, null);
        auction2Weeks1 = new Auction(new Contact("1u", "1n"), date2Weeks, "Co comment", null);
        auction2Weeks2 = new Auction(new Contact("2u", "2n"), date2Weeks, "Co comment", null);
        auction2Weeks3 = new Auction(new Contact("3u", "3n"), date2Weeks, "Co comment", null);
        auctionTomorrow = new Auction(new Contact("2u", "2n"), Calendar.addDaysToDate(new Date(), 1), null, null);
        auction25Days = new Auction(new Contact("2u", "2n"), Calendar.addDaysToDate(new Date(), 25), null, null);
        auction3Days = new Auction(new Contact("4u", "4u"), threeDays, "Co comment", null);
        auction2Days= new Auction(new Contact("5u", "5u"), twoDays, "Co comment", null);

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

    @Test
    public void testGetNumberOfDaysForCurrentMonthIsCorrect() {
        assertEquals(Calendar.getJavaCalendar().getActualMaximum(java.util.Calendar.DAY_OF_MONTH),
                     emptyCalendar.getNumberOfDaysForCurrentMonth());
    }

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

    @Test
    public void testGetNumberOfAuctionsOnDateZero() {
        assertEquals(0, emptyCalendar.getNumberOfAuctionsOnDate(new Date()));
    }

    @Test
    public void testGetNumberOfAuctionsOnDateOne() {
        emptyCalendar.addAuction(auction2Weeks1);
        assertEquals(1, emptyCalendar.getNumberOfAuctionsOnDate(date2Weeks));
    }

    @Test
    public void testGetNumberOfAuctionsOnDateTwo() {
        emptyCalendar.addAuction(auction2Weeks1);
        emptyCalendar.addAuction(auction2Weeks2);
        assertEquals(2, emptyCalendar.getNumberOfAuctionsOnDate(date2Weeks));
    }
        @Test
    public void testAddAuctionOnCoupleMonthsInPast(){
        java.util.Calendar c = Calendar.getJavaCalendar();
        c.set(2015,5,9);
        Auction oneYearAgoAuction = new Auction(contact1,c.getTime(),"Test",1);
        emptyCalendar.faddAuction(oneYearAgoAuction);
        c.set(2015,11,8);
        assertEquals(false,emptyCalendar.canAddAuctionYear(new Auction(contact1,c.getTime(),"Test",1)));


    }
    @Test
    public void testAddAuctionOnOneYearPastMinusOneDay(){
        java.util.Calendar c = Calendar.getJavaCalendar();
        c.set(2014,11,9);
        Auction oneYearAgoAuction = new Auction(contact1,c.getTime(),"Test",1);
        emptyCalendar.faddAuction(oneYearAgoAuction);
        c.set(2015,11,8);
        assertEquals(false,emptyCalendar.canAddAuctionYear(new Auction(contact1,c.getTime(),"Test",1)));

    }
    @Test
    public void testAddAuctionOnGreaterThanOneYear(){
        java.util.Calendar c = Calendar.getJavaCalendar();
        c.set(2013,9,9);
        Auction oneYearAgoAuction = new Auction(contact1,c.getTime(),"Test",1);
        emptyCalendar.faddAuction(oneYearAgoAuction);
        c.set(2015,5,8);
        assertEquals(true,emptyCalendar.canAddAuctionYear(new Auction(contact1,c.getTime(),"Test",1)));

    }
    @Test
    public void testAddAuctionOnExactlyOneYear(){
        java.util.Calendar c = Calendar.getJavaCalendar();
        c.set(2014,11,8);
        Auction oneYearAgoAuction = new Auction(contact1,c.getTime(),"Test",1);
        emptyCalendar.faddAuction(oneYearAgoAuction);
        c.set(2015,11,8);
        assertEquals(true,emptyCalendar.canAddAuctionYear(new Auction(contact1,c.getTime(),"Test",1)));

    }
    @Test
    public void testRemoveAuctionTwentyFiveDaysAway(){
        emptyCalendar.addAuction(auction25Days);
        assertTrue(emptyCalendar.removeAuction(auction25Days));

    }
    @Test
    public void testRemoveAuctionThreeDaysAway() {
        emptyCalendar.faddAuction(auction3Days);
        assertTrue(emptyCalendar.removeAuction(auction3Days));
    }
    @Test
    public void testRemoveAuctionTwoDaysAway() {
        emptyCalendar.faddAuction(auction2Days);
        assertFalse(emptyCalendar.removeAuction(auction2Days));
    }
    @Test
    public void testRemoveAuctionToday(){
        emptyCalendar.faddAuction(auctionToday);
        assertFalse(emptyCalendar.removeAuction(auctionToday));
    }

    @Test
    public void testAddMaxAuctions() {
        calendarFull.changeMaxAuctions(26);
        assertEquals(26, calendarFull.getMaxAuctions());
        calendarFull.changeMaxAuctions(5000);
        assertEquals(5000, calendarFull.getMaxAuctions());
    }
}
