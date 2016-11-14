package auctioncentral.view.contact;

import auctioncentral.*;
import auctioncentral.model.*;
import auctioncentral.view.*;

import java.time.*;
import java.time.format.*;
import java.util.Calendar;
import java.util.*;
import java.text.*;

public class ContactAddMenu extends AbstractMenu {

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM d, uuuu");
    private Auction newAuction;
    private  Date dateParsed;
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
       System.out.println("What date would you like this auction?\n");
        date = response.next();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        try{
             dateParsed = format.parse(date);
        }catch (ParseException e)
        {
            e.printStackTrace();
        }
        try {
            newAuction = new Auction((Contact) AuctionCentral.loginManager.getCurrentUser(), dateParsed, comments, null);
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
