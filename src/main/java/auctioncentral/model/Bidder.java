/**
 *  Created by: Sea Slugs
 *
 *  Bidder class takes in username and name of the bidder and has a git title class
 */
package auctioncentral.model;

public class Bidder extends User {

    public Bidder(String username, String name) {
        super(username, name);
    }

    @Override
    public String getTitle() {
        return "Bidder";
    }
}
