/*
    Deliverable 2 
    TCSS 360
    Created by: Sea Slugs

 */
package auctioncentral.model;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
@RunWith(Suite.class)
@Suite.SuiteClasses({
        AuctionTest.class,
        CalendarTest.class,
        ItemTest.class,
        AddItemAcceptanceTest.class,
        CancelAuctionAcceptanceTest.class,
        PlaceBidAcceptanceTest.class,
        RemoveItemAccpetanceTest.class,
        SubmitAuctionAcceptanceTest.class,

})
public class TestSuite {
}
