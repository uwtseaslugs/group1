public class LoginMenu extends AbstractMenu {

    @Override
    public String getHeading() {
        return "Logging in";
    }

    @Override
    public String getBody() {
        return "Enter username:";
    }

    @Override
    public void onResponse(String response) {
        AuctionCentral.currentUser = AuctionCentral.users.stream()
                .filter(u -> u.getUsername().equals(response))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException());
    }
}
