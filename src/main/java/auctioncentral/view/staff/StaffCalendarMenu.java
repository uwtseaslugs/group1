/*
    Deliverable 2 
    TCSS 360
    Created by: Sea Slugs
    
   StaffCalendarMenu shows the staff the calendar.
 */
package auctioncentral.view.staff;

import auctioncentral.*;
import auctioncentral.view.*;
import auctioncentral.model.Calendar;

import java.time.*;
import java.time.format.*;
import java.util.*;

public class StaffCalendarMenu extends AbstractMenu {

    public StaffCalendarMenu(AbstractMenu parent) {
        super(parent);
    }

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM d, uuuu");

    @Override
    public String getHeading() {
        return dateTimeFormatter.format(LocalDateTime.now()) + ".  Total number of upcoming auctions: " +
                Calendar.inst().getAuctionsPastDate(new Date()).size();
    }

    @Override
    public String getBody() {
        new CalendarView(Calendar.inst()).show();
        return "\nSpecify a day to view (enter the two digit date), or -1 to go back";
    }

    @Override
    public void onResponse(Scanner scan) {
        String response = scan.nextLine();
        if (response.equals("-1")) {
            parent.show();
        }
    }
}
