package auctioncentral.view.login;

import auctioncentral.view.*;

public class LoginNameMenu extends AbstractMenu {

    private String username;

    public LoginNameMenu(String username) {
        this.username = username;
    }

    @Override
    public String getHeading() {
        return "Logging in...";
    }

    @Override
    public String getBody() {
        return "Enter name:";
    }

    @Override
    public void onResponse(String response) {
        new LoginUserTypeMenu(username, response).show();
    }
}
