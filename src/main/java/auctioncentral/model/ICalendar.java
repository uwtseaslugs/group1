package auctioncentral.model;

import java.util.Date;
import java.util.List;

import java.io.Serializable;

public interface ICalendar extends Serializable {
    boolean canAddAuction(Auction a);
    boolean addAuction(Auction a);
    boolean removeAuction(Auction a);

    List<Auction> getAuctionsPastDate(Date d);
    List<Auction> getAuctionsBetweenDates(Date start, Date end);


    int getNumberOfDaysForCurrentMonth();
}
