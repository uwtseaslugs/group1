/*
    Deliverable 2 
    TCSS 360
    Created by: Sea Slugs
    
    Staff class takes in the username and name.
 */
package auctioncentral.model;

public class Staff extends User {

    public Staff(String username, String name) {
        super(username, name);
    }

    @Override
    public String getTitle() {
        return "Auction Central Staff Person";
    }
}
