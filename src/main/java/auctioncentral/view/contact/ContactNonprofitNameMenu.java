package auctioncentral.view.contact;

import auctioncentral.*;
import auctioncentral.model.*;
import auctioncentral.view.*;

import java.util.*;

public class ContactNonprofitNameMenu extends AbstractMenu {

    private String username;
    private String name;

    public ContactNonprofitNameMenu(String username, String name) {
        this.username = username;
        this.name = name;
    }

    @Override
    public String getHeading() {
        return "Logging in...";
    }

    @Override
    public String getBody() {
        return "Enter non-profit name:";
    }

    @Override
    public void onResponse(Scanner response) {
        AuctionCentral.loginManager.setCurrentUser(new Contact(username, name, response.nextLine()));
        new ContactHomeMenu().show();
    }
}
