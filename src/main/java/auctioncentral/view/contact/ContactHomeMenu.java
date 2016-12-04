/*
    Deliverable 2 
    TCSS 360
    Created by: Sea Slugs
    
    ContactHomeMenu is the home screen of the contact. The contact and select to sumbit and request or edit auction.
 */
package auctioncentral.view.contact;

import auctioncentral.*;
import auctioncentral.model.*;
import auctioncentral.view.*;

import java.time.*;
import java.time.format.*;
import java.util.Scanner;
import java.util.Date;

public class ContactHomeMenu extends AbstractMenu {

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM d, uuuu");

    private Auction upcomingAuction;

    @Override
    public void show() {
        upcomingAuction = Calendar.inst().getAuctionsPastDate(new Date()).stream()
                .filter(a -> a.getContact().equals(LoginManager.inst().getCurrentUser()))
                .findFirst().orElse(null);
        super.show();
    }

    public String getHeading() {
        String heading =  dateTimeFormatter.format(LocalDateTime.now()) + ".\n" +
                ((Contact)LoginManager.inst().getCurrentUser()).getNonprofitName() + ".\n";
        if (upcomingAuction == null) {
            heading += "You do not have an upcoming auction.";
        } else {
            heading += "You have an upcoming auction on " + upcomingAuction.getDate().toString();
        }
        return heading;
    }

    @Override
    public String getBody() {
        return "What would you like to do?\n" +
                "1. Edit upcoming auction\n" +
                "2. Add Auction\n" +
                "3. Exit AuctionCentral";
    }

    @Override
    public void onResponse(Scanner scan) {
        switch (scan.nextLine()) {
            case "1":
                if (upcomingAuction == null) {
                    System.out.println("Error: You do not have any upcoming auctions\n> ");
                    onResponse(scan);
                } else {
                    new ContactEditAuctionMenu(upcomingAuction, this).show();
                }
                return;
            case "2":
                new ContactAddMenu(this).show();
                return;
            case "3":
                return;
            default:
                System.out.printf("Please enter a number 1 - 3.\n> ");
                onResponse(scan);
        }
    }
}
