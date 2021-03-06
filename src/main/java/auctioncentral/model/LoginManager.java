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
public class LoginManager extends SerializeOnExit implements ILoginManager, Serializable {

    private final long serialVersionUID = 4433452L;

    private static LoginManager myInst = null;
    public static LoginManager inst() {
        if (myInst == null) {
            myInst = new LoginManager();
        }
        return myInst;
    }

    public static void setInstance(LoginManager theInst) {
        if (myInst != null)
            throw new IllegalStateException("Must call setInstance before inst");
        myInst = theInst;
    }

    private transient User currentUser;

    public Map<String, User> users;

    public LoginManager() {
        users = new HashMap<>();
    }

    @Override
    public String getSerializedName() {
        return "loginmanager";
    }

    /**
     *
     * @return the User currently logged in
     */
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

    /**
     *
     * @param currentUser
     */
    @Override
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
