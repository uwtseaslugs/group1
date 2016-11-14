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
        System.out.print("\nEnter donor name or -1 to leave out:\n> ");
        String donorName = response.nextLine();
        if (donorName.equals("-1")) {
            donorName = null;
        }
        System.out.print("\nEnter description for bidders or -1 to leave out:\n> ");
        String description = response.nextLine();
        if (description.equals("-1")) {
            description = null;
        }
        System.out.print("\nEnter comment for Auction Central staff or -1 to leave out:\n> ");
        String comment = response.nextLine();
        if (comment.equals("-1")) {
            comment = null;
        }
        try {
            Item item = new Item(name, condition, size, minimumBid, donorName, description, comment);
            auction.addItem(item);
            System.out.print("\nItem successfully added. Enter anything to continue\n> ");
        } catch (IllegalArgumentException e) {
            System.out.print("\nError inputting information. Enter anything to return to previous menu\n> ");
        }
        response.nextLine();
        getParent().show();
    }
}
