package auctioncentral.model;

public class LoginManager implements ILoginManager {

    private User currentUser;

    public LoginManager() {

    }

    @Override
    public User getCurrentUser() {
        return currentUser;
    }

    @Override
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
