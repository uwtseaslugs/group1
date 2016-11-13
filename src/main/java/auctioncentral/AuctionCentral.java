package auctioncentral;

import auctioncentral.model.*;
import auctioncentral.view.login.*;

public class AuctionCentral {

    public static ILoginManager loginManager = new LoginManager();
    public static ICalendar calendar = new Calendar();

    public static void main(String[] args) {
        new LoginUsernameMenu().show();
    }
}
