/*
    Deliverable 2 
    TCSS 360
    Created by: Sea Slugs
    
    Calendar class that we use to manage the auctions. Has canAddAuction()to check the 
    business rules and addAuction() to add the auction.

 */
package auctioncentral.model;

import java.util.*;
import java.util.stream.*;

public class Calendar implements ICalendar {

    private TreeSet<Auction> auctions;
    private int maxAuctions;

    public Calendar() {
        auctions = new TreeSet<>();
        maxAuctions = 25;
    }

    private static Calendar myInst = null;
    public static Calendar inst() {
        if (myInst == null) {
            myInst = new Calendar();
        }

        return myInst;
    }

    public static void setInst(Calendar theInst) {
        if (myInst != null)
            throw new IllegalStateException("Must call setInst before inst");
        myInst = theInst;
    }

    public static void clearInst() {
        myInst = null;
    }
    
      /**
     *
     * @param auction
     * @return true of false depending on contact past auctions
     */
    public boolean canAddAuctionYear(Auction auction){
        // if any previous auctions by this nonprofit, last one was 1 year+ before new auctions date
        Auction lastAuctionByThisNonprofit = auctions.descendingSet().stream()
                .filter(a -> a.getContact().equals(auction.getContact()))
                .filter(a -> a.getDate().before(auction.getDate()))
                .findFirst().orElse(null);
        if (lastAuctionByThisNonprofit != null) {
            java.util.Calendar jCalendar = getJavaCalendar();
            jCalendar.setTime(lastAuctionByThisNonprofit.getDate());
            jCalendar.add(java.util.Calendar.YEAR, 1);
            Date yearAfterLastAuction = jCalendar.getTime();
            if (yearAfterLastAuction.after(auction.getDate())) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param auction
     * @return true if the number of upcoming auctions is less than the max limit
     */
    public boolean canAddAuctionMaxLimit(Auction auction) {
        Date now = new Date();
        // less than "maxAuctions" future auctions
        List<Auction> upcomingAuctions = getAuctionsPastDate(now);
        int numOfUpcomingAuctions = upcomingAuctions.size();
        if (numOfUpcomingAuctions >= maxAuctions) {
            return false;
        }
        return true;
    }

    /**
     *
     * @param auction
     * @return true if the non profit of auction has another auction already scheduled in the future
     */
    public boolean canAddAuctionNonprofitLimit(Auction auction) {
        Date now = new Date();
        List<Auction> upcomingAuctions = getAuctionsPastDate(now);
        // no future auctions by this nonprofit
        int futureAuctionsByThisNonprofit = (int) upcomingAuctions.stream()
                .filter(a -> a.getContact().equals(auction.getContact()))
                .count();
        if (futureAuctionsByThisNonprofit >= 1) {
            return false;
        }
        return true;
    }

    /**
     *
     * @param auction
     * @return true if there is already 2 auctions on the same date as auction
     */
    public boolean canAddAuctionDailyLimit(Auction auction) {
        Date now = new Date();
        List<Auction> upcomingAuctions = getAuctionsPastDate(now);
        int countOnNewAuctionDate = 0;
        java.util.Calendar newAuctionCalendar = getJavaCalendar();
        newAuctionCalendar.setTime(auction.getDate());
        for (Auction a : upcomingAuctions) {
            java.util.Calendar jCalendar = getJavaCalendar();
            jCalendar.setTime(a.getDate());
            if (jCalendar.get(java.util.Calendar.YEAR) == newAuctionCalendar.get(java.util.Calendar.YEAR) &&
                    jCalendar.get(java.util.Calendar.MONTH) == newAuctionCalendar.get(java.util.Calendar.MONTH) &&
                    jCalendar.get(java.util.Calendar.DATE) == newAuctionCalendar.get(java.util.Calendar.DATE)) {
                countOnNewAuctionDate++;
            }
        }
        if (countOnNewAuctionDate >= 2) {
            return false;
        }
        return true;
    }

    /**
     *
     * @param auction
     * @return true if auction is less than one month in the future
     */
    public boolean canAddAuctionMaxInFuture(Auction auction) {
        Date now = new Date();
        // less than one month in the future
        java.util.Calendar jCalendar = getJavaCalendar();
        jCalendar.setTime(now);
        jCalendar.add(java.util.Calendar.MONTH, 1);
        Date oneMonthFromNow = jCalendar.getTime();
        if (auction.getDate().after(oneMonthFromNow)) {
            return false;
        }
        return true;
    }

    /**
     *
     * @param auction
     * @return true if auction is more than one week in the future
     */
    public boolean canAddAuctionMinInFuture(Auction auction) {
        Date now = new Date();
        java.util.Calendar jCalendar = getJavaCalendar();
        // more than one week in the future
        jCalendar = getJavaCalendar();
        jCalendar.setTime(now);
        jCalendar.add(java.util.Calendar.DATE, 7);
        Date oneWeekFromNow = jCalendar.getTime();
        if (auction.getDate().before(oneWeekFromNow)) {
            return false;
        }
        return true;
    }
     /**
     *
     * @param auction
     * @return true of false depending on business rules for future Auctions
     */
    @Override
    public boolean canAddAuction(Auction auction) {
        return canAddAuctionYear(auction) && canAddAuctionDailyLimit(auction) && canAddAuctionMaxInFuture(auction) &&
                canAddAuctionMinInFuture(auction) && canAddAuctionNonprofitLimit(auction) && canAddAuctionMaxLimit(auction);
    }
    /**
     *
     * @param a
     * @return adds the auction without business rules
     */
    @Override
    public boolean faddAuction(Auction a) {
        return auctions.add(a);
    }
    /**
     *
     * @param a
     * @return checks the buiness rules and then adds them.
     */
    @Override
    public boolean addAuction(Auction a) {
        if (!canAddAuction(a)) {
            return false;
        }
        return auctions.add(a);
    }
    /**
     *
     * @param a
     * @return removes the auction
     * created by Jon
     */
    @Override
    public boolean removeAuction(Auction a) {
        Date now = new Date();
        java.util.Calendar jCalendar = getJavaCalendar();
        jCalendar.setTime(now);
        jCalendar.add(java.util.Calendar.DATE, 2);
        Date twoDaysFromNow = jCalendar.getTime();
        if (a.getDate().before(twoDaysFromNow)) {
            return false;
        } else{
            return auctions.remove(a);
        }
    }
    @Override
    public List<Auction> getAuctionsPastDate(Date d) {
        return auctions.stream()
                .filter(a -> a.getDate().after(d))
                .collect(Collectors.toList());
    }
    /**
     *
     * @param start
     * @param end
     * @return returns the auctions between these 2 dates
     */
    @Override
    public List<Auction> getAuctionsBetweenDates(Date start, Date end) {
        return auctions.stream()
                .filter(a -> a.getDate().after(start) && a.getDate().before(end))
                .collect(Collectors.toList());
    }
 
    @Override
    public int getNumberOfDaysForCurrentMonth() {
        return getJavaCalendar().getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
    }

    public static java.util.Calendar getJavaCalendar() {
        return java.util.Calendar.getInstance();
    }
    
    /**
     * 
     * @param theDate
     * @param numDays
     * @return adds days to the current date.
     */
    public static Date addDaysToDate(Date theDate, int numDays) {
        java.util.Calendar c = getJavaCalendar();
        c.setTime(theDate);
        c.add(java.util.Calendar.DAY_OF_MONTH, numDays);
        return c.getTime();
    } 
    /**
     * 
     * @param d
     * @return returns the number of auctions on that day.
     */
    public int getNumberOfAuctionsOnDate(Date d) {
        java.util.Calendar target = java.util.Calendar.getInstance();
        target.setTime(d);
        int numAuctionsOnDate = 0;
        for (Auction a : auctions) {
            java.util.Calendar c = java.util.Calendar.getInstance();
            c.setTime(a.getDate());
            if (c.get(java.util.Calendar.YEAR) == target.get(java.util.Calendar.YEAR) &&
                    c.get(java.util.Calendar.MONTH) == target.get(java.util.Calendar.MONTH) &&
                    c.get(java.util.Calendar.DATE) == target.get(java.util.Calendar.DATE)) {
                numAuctionsOnDate++;
            }
        }
        return numAuctionsOnDate;
    }


    public static final int CANCEL_MAX_DAYS_AWAY = 2;

    /**
     *
     * @param auction
     * @return if auction has been previously scheduled with this Calendar and is at least 2 days in the future
     */
    public boolean canCancelAuction(Auction auction) {
        Date now = new Date();
        java.util.Calendar target = java.util.Calendar.getInstance();
        target.setTime(now);
        target.add(java.util.Calendar.DATE, CANCEL_MAX_DAYS_AWAY);
        return target.getTime().before(auction.getDate()) && auctions.contains(auction);
    }

    /**
     * Cancels the auction if possible
     * @param auction
     */
    public void cancelAuction(Auction auction) {
        if (canCancelAuction(auction)) {
            auctions.remove(auction);
        }
    }

    /**
     *
     * @param contact for the Auction
     * @return the next upcoming Auction by the contact or null if none
     */
    public Auction getNextAuctionBy(Contact contact) {
        List<Auction> upcoming = getAuctionsPastDate(new Date());
        return upcoming.stream().filter(a -> a.getContact().equals(contact))
                .findFirst().orElse(null);
    }

    /**
     *
     * @param addAuctions
     */
    public void addMaxAuctions(int addAuctions) {
        maxAuctions += addAuctions;
    }

    public int getMaxAuctions(){
        return maxAuctions;
    }
}
