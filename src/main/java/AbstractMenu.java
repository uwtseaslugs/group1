import java.util.*;

public abstract class AbstractMenu {

    protected AbstractMenu parent;

    protected AbstractMenu() {
        this(null);
    }

    protected AbstractMenu(AbstractMenu parent) {
        this.parent = parent;
    }

    public final AbstractMenu getParent() {
        return parent;
    }

    public final String getTitle() {
        return "AuctionCentral: the auctioneer for non-profit organizations.";
    }

    public final String getStatus() {
        User user = AuctionCentral.loginManager.getCurrentUser();
        if (user == null) {
            return "Not Logged in";
        }
        return user.getName() + " logged in as " + user.getTitle();
    }

    public abstract String getHeading();

    public abstract String getBody();

    public void show() {
        for (int i = 0; i < 6; i++) {
            System.out.println("");
        }
        System.out.println(getTitle());
        System.out.println(getStatus());
        System.out.println("");
        System.out.println(getHeading());
        System.out.println("");
        String body = getBody();
        System.out.println(body);
        int bodyLines = (int) body.chars().filter(c -> c == '\n').count();
        for (int i = 0; i < 22 - bodyLines; i++) {
            System.out.println("");
        }
        System.out.println("");
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        onResponse(scanner.next());
    }

    public abstract void onResponse(String response);
}
