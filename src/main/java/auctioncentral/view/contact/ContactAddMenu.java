package auctioncentral.view.contact;

import auctioncentral.*;
import auctioncentral.model.*;
import auctioncentral.view.*;

import java.time.*;
import java.time.format.*;
import java.util.Calendar;
import java.util.*;

public class ContactAddMenu extends AbstractMenu {

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM d, uuuu");
    private Auction newAuction;
    private int year,month,day;
    private String comments;

    public ContactAddMenu(AbstractMenu parent) {
        super(parent);
    }

    @Override
    public String getHeading() {
        return dateTimeFormatter.format(LocalDateTime.now()) + ".";
    }

    @Override
    public String getBody() {
        return "Adding new auction\n";
    }

    @Override
    public void onResponse(Scanner response) {
        System.out.println("What year would you like this Auction?");
        year = response.nextInt();
        response.nextLine();
        System.out.println("What month would you like this Auction?");
        month = response.nextInt() - 1;
        response.nextLine();
        System.out.println("What day would you like this Auction?");
        day = response.nextInt();
        response.nextLine();
        System.out.println("Add Comments :");
        comments = response.nextLine();


        Calendar c = Calendar.getInstance();
        c.set(year, month, day);
//        System.out.println(c.getTime().toString());
        try {
            newAuction = new Auction((Contact) AuctionCentral.loginManager.getCurrentUser(), c.getTime(), comments, null);
            AuctionCentral.calendar.addAuction(newAuction);
        } finally {
            getParent().show();
        }
    }

    private void confirmation(){
        new CalendarView(AuctionCentral.calendar).show();
        System.out.println("Comfirmed Your Auction is added on :" + newAuction.getDate().toString());
    }
}
