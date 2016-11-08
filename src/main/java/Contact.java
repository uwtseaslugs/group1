public class Contact extends User {

    public Contact(String username, String name) {
        super(username, name);
    }

    @Override
    public String getTitle() {
        return "Contact";
    }
}
