/*
    Deliverable 2 
    TCSS 360
    Created by: Sea Slugs
    
    ContactAddItemMenu is the scrren that the contact sees when the contact wants to add an auction Item. 
    Prompts for item infomation.
 */
package auctioncentral.view.contact;

import auctioncentral.model.*;
import auctioncentral.view.*;

import java.util.*;

public class ContactAddItemMenu extends AbstractMenu {

    private Auction auction;

    public ContactAddItemMenu(Auction auction, AbstractMenu parent) {
        super(parent);
        this.auction = auction;
    }

    @Override
    public String getHeading() {
        return "Adding new item to auction on " + auction.getDate().toString() + ".";
    }

    @Override
    public String getBody() {
        return "Enter name of item:";
    }

    @Override
    public void onResponse(Scanner response) {
        String name = response.nextLine();
        System.out.println("\nChoose condition of item:");
        for (int i = 0; i < ItemCondition.values().length; i++) {
            System.out.println("" + i + ": " + ItemCondition.values()[i].name());
        }
        System.out.print("> ");
        ItemCondition condition = ItemCondition.values()[response.nextInt()];
        response.nextLine();
        System.out.println("\nChoose size of item:");
        for (int i = 0; i < ItemSize.values().length; i++) {
            System.out.println("" + i + ": " + ItemSize.values()[i].name());
        }
        System.out.print("> ");
        ItemSize size = ItemSize.values()[response.nextInt()];
        response.nextLine();
        System.out.print("\nEnter minimum bid (positive integer):\n> ");
        int minimumBid = response.nextInt();
        response.nextLine();
        System.out.print("\nEnter donor name or leave blank to skip:\n> ");
        String donorName = response.nextLine();
        if (donorName.trim().isEmpty()) {
            donorName = null;
        }
        System.out.print("\nEnter description for bidders or leave blank to skip:\n> ");
        String description = response.nextLine();
        if (description.trim().isEmpty()) {
            description = null;
        }
        System.out.print("\nEnter comment for Auction Central staff or leave blank to skip:\n> ");
        String comment = response.nextLine();
        if (comment.trim().isEmpty()) {
            comment = null;
        }
        try {
            Item item = new Item(name, condition, size, minimumBid, donorName, description, comment);
            auction.addItem(item);
            System.out.print("\nItem successfully added. Press enter to continue\n> ");
        } catch (IllegalArgumentException e) {
            System.out.print("\nError inputting information. Press enter to return to previous menu\n> ");
        }
        response.nextLine();
        getParent().show();
    }
}
