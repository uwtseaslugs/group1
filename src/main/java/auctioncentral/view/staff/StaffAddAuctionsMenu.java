/*
    Deliverable 2 
    TCSS 360
    Created by: Sea Slugs
    
    StaffHomeMenu is the home screen for the staff.
 */
package auctioncentral.view.staff;

import auctioncentral.AuctionCentral;
import auctioncentral.view.AbstractMenu;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class StaffAddAuctionsMenu extends AbstractMenu {

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM d, uuuu");

    @Override
    public String getHeading() {
        return dateTimeFormatter.format(LocalDateTime.now()) + ".  Total number of upcoming auctions: " +
                AuctionCentral.calendar.getAuctionsPastDate(new Date()).size();
    }

    @Override
    public String getBody() {
        return "How much do you want to add";
    }

    @Override
    public void onResponse(Scanner scan) {
        int add = scan.nextInt();
        AuctionCentral.calendar.addMaxAuctions(add);
        System.out.print(AuctionCentral.calendar.getMaxAuctions());
    }
}
