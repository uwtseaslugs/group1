/*
    Deliverable 2 
    TCSS 360
    Created by: Sea Slugs
    
    LoginManager class sets the current user.
 */
package auctioncentral.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Hunter
 */
public class LoginManager implements ILoginManager, Serializable {

    private transient User currentUser;

    private Map<String, User> users;

    public LoginManager() {
        users = new HashMap<>();
    }

    @Override
    public User getCurrentUser() {
        return currentUser;
    }

    @Override
    public void register(User user) {
        users.put(user.getName(), user);
    }

    @Override
    public User getUser(String username) {
        return users.get(username);
    }

    @Override
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
