package auctioncentral.model;

public interface ILoginManager {

    User getCurrentUser();
    void setCurrentUser(User currentUser);
}
