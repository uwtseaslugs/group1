/*
    Deliverable 2 
    TCSS 360
    Created by: Sea Slugs
    
    BidderAuctionMenu shows the different auctions and then asks the user which auction the user wants to see.
 */
package auctioncentral.view.bidder;

import auctioncentral.model.Auction;
import auctioncentral.view.*;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.*;
import java.util.List;
import java.util.Scanner;

public class BidderAuctionMenu extends AbstractMenu {
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM d, uuuu");
    private static SimpleDateFormat auctionFormatter = new SimpleDateFormat("EEE MM-dd-YYYY HH:mm a");
    private List<Auction> auctionList;

    public BidderAuctionMenu(AbstractMenu parent, List<Auction> auctionList) {
        super(parent);
        this.auctionList = auctionList;
    }

    @Override
    public String getHeading() {
        return "List of Auctions after: " + dateTimeFormatter.format(LocalDateTime.now());
    }

    @Override
    public String getBody() {
        StringBuilder auctionStr = new StringBuilder();
        for (int i = 0; i <auctionList.size() ; i++) {
            Auction a = auctionList.get(i);
            auctionStr.append("\t" + i + "):" + a.getContact().getNonprofitName() + " Auction on "
                    + auctionFormatter.format(a.getDate()) + "\n");
        }
        return "Available Auctions (Select the auction you would like to view) :\n" + auctionStr.toString();

    }

    @Override
    public void onResponse(Scanner scan) {
        new BidderAuctionItemsMenu(auctionList.get(Integer.parseInt(scan.nextLine())),this).show();
    }
}
