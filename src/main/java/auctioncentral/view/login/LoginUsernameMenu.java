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
