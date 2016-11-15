/*
    Deliverable 2 
    TCSS 360
    Created by: Sea Slugs
    
    ContactAddMenu is the screen that lets the contact sumbit and auction request.
 */
package auctioncentral.view.contact;

import auctioncentral.*;
import auctioncentral.model.*;
import auctioncentral.view.*;

import java.text.*;
import java.time.*;
import java.time.format.*;
import java.util.*;

public class ContactAddMenu extends AbstractMenu {

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM d, uuuu");
    private Auction newAuction;
    private  Date dateParsed;
    private String dateAndTime;
    private String comments;
    private int amountOfItems;

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
        System.out.println("What date and Time would you like this auction?\n");
        dateAndTime = response.nextLine();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh a");
        try{
            dateParsed = format.parse(dateAndTime);
        }catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("Enter in the Comments:");
        comments = response.nextLine();
        System.out.println("Enter in the Approx # of Items:");
        amountOfItems = response.nextInt();
        response.nextLine();
        try {
            newAuction = new Auction((Contact) AuctionCentral.loginManager.getCurrentUser(), dateParsed, comments, amountOfItems);
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
