package auctioncentral.model;

public abstract class User {

    protected String username;
    protected String name;

    protected User(String username, String name) {
        this.username = username;
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public abstract String getTitle();
}
