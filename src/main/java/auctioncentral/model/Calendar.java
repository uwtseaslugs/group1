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
     * @return true of false depending on business rules for future Auctions
     */
    @Override
    public boolean canAddAuction(Auction auction) {
        Date now = new Date();

        // less than "maxAuctions" future auctions
        List<Auction> upcomingAuctions = getAuctionsPastDate(now);
        int numOfUpcomingAuctions = upcomingAuctions.size();
        if (numOfUpcomingAuctions >= maxAuctions) {
            return false;
        }

        // no future auctions by this nonprofit
        int futureAuctionsByThisNonprofit = (int) upcomingAuctions.stream()
                .filter(a -> a.getContact().equals(auction.getContact()))
                .count();
        if (futureAuctionsByThisNonprofit >= 1) {
            return false;
        }
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

        // max of 1 other auction on the new auctions date
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

        // less than one month in the future
        java.util.Calendar jCalendar = getJavaCalendar();
        jCalendar.setTime(now);
        jCalendar.add(java.util.Calendar.MONTH, 1);
        Date oneMonthFromNow = jCalendar.getTime();
        if (auction.getDate().after(oneMonthFromNow)) {
            return false;
        }

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
