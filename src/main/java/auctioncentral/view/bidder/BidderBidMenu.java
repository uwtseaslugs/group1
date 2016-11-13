package auctioncentral.view.bidder;

import auctioncentral.view.*;
import java.time.*;
import java.time.format.*;
import java.util.Scanner;

public class BidderBidMenu extends AbstractMenu {

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM d, uuuu");

    @Override
    public String getHeading() {
        return dateTimeFormatter.format(LocalDateTime.now());
    }

    @Override
    public String getBody() {
        return "Available Actions:\n";
    }

    @Override
    public void onResponse(Scanner scan) {
//        int responseNum = Integer.parseInt(response);
//        switch (responseNum) {
//
//
//        }
    }
}
