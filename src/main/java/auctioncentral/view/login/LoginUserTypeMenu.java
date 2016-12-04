/*
    Deliverable 2 
    TCSS 360
    Created by: Sea Slugs
    
    LoginUserTypeMenu asks the user what type of user they are.
 */
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
        switch (scan.nextLine()) {
            case "1":
                LoginManager.inst().setCurrentUser(new Staff(username, name));
                new StaffHomeMenu().show();
                return;
            case "2":
                new ContactNonprofitNameMenu(username, name).show();
                return;
            case "3":
                LoginManager.inst().setCurrentUser(new Bidder(username, name));
                new BidderHomeMenu(this).show();
                return;
            default:
                System.out.print("Please enter a number 1 - 3.\n> ");
                onResponse(scan);
        }
    }
}
