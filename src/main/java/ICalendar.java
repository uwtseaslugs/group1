

import java.util.Date;
import java.util.List;

public interface ICalendar {
    boolean canAddAuction(Auction a);
    void addAuction(Auction a);
    void removeAuction(Auction a);

    List<Auction> getAuctionsPastDate(Date d);
    List<Auction> getAuctionsBetweenDates(Date start, Date end);
}
