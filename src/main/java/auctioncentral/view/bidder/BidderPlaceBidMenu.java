/*
    Deliverable 2 
    TCSS 360
    Created by: Sea Slugs
    
    BidderPlaceBidMenu shows the bid info.
 */
package auctioncentral.view.bidder;
import auctioncentral.*;
import auctioncentral.model.*;
import auctioncentral.view.*;

import java.math.*;
import java.time.*;
import java.time.format.*;
import java.util.*;

public class BidderPlaceBidMenu  extends AbstractMenu {
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM d, uuuu");
    private Item item;
    private String startBid = "Starting Bid: ";
    private String currentBid = "Current Bid: ";
    private String prompt = "Enter bid Amount: ";

    public BidderPlaceBidMenu(AbstractMenu parent, Item item) {
        super(parent);
        this.item = item;
    }

    @Override
    public String getHeading() {
        return dateTimeFormatter.format(LocalDateTime.now());
    }

    @Override
    public String getBody() {
        return "" + item.getName() + "\n" + item.getCondition() + "\n$" + item.getMinimumBid() + "\n" + item.getComment() + "\n\n" +
                prompt;
    }

    @Override
    public void onResponse(Scanner scan) {
        BigDecimal bd = new BigDecimal(scan.nextLine());
        if (!item.isValidBid(bd)) {
            System.out.print("Error: invalid price\n> ");
            onResponse(scan);
        } else if (item.getBid((Bidder) LoginManager.inst().getCurrentUser()) != null) {
            System.out.print("Error: you have already bid on this item\n> ");
            onResponse(scan);
        } else {
            new BidderConfirmBidMenu(this, item, bd.doubleValue()).show();
        }
    }
}
