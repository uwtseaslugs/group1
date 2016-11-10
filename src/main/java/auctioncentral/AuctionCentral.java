package auctioncentral;

import auctioncentral.model.*;
import auctioncentral.view.login.*;

public class AuctionCentral {

    public static ILoginManager loginManager = new LoginManager();

    public static void main(String[] args) {
        new LoginUsernameMenu().show();
    }
}
