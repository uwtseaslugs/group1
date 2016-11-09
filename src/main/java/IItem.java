import java.io.*;
import java.math.*;

public interface IItem extends Serializable {

    String getName();
    ItemCondition getCondition();
    ItemSize getSize();
    int getMinimumBid();

    String getDonorName();
    String getDescription();
    String getComment();

    BigDecimal getBid(Bidder bidder);
    void placeBid(Bidder bidder, BigDecimal price);
}
