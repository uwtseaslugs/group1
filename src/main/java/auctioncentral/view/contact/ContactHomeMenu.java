package auctioncentral.view.contact;

import auctioncentral.view.AbstractMenu;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ContactHomeMenu extends AbstractMenu {
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM d, uuuu");

    @Override
    public String getHeading() {
        return dateTimeFormatter.format(LocalDateTime.now()) + ".  Total number of upcoming auctions: ";
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
    public void onResponse(String response) {
        int responseNum = Integer.parseInt(response);
        switch (responseNum) {
            case 1:
                return;
            case 2:
                return;
            case 3:
                return;
            case 4:
                return;
        }
    }
}
