package auctioncentral.view.contact;
import auctioncentral.*;
import auctioncentral.model.*;
import auctioncentral.view.*;

import java.time.*;
import java.time.format.*;
import java.util.*;
import java.util.Calendar;

public class ContactAddMenu {
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM d, uuuu");
    private Auction newAuction;
    private Contact contact;
    private int year,month,day;
    private String comments;
    public void show() {
    Calendar c = Calendar.getInstance();
    contact = new Contact(AuctionCentral.loginManager.getCurrentUser().getUsername(),AuctionCentral.
            loginManager.getCurrentUser().getName());

        System.out.println( dateTimeFormatter.format(LocalDateTime.now()) + ".  Total number of upcoming auctions: "
                + AuctionCentral.calendar.getAuctionsPastDate(new Date()).size());
        Scanner scan = new Scanner(System.in);
        settingAuctionParameters(scan);
        c.set(year,month,day);
        System.out.println(c.getTime().toString());
        try{
            newAuction = new Auction (contact,c.getTime(),comments,0);
        }catch(IllegalArgumentException e){
            System.out.println("Can not add your Auction\n"+"Moving Back to Contact Homepage...\n");
            new ContactHomeMenu().show();
        }
            AuctionCentral.calendar.addAuction(newAuction);
        //testing if I can add Auction
             confirmation();
    }
    // Sets
    private void settingAuctionParameters(Scanner scan){
        System.out.println("What year would you like this Auction?");
        year = scan.nextInt() - 1;
        System.out.println("What month would you like this Auction?");
        month = scan.nextInt() - 1;
        System.out.println("What day would you like this Auction?");
        day = scan.nextInt();
        System.out.println("Add Comments :");
        comments = scan.next();

    }
    private void confirmation(){
        new CalendarView(AuctionCentral.calendar).show();
        System.out.println("Comfirmed Your Auction is added on :" + newAuction.getDate().toString());
    }
}
