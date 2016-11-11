package auctioncentral.view.bidder;

import auctioncentral.view.*;
import java.time.*;
import java.time.format.*;

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
    public void onResponse(String response) {
        int responseNum = Integer.parseInt(response);
        switch (responseNum) {
            case 1:
                new BidderBidMenu().show();
                return;
            case 2:
                return;
            case 3:
                return;
        }
    }
}