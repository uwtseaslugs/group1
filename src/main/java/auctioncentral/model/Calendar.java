package auctioncentral.model;

import java.util.Date;
import java.util.List;

public class Calendar implements ICalendar {
    @Override
    public boolean canAddAuction(Auction a) {
        return false;
    }
    
    @Override
    public boolean addAuction(Auction a) {
        return false;
    }
    
    @Override
    public boolean removeAuction(Auction a) {
        return false;
    }

    @Override
    public List<Auction> getAuctionsPastDate(Date d) {
        return null;
    }
    
    @Override
    public List<Auction> getAuctionsBetweenDates(Date start, Date end) {
        return null;
    }
    
    @Override
    public int getNumberOfDaysForCurrentMonth() {
        return 0;
    }

    public static java.util.Calendar getJavaCalendar() {
        return java.util.Calendar.getInstance();
    }

    public static Date addDaysToDate(Date theDate, int numDays)
    {
        java.util.Calendar c = getJavaCalendar();
        c.setTime(theDate);
        c.add(java.util.Calendar.DAY_OF_MONTH, numDays);
        return c.getTime();
    }
}
