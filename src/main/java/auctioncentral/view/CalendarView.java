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

    public CalendarView(ICalendar theCal) {
        myCal = theCal;
        startDate = new Date();
    }
    
    public void show() {
        System.out.println("   Su      M      T      W      T      F      S");

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
        System.out.printf("                    [%s]\n", curMonth);

        // Print out front spaces for days already occured this week
        for (; dayOfWeek < c.get(java.util.Calendar.DAY_OF_WEEK); dayOfWeek++) {
            System.out.printf("|      ");
        }

        // Print out upcoming events
        int dayOn = c.get(java.util.Calendar.DAY_OF_MONTH);
        int startDayOfMonth = dayOn;
        for (; dayOn <= myCal.getNumberOfDaysForCurrentMonth(); dayOn++, dayOfWeek++) {
            if (dayOfWeek == 8) {
                System.out.printf("|\n");
                dayOfWeek = 1;
            }
            int numAuctionsOnDate = myCal.getNumberOfAuctionsOnDate(Calendar.addDaysToDate(startDate, dayOn - startDayOfMonth));
            if (numAuctionsOnDate < 0)
                numAuctionsOnDate = 0;
            
            System.out.printf("| %2d:%1d ", dayOn, numAuctionsOnDate);
        }

        // Print out rest of month
        for (int endWeek = dayOfWeek; endWeek < 8; endWeek++) {
            System.out.printf("|      ");
        }

        System.out.printf("|\n");
        return dayOfWeek;
    }

    private void printOutNextMonth(int dayOfWeek) {
        java.util.Calendar c = Calendar.getJavaCalendar();
        c.setTime(startDate);
        c.set(java.util.Calendar.DAY_OF_MONTH, 1);
        c.add(java.util.Calendar.MONTH, 1);

        String nextMonth = new SimpleDateFormat("MMMM").format(c.getTime());
        
        // Print out begining of next month
        System.out.printf("                    [%s]\n", nextMonth);

        // Print out front spaces for days already occured this week
        for (int startWeek = 1; startWeek < dayOfWeek; startWeek++) {
            System.out.printf("|      ");
        }
        
        // Print out upcoming events
        int dayOn = 1;

        java.util.Calendar cPrev = Calendar.getJavaCalendar();
        cPrev.setTime(startDate);

        for (; dayOn < cPrev.get(java.util.Calendar.DAY_OF_MONTH); dayOn++, dayOfWeek++) {
            if (dayOfWeek == 8) {
                System.out.printf("|\n");
                dayOfWeek = 1;
            }
            int numAuctionsOnDate = myCal.getNumberOfAuctionsOnDate(Calendar.addDaysToDate(c.getTime(), dayOn - 1));
            if (numAuctionsOnDate < 0)
                numAuctionsOnDate = 0;
            
            System.out.printf("| %2d:%1d ", dayOn, numAuctionsOnDate);
        }

        // Print out rest of month
        for (int endWeek = dayOfWeek; endWeek < 8; endWeek++) {
            System.out.printf("|      ");
        }

        System.out.printf("|\n");
    }
}
