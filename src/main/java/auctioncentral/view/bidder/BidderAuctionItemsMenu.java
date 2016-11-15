package auctioncentral.view.bidder;

import auctioncentral.*;
import auctioncentral.model.*;
import auctioncentral.view.*;

import java.math.*;
import java.text.*;
import java.util.*;

public class BidderAuctionItemsMenu extends AbstractMenu {
    private Bidder bidder;
    private Auction auction;
    private List<Item> items;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MMMMMM d, yyyy, ha");
    private String heading = "Select an item to bid on::\n";

    public BidderAuctionItemsMenu(Auction auction, AbstractMenu parent){
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
        itemStr.append("Items offered for sale:\n");
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
        return heading + itemStr.toString() + "\n" +
               "What would you like to do?\n" +
                "1. Bid on an Item.\n" +
                "2. Go back \n" +
                "3. Exit AuctionCentral\n" ;
    }

    @Override
    public void onResponse(Scanner scan) {
        switch (scan.nextLine()) {
            case "1":
                new BidderBidMenu(auction, this).show();
                return;
            case "2":
                getParent().getParent().show();
                return;
            case "3":
                return;
            default:
                System.out.print("Please enter a number 1 - 3.\n> ");
                onResponse(scan);
        }
    }
}
