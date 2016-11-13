package auctioncentral.model;

import java.util.*;

/**
 * An Auction held by a non-profit. Contains Items to be sold.
 */
public class Auction {

    private Date date;
    private String comments;
    private Integer approxNumOfItems;
    private Contact contact;
    private List<Item> items;

    /**
     *
     * @param contact (required) Contact representing the non-profit holding the auction
     * @param date (required) date and time of the auction
     * @param comments (optional)
     * @param approxNumOfItems (optional)
     * @throws IllegalArgumentException if date is null, contact is null, or approxNumOfItems is <= 0
     */
    public Auction(Contact contact, Date date, String comments, Integer approxNumOfItems){
        if (date == null || contact == null || (approxNumOfItems != null && approxNumOfItems <= 0)) {
            throw new IllegalArgumentException();
        }
        this.date = date;
        this.comments = comments;
        this.approxNumOfItems = approxNumOfItems;
        this.contact = contact;
        this.items = new ArrayList<>();
    }

    /**
     *
     * @return date and time of this auction
     */
    public Date getDate() {
        return date;
    }

    public Contact getContact() {
        return contact;
    }

    /**
     *
     * @return all items being sold in this auction
     */
    public List<Item> getItems(){
        return items;
    }

    /**
     *
     * @param newItem item to be added to this auction
     * @throws IllegalArgumentException if this auction already contains newItem
     */
    public void addItem(Item newItem){
        if (items.contains(newItem)) {
            throw new IllegalArgumentException();
        }
        items.add(newItem);
    }
}

