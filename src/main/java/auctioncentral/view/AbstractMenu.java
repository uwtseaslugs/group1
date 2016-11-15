package auctioncentral.view;

import auctioncentral.*;
import auctioncentral.model.*;

import java.util.*;

public abstract class AbstractMenu {

    protected AbstractMenu parent;

    protected AbstractMenu() {
        this(null);
    }
    /**
     * 
     * @param parent
     */
    protected AbstractMenu(AbstractMenu parent) {
        this.parent = parent;
    }
    /**
     * 
     * @return gets the parent
     */
    public final AbstractMenu getParent() {
        return parent;
    }
    /**
     * 
     * @return gets the name of nonprofit
     */
    public final String getTitle() {
        return "AuctionCentral: the auctioneer for non-profit organizations.";
    }
    /**
     * 
     * @return returns the status of the user
     */
    public final String getStatus() {
        User user = AuctionCentral.loginManager.getCurrentUser();
        if (user == null) {
            return "Not Logged in";
        }
        return user.getName() + " logged in as " + user.getTitle();
    }

    public abstract String getHeading();

    public abstract String getBody();
    /**
     *  Show() is the template for the menus
     */
    public void show() {
        clearPreviousScreen();
        System.out.println(getTitle());
        System.out.println(getStatus());
        System.out.println("");
        System.out.println(getHeading());
        System.out.println("");
        String body = getBody();
        System.out.println(body);
        int bodyLines = (int) body.chars().filter(c -> c == '\n').count();
        System.out.println("");
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        onResponse(scanner);
    }
    /**
     * clears the screen.
     */
    public final void clearPreviousScreen() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")){
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            for (int i = 0; i < 6; i++) {
                System.out.println("");
            }
        }
    }

    public abstract void onResponse(Scanner response);
}
