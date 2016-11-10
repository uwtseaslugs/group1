package auctioncentral.view.login;

import auctioncentral.view.*;

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
    public void onResponse(String response) {
        new LoginNameMenu(response).show();
    }
}
