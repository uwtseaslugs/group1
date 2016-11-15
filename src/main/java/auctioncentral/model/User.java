package auctioncentral.model;

import java.io.*;

public abstract class User implements Serializable {

    protected String username;
    protected String name;
    /**
     *
     * @param username
     * @param name
     */
    protected User(String username, String name) {
        this.username = username;
        this.name = name;
    }
    /**
     *
     * @return gets the username
     */
    public String getUsername() {
        return username;
    }
    /**
     * 
     * @return gets the name of user
     */
    public String getName() {
        return name;
    }

    public abstract String getTitle();
}
