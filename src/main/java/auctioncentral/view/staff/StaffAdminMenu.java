/*
    Deliverable 2 
    TCSS 360
    Created by: Sea Slugs
    
    StaffHomeMenu is the home screen for the staff.
 */
package auctioncentral.view.staff;

import auctioncentral.AuctionCentral;
import auctioncentral.view.AbstractMenu;
import auctioncentral.model.Calendar;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class StaffAdminMenu extends AbstractMenu {

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM d, uuuu");

    @Override
    public String getHeading() {
        return dateTimeFormatter.format(LocalDateTime.now()) + ".  Total number of upcoming auctions: " +
                Calendar.inst().getAuctionsPastDate(new Date()).size();
    }

    @Override
    public String getBody() {
        return "What would you like to do in the Admin Menu?\n" +
                "1. Add Maximum number of Auctions\n" +
                "2. Exit AuctionCentral";
    }

    @Override
    public void onResponse(Scanner scan) {
        switch (scan.nextLine()) {
            case "1":
                new StaffAddAuctionsMenu().show();
                return;
            case "2":
                return;
            case "3":
                return;
            default:
                System.out.printf("Please enter a number 1 - 3.\n> ");
                onResponse(scan);
        }
    }
}
