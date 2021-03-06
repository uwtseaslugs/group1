/*
    Deliverable 2 
    TCSS 360
    Created by: Sea Slugs
    
   Auction class that takes in the contact, date, comments and # of items from the user. Also has a
   method to add items.
 */
package auctioncentral.model;

import java.io.*;
import java.util.*;
import java.util.Calendar;

/**
 * An Auction held by a non-profit. Contains Items to be sold.
 * @author Hunter, Jon
 */
public class Auction implements Serializable, Comparable<Auction> {

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

    /**
     *
     * @return the Contact who created and is handling this Auction
     */
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

    public static final int REMOVE_ITEM_MIN_DAYS_AWAY = 2;

    /**
     *
     * @param i item to check if you can remove it.
     * @return true or false if it follows business rules
     */
    public boolean canRemoveItem(Item i){
        if(i == null){
            return false;
        }
        Date now = new Date();
        java.util.Calendar jCalendar = Calendar.getInstance();
        jCalendar.setTime(now);
        jCalendar.add(java.util.Calendar.DATE, REMOVE_ITEM_MIN_DAYS_AWAY);
        return jCalendar.getTime().before(date) && items.contains(i);
    }

    /**
     *
     * @param i item to remove
     * @throws IllegalArgumentException if item cannot be removed
     */
    public void removeItem(Item i) {
        if (canRemoveItem(i)) {
            items.remove(i);
        } else{
            throw new IllegalArgumentException();
        }
    }

    /**
     *
     * @param auction another Auction
     * @return compares first by the dates and then randomly in case of ties
     */
    @Override
    public int compareTo(Auction auction) {
        int compareDates = getDate().compareTo(auction.getDate());
        if (compareDates == 0) {
            return Integer.compare(hashCode(), auction.hashCode());
        } else {
            return compareDates;
        }
    }
}

