/*
    Deliverable 2 
    TCSS 360
    Created by: Sea Slugs
    
    CalendarView is the view of the calendar with #ofday:#ofAuctions under the day.
 */
package auctioncentral.view;

import auctioncentral.model.ICalendar;
import auctioncentral.model.Calendar;

import java.util.Date;
import java.text.SimpleDateFormat;

public class CalendarView {
    private ICalendar myCal;
    private Date startDate;

    public String s = "";

    public String getText() {
        return s;
    }

    public CalendarView(ICalendar theCal) {
        myCal = theCal;
        startDate = new Date();
    }
    
    public void show() {
        s += ("   Su      M      T      W      T      F      S\n");

        int dayOfWeek = 1;
        dayOfWeek = printOutThisMonth(dayOfWeek);

        java.util.Calendar c = Calendar.getJavaCalendar();
        c.setTime(startDate);
        if (c.get(java.util.Calendar.DAY_OF_MONTH) > 8) {
            printOutNextMonth(dayOfWeek);
        }
    }

    private int printOutThisMonth(int dayOfWeek) {
        java.util.Calendar c = Calendar.getJavaCalendar();
        c.setTime(startDate);
        String curMonth = new SimpleDateFormat("MMMM").format(c.getTime());
        s += "                    [" + curMonth + "]\n";

        // Print out front spaces for days already occured this week
        for (; dayOfWeek < c.get(java.util.Calendar.DAY_OF_WEEK); dayOfWeek++) {
            s += ("|      ");
        }

        // Print out upcoming events
        int dayOn = c.get(java.util.Calendar.DAY_OF_MONTH);
        int startDayOfMonth = dayOn;
        for (; dayOn <= myCal.getNumberOfDaysForCurrentMonth(); dayOn++, dayOfWeek++) {
            if (dayOfWeek == 8) {
                s += ("|\n");
                dayOfWeek = 1;
            }
            int numAuctionsOnDate = myCal.getNumberOfAuctionsOnDate(Calendar.addDaysToDate(startDate, dayOn - startDayOfMonth));
            if (numAuctionsOnDate < 0)
                numAuctionsOnDate = 0;
            
            s += String.format("| %2d:%1d ", dayOn, numAuctionsOnDate);
        }

        // Print out rest of month
        for (int endWeek = dayOfWeek; endWeek < 8; endWeek++) {
            s += ("|      ");
        }

        s += ("|\n");
        return dayOfWeek;
    }

    private void printOutNextMonth(int dayOfWeek) {
        java.util.Calendar c = Calendar.getJavaCalendar();
        c.setTime(startDate);
        c.set(java.util.Calendar.DAY_OF_MONTH, 1);
        c.add(java.util.Calendar.MONTH, 1);

        String nextMonth = new SimpleDateFormat("MMMM").format(c.getTime());
        
        // Print out begining of next month
        s += String.format("                    [%s]\n", nextMonth);

        // Print out front spaces for days already occured this week
        for (int startWeek = 1; startWeek < dayOfWeek; startWeek++) {
            s += ("|      ");
        }
        
        // Print out upcoming events
        int dayOn = 1;

        java.util.Calendar cPrev = Calendar.getJavaCalendar();
        cPrev.setTime(startDate);

        for (; dayOn < cPrev.get(java.util.Calendar.DAY_OF_MONTH); dayOn++, dayOfWeek++) {
            if (dayOfWeek == 8) {
                s += ("|\n");
                dayOfWeek = 1;
            }
            int numAuctionsOnDate = myCal.getNumberOfAuctionsOnDate(Calendar.addDaysToDate(c.getTime(), dayOn - 1));
            if (numAuctionsOnDate < 0)
                numAuctionsOnDate = 0;
            
            s += String.format("| %2d:%1d ", dayOn, numAuctionsOnDate);
        }

        // Print out rest of month
        for (int endWeek = dayOfWeek; endWeek < 8; endWeek++) {
            s += ("|      ");
        }

        s += ("|\n");
    }
}
