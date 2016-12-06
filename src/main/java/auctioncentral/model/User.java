/*
    TCSS 360
    Created by: Sea Slugs
 */
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
     * @return the name of user
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return a description of the type of user
     */
    public abstract String getTitle();

    @Override
    public boolean equals(Object obj) {
        return obj instanceof User && ((User)obj).getUsername().equals(getUsername());
    }
}
