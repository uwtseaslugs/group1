package auctioncentral.model;

import org.junit.*;
import java.util.*;
import static org.junit.Assert.*;
/**
 *As a contact person for a non-profit organization, I want to submit an auction request.

 */
public class UserStorySubmitAuctionRequest {

    private ICalendar emptyCalendar;
    private Contact contact1;

    private Auction auction2Weeks1;
    private Auction auctionToday;
    private Auction auction2Weeks2;
    private Auction auction2Weeks3;
    private Auction auction25Days;
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
    /*
    Business rule: only the contact person for this non-profit organization can submit an auction request.
        User is the contact person for this non-profit organization -- Allowed
        User is not the contact person for this non-profit organization -- Not Allowed
     */
    @Test
    public void testAddAuctionByContact(){
        assertNotNull(new Auction(contact1,new Date(), "Null Item",null));
    }
    /*
     Business rule: Maximum of one future auction for this non-profit
        Prior to submitting the auction, the non-profit has no future auctions in the system -- Allowed
        Prior to submitting the auction, the non-profit has one future auction in the system -- Not Allowed
        Prior to submitting the auction, the non-profit has more than one future auction in the system -- Not Allowed
     */
    @Test
    public void testAddAuctionOneFutureAution(){
        emptyCalendar.addAuction(auction2Weeks1);
        assertEquals(false, emptyCalendar.canAddAuction(auction2Weeks1));
    }
    @Test
    public void testAddAuctionTwoFutureAution(){
        emptyCalendar.addAuction(auction2Weeks1);
        emptyCalendar.faddAuction(auction2Weeks1);
        assertEquals(false, emptyCalendar.canAddAuction(auction2Weeks1));
    }
    /*
     Business rule: No auctions within the past year for this non-profit
        Prior to submitting the auction, the non-profit has one auction in the system that occurred less than one year minus one day ago -- Not Allowed
        Prior to submitting the auction, the non-profit has one auction that is exactly one year minus one day in the past -- Not Allowed.
        Prior to submitting the auction, the non-profit has more than one auction in the system that occurred less than one year ago -- Not Allowed
        Prior to submitting the auction, the non-profit has no auctions in the past year in the system but one auction that is greater than one year in the past -- Allowed.
        Prior to submitting the auction, the non-profit has no auctions in the past year in the system but one auction that is exactly one year in the past -- Allowed.
        Prior to submitting the auction, the non-profit has no auctions in the past in the system -- Allowed.
     */
    @Test
    public void testAddAuctionOnLessThanOneYear(){
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
    public void testAddAuctionMoreThanOneAuctionInPast(){
        java.util.Calendar c = Calendar.getJavaCalendar();
        c.set(2015,5,9);
        Auction oneYearAgoAuction = new Auction(contact1,c.getTime(),"Test",1);
        emptyCalendar.faddAuction(oneYearAgoAuction);
        c.set(2015,1,4);
        Auction oneYearAgoAuction2 = new Auction(contact1,c.getTime(),"Test",1);
        emptyCalendar.faddAuction(oneYearAgoAuction2);
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
    /*
     Business rule: Maximum of two auctions per day
        Prior to submitting the auction, there are no auctions in the system scheduled for this day -- Allowed
        Prior to submitting the auction, there is exactly one auction in the system scheduled for this day -- Allowed
        Prior to submitting the auction, there are exactly two auctions in the system scheduled for this day -- Not Allowed
        Prior to submitting the auction, there are more than two auctions in the system scheduled for this day -- Not Allowed
     */
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
    public void testCannotAddMoreThanThreeAuctionsInOneDay(){
        emptyCalendar.addAuction(auction2Weeks1);
        emptyCalendar.addAuction(auction2Weeks2);
        emptyCalendar.faddAuction(auction2Weeks2);
        assertEquals(false, emptyCalendar.canAddAuction(auction2Weeks3));
        assertEquals(false, emptyCalendar.addAuction(auction2Weeks3));
    }
    /*
     Business rule: Maximum of twenty-five upcoming auctions in the system
        Prior to submitting the auction, there are less than 24 auctions in the system -- Allowed
        Prior to submitting the auction, there are exactly 24 auctions in the system -- Allowed
        Prior to submitting the auction, there are exactly 25 auctions in the system -- Not Allowed
        Prior to submitting the auction, there are greater than 25 auctions in the system -- Not Allowed
     */
    @Test
    public void testCanAddAuctionToEmptyCalendar() {
        assertEquals(true, emptyCalendar.canAddAuction(auction2Weeks1));
        assertEquals(true, emptyCalendar.addAuction(auction2Weeks1));
    }
    @Test
    public void testAddAuctionWith24Auctions() {
        assertEquals(true, calendarFullMinusOne.canAddAuction(auction25Days));
        assertEquals(true, calendarFullMinusOne.addAuction(auction25Days));
    }
    @Test
    public void testAddAuctionToFullCalendar() {
        assertEquals(false, calendarFull.canAddAuction(auction25Days));
        assertEquals(false, calendarFull.addAuction(auction25Days));
    }
    /*
    Business rule: Auction cannot be scheduled for more than one month into the future
        The scheduled date is exactly one month in the future, i.e. the same day of the month one month from today -- Allowed
        The scheduled date is less than one month in the future -- Allowed
        The scheduled date is exactly one month plus one day in the future -- Not Allowed
        The scheduled date is greater than one month plus one day in the future -- Not Allowed
     */
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
    /*
     Business rule: all required fields must be specified at the time an auction is submitted.
     These fields are: auction date (formatted date: DD/MM/YYYY), auction time (formatted time: HH [AM/PM]).
        One of the required fields is not specified -- Not Allowed
        All of the required fields are specified -- Allowed
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNewAuctionNullDate(){
        new Auction(contact1,null, "Null date",1);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testNewAuctionNegativeNumberOfItems() {
        new Auction(contact1,new Date(), "Negative Items",-3);
    }
    /*
     Business rule: auction date is at least one week from the day in which it is being added.
        The auction date is exactly one week from the day on which it is being added -- Allowed
        The auction date is greater than one week from the day on which it is being added -- Allowed
        The auction date is exactly six days from the day on which it is being added -- Not Allowed
        The auction date is less than six days from the day on which it is being added -- Not Allowed
        The auction date is the day on which it is being added -- Not Allowed
        The auction date is in the past -- Not Allowed
     */
    @Test
    public void testAddAuctionInOneWeek() {
        java.util.Calendar c = Calendar.getJavaCalendar();
        c.add(java.util.Calendar.DATE, 7);
        c.add(java.util.Calendar.HOUR, 1);
        assertTrue(emptyCalendar.canAddAuction(new Auction(contact1, c.getTime(), null, null)));
    }
    @Test
    public void testAddAuctionInEightDays() {
        java.util.Calendar c = Calendar.getJavaCalendar();
        c.add(java.util.Calendar.DATE, 8);
        assertTrue(emptyCalendar.canAddAuction(new Auction(contact1, c.getTime(), null, null)));
    }
    @Test
    public void testAddAuctionInSixDays() {
        java.util.Calendar c = Calendar.getJavaCalendar();
        c.add(java.util.Calendar.DATE, 6);
        assertFalse(emptyCalendar.canAddAuction(new Auction(contact1, c.getTime(), null, null)));
    }
    @Test
    public void testAddAuctionInFiveDays() {
        java.util.Calendar c = Calendar.getJavaCalendar();
        c.add(java.util.Calendar.DATE, 5);
        assertFalse(emptyCalendar.canAddAuction(new Auction(contact1, c.getTime(), null, null)));
    }
    @Test
    public void testAddAuctionToday() {
        assertEquals(false, emptyCalendar.canAddAuction(auctionToday));
        assertEquals(false, emptyCalendar.addAuction(auctionToday));
    }
    @Test
    public void testAddAuctionInPast() {
        java.util.Calendar c = Calendar.getJavaCalendar();
        c.add(java.util.Calendar.DATE, -5);
        assertFalse(emptyCalendar.canAddAuction(new Auction(contact1, c.getTime(), null, null)));
    }


}
