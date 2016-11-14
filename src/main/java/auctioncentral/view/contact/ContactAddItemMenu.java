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
        return "";
    }

    @Override
    public void onResponse(Scanner response) {
        response.nextLine();
        getParent().show();
    }
}
