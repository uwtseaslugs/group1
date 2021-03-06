/*
    Deliverable 2 
    TCSS 360
    Created by: Sea Slugs
    
    BidderConfirmBidMenu page that tells the user the bid has been placed.
 */
package auctioncentral.view.bidder;
import auctioncentral.AuctionCentral;
import auctioncentral.model.Bidder;
import auctioncentral.model.Item;
import auctioncentral.model.LoginManager;
import auctioncentral.view.*;

import java.math.BigDecimal;
import java.time.*;
import java.time.format.*;
import java.util.Scanner;

public class BidderConfirmBidMenu extends AbstractMenu {
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM d, uuuu");
    private double bid;
    private Item item;
    private String accept = "\nAccept - 1";
    private String cancel = "\nCancel - 2";


    public BidderConfirmBidMenu(AbstractMenu parent, Item item, double bid) {
        super(parent);
        this.item = item;
        this.bid = bid;
    }

    @Override
    public String getHeading() {
        return dateTimeFormatter.format(LocalDateTime.now());
    }

    @Override
    public String getBody() {
        return "You are bidding " + bid + " for " + item.getName()
                + accept
                + cancel;
    }

    @Override
    public void onResponse(Scanner scan) {
        switch (scan.nextLine()){
            case "1":
                item.placeBid((Bidder) LoginManager.inst().getCurrentUser(), new BigDecimal(bid));
                getParent().getParent().getParent().show();
            case "2":
                getParent().getParent().getParent().show();
        }
    }
}
