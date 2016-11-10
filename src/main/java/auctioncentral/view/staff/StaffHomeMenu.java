package auctioncentral.view.staff;

import auctioncentral.view.*;

import java.time.*;
import java.time.format.*;

public class StaffHomeMenu extends AbstractMenu {

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM d, uuuu");

    @Override
    public String getHeading() {
        return dateTimeFormatter.format(LocalDateTime.now()) + ".";
    }

    @Override
    public String getBody() {
        return "What would you like to do?\n" +
                "1. View calendar of upcoming auctions\n" +
                "2. Administrative functions\n" +
                "3. Exit AuctionCentral";
    }

    @Override
    public void onResponse(String response) {
        int responseNum = Integer.parseInt(response);
        switch (responseNum) {

        }
    }
}
