package auctioncentral.model;

import java.util.Date;
import java.util.List;

import java.io.Serializable;

public interface ICalendar extends Serializable {
    boolean canAddAuction(Auction a);
    void addAuction(Auction a);
    void removeAuction(Auction a);

    List<Auction> getAuctionsPastDate(Date d);
    List<Auction> getAuctionsBetweenDates(Date start, Date end);
}
