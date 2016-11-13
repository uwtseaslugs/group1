package auctioncentral.view.staff;

import auctioncentral.view.*;

import java.time.*;
import java.time.format.*;
import java.util.Scanner;

public class StaffHomeMenu extends AbstractMenu {

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM d, uuuu");

    @Override
    public String getHeading() {
        return dateTimeFormatter.format(LocalDateTime.now()) + ".  Total number of upcoming auctions: ";
    }

    @Override
    public String getBody() {
        return "What would you like to do?\n" +
                "1. View calendar of upcoming auctions\n" +
                "2. Administrative functions\n" +
                "3. Exit AuctionCentral";
    }

    @Override
    public void onResponse(Scanner scan) {
        while (!scan.hasNextInt()) {
            System.out.printf("Please enter a number 1 - 3.\n> ");
        }
        int responseNum = scan.nextInt();
        switch (responseNum) {
            case 1:
                return;
            case 2:
                //new StaffAdminMenu().show();
                return;
            case 3:
                return;
        }
    }
}
