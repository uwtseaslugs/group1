import java.util.*;

public class AuctionCentral {

    public static User currentUser;
    public static Set<User> users;

    public static void main(String[] args) {
        users = loadUsers();
        new LoginMenu().show();
        if (currentUser instanceof Staff) {
            new StaffHomeMenu().show();
        }
    }

    private static Set<User> loadUsers() {
        Set<User> set = new HashSet<>();
        set.add(new Bidder("bidder1", "bidder1name"));
        set.add(new Staff("staff1", "staff1name"));
        set.add(new Contact("contact1", "contact1name"));
        return set;
    }
}
