package auctioncentral.model;

public class Contact extends User {

    private String nonprofitName;

    public Contact(String username, String name) {
        this(username, name, "");
    }

    public Contact(String username, String name, String nonprofitName) {
        super(username, name);
        this.nonprofitName = nonprofitName;
    }

    public String getNonprofitName() {
        return nonprofitName;
    }

    @Override
    public String getTitle() {
        return "Contact";
    }
}
