package auctioncentral.view.contact;

import auctioncentral.*;
import auctioncentral.view.*;

import java.time.*;
import java.time.format.*;
import java.util.*;

public class ContactHomeMenu extends AbstractMenu {
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM d, uuuu");

    public String getHeading() {
        return dateTimeFormatter.format(LocalDateTime.now()) + ".  Total number of upcoming auctions: " +
                AuctionCentral.calendar.getAuctionsPastDate(new Date()).size();
    }

    @Override
    public String getBody() {
        return "What would you like to do?\n" +
                "1. View calendar of upcoming auctions\n" +
                "2. Add Auction\n" +
                "3. View My Auction Items\n"+
                "4. Exit AuctionCentral";
    }

    @Override
    public void onResponse(Scanner scan) {
        switch (scan.nextLine()) {
            case "1":
                return;
            case "2":
                new ContactAddMenu(this).show();
                return;
            case "3":
                return;
            case "4":
                return;
            default:
                System.out.printf("Please enter a number 1 - 4.\n> ");
                onResponse(scan);
        }
    }
}
