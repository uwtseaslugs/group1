package auctioncentral.view.login;

import auctioncentral.*;
import auctioncentral.model.*;
import auctioncentral.view.*;
import auctioncentral.view.bidder.*;
import auctioncentral.view.contact.*;
import auctioncentral.view.staff.*;

import java.util.*;

public class LoginUserTypeMenu extends AbstractMenu {

    private String username;
    private String name;

    public LoginUserTypeMenu(String username, String name) {
        this.username = username;
        this.name = name;
    }

    @Override
    public String getHeading() {
        return "Logging in...";
    }

    @Override
    public String getBody() {
        return "Are you a...\n" +
                "1. Staff\n" +
                "2. Contact\n" +
                "3. Bidder";
    }

    @Override
    public void onResponse(Scanner scan) {
        while (!scan.hasNextInt()) {
            System.out.printf("Please input a number 1 - 3.\n>");
        }
        int responseInt = scan.nextInt();
        switch (responseInt) {
            case 1:
                AuctionCentral.loginManager.setCurrentUser(new Staff(username, name));
                new StaffHomeMenu().show();
                return;
            case 2:
                AuctionCentral.loginManager.setCurrentUser(new Contact(username, name));
                new ContactHomeMenu().show();
                return;
            case 3:
                AuctionCentral.loginManager.setCurrentUser(new Bidder(username, name));
                new BidderHomeMenu().show();
                return;
            default:
        }
    }
}
