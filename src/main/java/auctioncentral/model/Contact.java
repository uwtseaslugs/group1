/*
    Deliverable 2 
    TCSS 360
    Created by: Sea Slugs
    
    Contact class takes in username and name of contact.
 */
package auctioncentral.model;

public class Contact extends User {

    private String nonprofitName;
    
    /**
     *
     * @param username
     * @param name
     */
    public Contact(String username, String name) {
        this(username, name, "");
    }

    /**
     *
     * @param username
     * @param name
     */
    public Contact(String username, String name, String nonprofitName) {
        super(username, name);
        this.nonprofitName = nonprofitName;
    }
    
    /**
     *
     * @return nonprofit name
     */
    public String getNonprofitName() {
        return nonprofitName;
    }

    /**
     *
     * @return gets the title
     */
    @Override
    public String getTitle() {
        return "Contact";
    }
}
