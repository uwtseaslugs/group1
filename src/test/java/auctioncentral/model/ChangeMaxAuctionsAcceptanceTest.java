package auctioncentral.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Date;

/**
 *As a staff person for Auction Central, I want to change the maximum number of future auctions that the system is allowed to schedule at any one time.
  
 */
public class ChangeMaxAuctionsAcceptanceTest {

    private Calendar emptyCalendar;
    private Staff staff1;

    @Before
    public void setup() {
        staff1 = new Staff("username1", "staff1");
        emptyCalendar = new Calendar();
    }

    /*
     * BR: Can change max auctions to a number higher than 0.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testChangeMaxAuctionAboveZero(){
        emptyCalendar.changeMaxAuctions(1);
        assertEquals(emptyCalendar.inst().getMaxAuctions(), 1);
    }

    /*
    * BR: Can change max auctions to 0.
    */
    @Test(expected = IllegalArgumentException.class)
    public void testChangeMaxAuctionZero(){
        emptyCalendar.changeMaxAuctions(0);
        assertEquals(emptyCalendar.inst().getMaxAuctions(), 0);
    }

    /*
    * BR: Cannot change max auctions to a number lesser than 0.
    */
    @Test(expected = IllegalArgumentException.class)
    public void testChangeMaxAuctionLessThanZero(){
        emptyCalendar.changeMaxAuctions(-1);
        assertEquals(emptyCalendar.inst().getMaxAuctions(), 0);
    }

}
