/*
    Deliverable 2 
    TCSS 360
    Created by: Sea Slugs
    
    LoginUsernameMenu prompts user for their username.
 */
package auctioncentral.view.login;

import auctioncentral.view.*;

import java.util.Scanner;

public class LoginUsernameMenu extends AbstractMenu {

    @Override
    public String getHeading() {
        return "Logging in...";
    }

    @Override
    public String getBody() {
        return "Enter username:";
    }

    @Override
    public void onResponse(Scanner scan) {
        new LoginNameMenu(scan.nextLine()).show();
    }
}
