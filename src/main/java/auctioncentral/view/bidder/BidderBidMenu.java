package auctioncentral.view.bidder;

import auctioncentral.model.Auction;
import auctioncentral.model.Bidder;
import auctioncentral.model.Item;
import auctioncentral.view.*;
import java.time.*;
import java.time.format.*;
import java.util.List;
import java.util.Scanner;

public class BidderBidMenu extends AbstractMenu {
    private Bidder bidder;
    private Auction auction;
    private List<Item> items;
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM d, uuuu");
    private String heading = "Select an item to bid on::\n";

    public BidderBidMenu(Auction auction, AbstractMenu parent){
        super(parent);
        items = auction.getItems();
        this.auction = auction;
    }

    @Override
    public String getHeading() {
        return dateTimeFormatter.format(LocalDateTime.now());
    }

    @Override
    public String getBody() {
        StringBuilder itemStr = new StringBuilder();
        for (int i = 0; i <auction.getItems().size() ; i++) {
            Item item = items.get(i);
            itemStr.append("\tItem " + i + "):" + item.getName());
        }
        return heading + itemStr.toString();
    }

    @Override
    public void onResponse(Scanner scan) {
        new BidderPlaceBidMenu(this,items.get(Integer.parseInt(scan.nextLine()))).show();
    }
}
