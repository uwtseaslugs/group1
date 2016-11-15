/*
    Deliverable 2 
    TCSS 360
    Created by: Sea Slugs
    
   Interface for LoginManager
 */
package auctioncentral.model;

public interface ILoginManager {

    User getCurrentUser();
    void setCurrentUser(User currentUser);
}
