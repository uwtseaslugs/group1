public class Staff extends User {

    public Staff(String username, String name) {
        super(username, name);
    }

    @Override
    public String getTitle() {
        return "Auction Central Staff Person";
    }
}
