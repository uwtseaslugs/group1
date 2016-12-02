/*
    TCSS 360
    Created by: Sea Slugs
 */
package auctioncentral.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles the authentication and storage of Users
 * @author Hunter
 */
public class LoginManager implements ILoginManager, Serializable {

    private static LoginManager myInst = null;

    public static LoginManager getInstance() {
        if (myInst == null) {
            myInst = new LoginManager();
        }
        return myInst;
    }

    public static void setInstance(LoginManager theInst) {
        if (myInst != null)
            throw new IllegalStateException("Must call setInstance before getInstance");
        myInst = theInst;
    }

    private transient User currentUser;

    private Map<String, User> users;

    public LoginManager() {
        users = new HashMap<>();
    }

    @Override
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     *
     * @param user a new User for registration
     */
    @Override
    public void register(User user) {
        users.put(user.getUsername(), user);
    }

    /**
     *
     * @param username of a previously registered User
     * @return the User with the given username or null if no such User exists
     */
    @Override
    public User getUser(String username) {
        return users.get(username);
    }

    @Override
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
