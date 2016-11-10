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
