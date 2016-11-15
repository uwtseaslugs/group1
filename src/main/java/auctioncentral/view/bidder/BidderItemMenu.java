package auctioncentral.view.bidder;

import auctioncentral.model.*;
import auctioncentral.view.*;

import java.text.*;
import java.util.*;

public class BidderItemMenu extends AbstractMenu {
    private Bidder bidder;
    private Auction auction;
    private Item item;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MMMMMM d, yyyy, ha");
    private String heading = "Select an item to bid on::\n";

    public BidderItemMenu(Auction auction, Item item, AbstractMenu parent){
        super(parent);
        this.item = item;
        this.auction = auction;
    }

    @Override
    public String getHeading() {
        return auction.getContact().getNonprofitName() + " Auction, " + dateFormat.format(auction.getDate());
    }

    @Override
    public String getBody() {
        return "" + item.getName() + "\n" + item.getCondition() + "\n$" + item.getMinimumBid() + "\n" + item.getComment() + "\n\n" +
                "What would you like to do?\n" +
                "1. Place bid on this item\n" +
                "2. Go back \n" +
                "3. Exit AuctionCentral\n";
    }

    @Override
    public void onResponse(Scanner scan) {
        switch (scan.nextLine()) {
            case "1":
                new BidderPlaceBidMenu(this, item).show();
                return;
            case "2":
                getParent().show();
                return;
            case "3":
                return;
            default:
                System.out.print("Please enter a number 1 - 3.\n> ");
                onResponse(scan);
        }
    }
}
