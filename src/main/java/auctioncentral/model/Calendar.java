package auctioncentral.model;

import java.util.*;
import java.util.stream.*;

public class Calendar implements ICalendar {

    private SortedSet<Auction> auctions;

    public Calendar() {
        auctions = new TreeSet<>((a, b) -> a.getDate().compareTo(b.getDate()));
    }

    @Override
    public boolean canAddAuction(Auction a) {
        return false;
    }
    
    @Override
    public boolean addAuction(Auction a) {
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
