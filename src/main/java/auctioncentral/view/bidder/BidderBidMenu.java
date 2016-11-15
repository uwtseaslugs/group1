/*
    Deliverable 2 
    TCSS 360
    Created by: Sea Slugs
    
    BidderBidMenu shows the infotion about the item for the user.
 */
package auctioncentral.view.bidder;

import auctioncentral.*;
import auctioncentral.model.*;
import auctioncentral.view.*;

import java.math.*;
import java.text.*;
import java.util.*;

public class BidderBidMenu extends AbstractMenu {
    private Bidder bidder;
    private Auction auction;
    private List<Item> items;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MMMMMM d, yyyy, ha");
    private String heading = "Select an item to bid on::\n";

    public BidderBidMenu(Auction auction, AbstractMenu parent){
        super(parent);
        items = auction.getItems();
        this.auction = auction;
    }

    @Override
    public String getHeading() {
        return auction.getContact().getNonprofitName() + " Auction, " + dateFormat.format(auction.getDate());
    }

    @Override
    public String getBody() {
        StringBuilder itemStr = new StringBuilder();
        itemStr.append("ID\tItem name\tCondition\tMin bid\t\tMy bid\n");
        for (int i = 0; i < auction.getItems().size() ; i++) {
            Item item = items.get(i);
            itemStr.append("" + i + "\t" + item.getName() + "\t\t" + item.getCondition().name().toLowerCase() + "    \t$" + item.getMinimumBid() + "\t\t");
            BigDecimal bid = item.getBid((Bidder) AuctionCentral.loginManager.getCurrentUser());
            if (bid != null) {
                itemStr.append("$" + bid.toPlainString());
            }
            itemStr.append("\n");
        }
        return itemStr.toString() + "\n" +
                "Type item ID to get more information and bid on the item\n" ;
    }

    @Override
    public void onResponse(Scanner scan) {
        new BidderPlaceBidMenu(this,items.get(Integer.parseInt(scan.nextLine()))).show();
    }
}
