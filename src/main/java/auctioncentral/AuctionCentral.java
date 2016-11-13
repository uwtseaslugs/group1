package auctioncentral;

import auctioncentral.model.*;
import auctioncentral.view.login.*;

public class AuctionCentral {

    public static ILoginManager loginManager = new LoginManager();
    public static ICalendar calendar = new Calendar();

    public static void main(String[] args) {
        addAuctions();
        new LoginUsernameMenu().show();
    }

    // test
    private static void addAuctions() {
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.add(java.util.Calendar.DATE, 8);
        for (int i = 0; i < 12; i++) {
            c.add(java.util.Calendar.DATE, 1);
            calendar.addAuction(new Auction(new Contact("" + i, "" + i), c.getTime(), null, null));

        }
    }
}
