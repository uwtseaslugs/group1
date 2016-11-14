package auctioncentral.model;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
@RunWith(Suite.class)
@Suite.SuiteClasses({
        AuctionTest.class,
        CalendarTest.class,
        ItemTest.class,

})
public class testSuite {
}

//@RunWith(Suite.class)
//@Suite.SuiteClasses({
//        TestFeatureLogin.class,
//        TestFeatureLogout.class,
//        TestFeatureNavigate.class,
//        TestFeatureUpdate.class
//})
//
//public class FeatureTestSuite {
//    // the class remains empty,
//    // used only as a holder for the above annotations
//}