/*
    TCSS 360
    Sea Slugs
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

    private static LoginManager myInst = null;

    public static LoginManager inst() {
        if (myInst == null) {
            myInst = new LoginManager();
        }
        return myInst;
    }

    public static void setInst(LoginManager theInst) {
        if (myInst != null)
            throw new IllegalStateException("Must call setInst before inst");
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

    @Override
    public void register(User user) {
        users.put(user.getUsername(), user);
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
