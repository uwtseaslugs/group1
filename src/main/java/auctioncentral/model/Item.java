package auctioncentral.model;

import java.math.*;
import java.util.*;

/**
 * An inventory item in an auction
 */
public class Item implements IItem {

    private String name;
    private ItemCondition condition;
    private ItemSize size;
    private int minimumBid;
    private String donorName;
    private String description;
    private String comment;
    private Map<Bidder, BigDecimal> bids;

    /**
     *
     * @param name (required)
     * @param condition (required)
     * @param size (required)
     * @param minimumBid (required) must be greater than 0
     * @param donorName (optional)
     * @param description (optional) description for bidders
     * @param comment (optional) comment for Auction Central staff
     * @throws IllegalArgumentException if name is null, condition is null, size is null, or minimum bid is less than or equal to 0
     */
    public Item(String name, ItemCondition condition, ItemSize size, int minimumBid,
                String donorName, String description, String comment) {
        if (name == null || condition == null || size == null || minimumBid <= 0) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.condition = condition;
        this.size = size;
        this.minimumBid = minimumBid;
        this.donorName = donorName;
        this.description = description;
        this.comment = comment;
        this.bids = new HashMap<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ItemCondition getCondition() {
        return condition;
    }

    @Override
    public ItemSize getSize() {
        return size;
    }

    /**
     *
     * @return minimum acceptable bid
     */
    @Override
    public int getMinimumBid() {
        return minimumBid;
    }

    @Override
    public String getDonorName() {
        return donorName;
    }

    /**
     *
     * @return description for bidders
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     *
     * @return comment for Auction Central staff
     */
    @Override
    public String getComment() {
        return comment;
    }

    /**
     *
     * @param bidder the bidder who placed the bid
     * @return the price of the bid or null if the bidder has not placed a bid
     * @throws IllegalArgumentException if bidder is null
     */
    @Override
    public BigDecimal getBid(Bidder bidder) {
        if (bidder == null) {
            throw new IllegalArgumentException();
        }
        return bids.get(bidder);
    }

    /**
     *
     * @param bidder bidder who is placing the bid
     * @param price the price of the bid
     * @throws IllegalArgumentException if bidder is null, price is null, price is less than the minimum bid,
     *         or bidder has already placed a bid before
     */
    @Override
    public void placeBid(Bidder bidder, BigDecimal price) {
        if (bidder == null || price == null || price.compareTo(BigDecimal.valueOf(minimumBid)) < 0 ||
                bids.containsKey(bidder)) {
            throw new IllegalArgumentException();
        }
        bids.put(bidder, price);
    }
}
