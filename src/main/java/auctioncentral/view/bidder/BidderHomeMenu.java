package auctioncentral.view.bidder;

import auctioncentral.view.*;
import java.time.*;
import java.time.format.*;
import java.util.Scanner;

public class BidderHomeMenu extends AbstractMenu {

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM d, uuuu");

    @Override
    public String getHeading() {
        return dateTimeFormatter.format(LocalDateTime.now());
    }

    @Override
    public String getBody() {
        return "Home Screen:\n" +
                "1. View Auctions\n" +
                "2. Settings\n" +
                "3. Exit AuctionCentral";
    }

    @Override
    public void onResponse(Scanner scan) {
        switch (scan.nextLine()) {
            case "1":
                return;
            case "2":
                new BidderBidMenu().show();
                return;
            case "3":
                return;
            default:
                System.out.printf("Please enter a number 1 - 3.\n> ");
                onResponse(scan);
        }
    }
}
