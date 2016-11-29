/*
    Deliverable 2 
    TCSS 360
    Created by: Sea Slugs
    
   Interface for LoginManager
 */
package auctioncentral.model;

public interface ILoginManager {

    User getCurrentUser();
    void register(User user);
    User getUser(String username);
    void setCurrentUser(User currentUser);
}
