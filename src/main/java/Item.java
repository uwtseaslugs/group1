import java.math.*;
import java.util.*;

public class Item implements IItem {

    private String name;
    private ItemCondition condition;
    private ItemSize size;
    private int minimumBid;
    private String donorName;
    private String description;
    private String comment;
    private Map<Bidder, BigDecimal> bids;

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

    @Override
    public int getMinimumBid() {
        return minimumBid;
    }

    @Override
    public String getDonorName() {
        return donorName;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getComment() {
        return comment;
    }

    @Override
    public BigDecimal getBid(Bidder bidder) {
        if (bidder == null) {
            throw new IllegalArgumentException();
        }
        return bids.get(bidder);
    }

    @Override
    public void placeBid(Bidder bidder, BigDecimal price) {
        if (bidder == null || price == null || price.compareTo(BigDecimal.valueOf(minimumBid)) <= 0) {
            throw new IllegalArgumentException();
        }
        bids.put(bidder, price);
    }
}
