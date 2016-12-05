/*
    Deliverable 2 
    TCSS 360
    Created by: Sea Slugs
    
    Interface for Calendar.
 */
package auctioncentral.model;

import java.util.Date;
import java.util.List;

import java.io.Serializable;

public interface ICalendar extends Serializable {
    boolean canAddAuction(Auction a);
    boolean addAuction(Auction a);
    boolean removeAuction(Auction a);
    boolean faddAuction(Auction a);
    boolean canAddAuctionYear(Auction a);
    void changeMaxAuctions(int a);
    int getMaxAuctions();
    
    List<Auction> getAuctionsPastDate(Date d);
    List<Auction> getAuctionsBetweenDates(Date start, Date end);


    int getNumberOfDaysForCurrentMonth();

    int getNumberOfAuctionsOnDate(Date d);
}
