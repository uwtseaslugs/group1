package auctioncentral.model;

import java.util.*;
import java.util.stream.*;

public class Calendar implements ICalendar {

    private TreeSet<Auction> auctions;

    public Calendar() {
        auctions = new TreeSet<>((a, b) -> a.getDate().compareTo(b.getDate()));
    }

    @Override
    public boolean canAddAuction(Auction auction) {
        Date now = new Date();

        // less than 25 future auctions
        List<Auction> upcomingAuctions = getAuctionsPastDate(now);
        int numOfUpcomingAuctions = upcomingAuctions.size();
        if (numOfUpcomingAuctions >= 25) {
            return false;
        }

        // no future auctions by this nonprofit
        int futureAuctionsByThisNonprofit = (int) upcomingAuctions.stream()
                .filter(a -> a.getContact().equals(auction.getContact()))
                .count();
        if (futureAuctionsByThisNonprofit >= 1) {
            return false;
        }

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

        // max of 1 other auction on the new auctions date
        // todo

        // less than one month in the future
        // todo

        // more than one week in the future
        // todo

        return true;
    }
    
    @Override
    public boolean addAuction(Auction a) {
        if (!canAddAuction(a)) {
            return false;
        }
        return auctions.add(a);
    }
    
    @Override
    public boolean removeAuction(Auction a) {
        return auctions.remove(a);
    }

    @Override
    public List<Auction> getAuctionsPastDate(Date d) {
        return auctions.stream()
                .filter(a -> a.getDate().after(d))
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Auction> getAuctionsBetweenDates(Date start, Date end) {
        return auctions.stream()
                .filter(a -> a.getDate().after(start) && a.getDate().before(end))
                .collect(Collectors.toList());
    }
    
    @Override
    public int getNumberOfDaysForCurrentMonth() {
        return 0;
    }

    public static java.util.Calendar getJavaCalendar() {
        return java.util.Calendar.getInstance();
    }

    public static Date addDaysToDate(Date theDate, int numDays) {
        java.util.Calendar c = getJavaCalendar();
        c.setTime(theDate);
        c.add(java.util.Calendar.DAY_OF_MONTH, numDays);
        return c.getTime();
    }
}
