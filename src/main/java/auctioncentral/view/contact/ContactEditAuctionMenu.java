/*
    Deliverable 2 
    TCSS 360
    Created by: Sea Slugs
    
    ContactEditAuctionMenu is when the contact selects the option to edit auctions.
 */
package auctioncentral.view.contact;

import auctioncentral.model.*;
import auctioncentral.view.*;

import java.util.*;

public class ContactEditAuctionMenu extends AbstractMenu {

    private Auction auction;

    public ContactEditAuctionMenu(Auction auction, AbstractMenu parent) {
        super(parent);
        this.auction = auction;
    }

    @Override
    public String getHeading() {
        return "Editing upcoming auction scheduled on " + auction.getDate().toString();
    }

    @Override
    public String getBody() {
        String body = "Number of items: " + auction.getItems().size();
        body += "\n\nWhat would you like to do?\n" +
                "1. Add new Item\n" +
                "2. Go back\n" +
                "3. Exit AuctionCentral";
        return body;
    }

    @Override
    public void onResponse(Scanner scan) {
        switch (scan.nextLine()) {
            case "1":
                new ContactAddItemMenu(auction, this).show();
                return;
            case "2":
                getParent().show();
                return;
            case "3":
                return;
            default:
                System.out.printf("Please enter a number 1 - 3.\n> ");
                onResponse(scan);
        }
    }
}
