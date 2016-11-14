package auctioncentral.view.bidder;

import auctioncentral.*;
import auctioncentral.view.*;

import java.time.*;
import java.time.format.*;
import java.util.*;

public class BidderHomeMenu extends AbstractMenu {
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM d, uuuu");
    private String body = "Home Screen:\n1. View Auctions\n2. Settings\n3. Exit AuctionCentral";
    private String wrongInput = "Please enter a number 1 - 3.\n> ";

    @Override
    public String getHeading() {
        return dateTimeFormatter.format(LocalDateTime.now());
    }

    public BidderHomeMenu(AbstractMenu parent) {
        super(parent);
    }

    @Override
    public String getBody() {
        return body;
    }

    @Override
    public void onResponse(Scanner scan) {
        switch (scan.nextLine()) {
            case "1":
                new BidderAuctionMenu(this, AuctionCentral.calendar.getAuctionsPastDate(new Date())).show();
                return;
            case "2":
                getParent().show();
            case "3":
                return;
            default:
                System.out.printf(wrongInput);
                onResponse(scan);
        }
    }
}
