package auctioncentral.view.bidder;
import auctioncentral.model.*;
import auctioncentral.view.*;

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
        new BidderConfirmBidMenu(this, item, scan.nextDouble()).show();
    }
}
