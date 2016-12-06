package auctioncentral.gui;

import auctioncentral.model.Contact;
import auctioncentral.model.LoginManager;
import auctioncentral.model.User;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Observable;

public class StatusBorder extends AbstractScreen {

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM d, uuuu");

    public StatusBorder(AbstractScreen screen, Window w) {
        super(w);
        JLabel loginLabel;
        JLabel dateLabel = new JLabel(" " + dateTimeFormatter.format(LocalDateTime.now()));
        User currentUser = LoginManager.inst().getCurrentUser();
        if (currentUser == null) {
            loginLabel = new JLabel(" Not logged in");
        } else if (currentUser instanceof Contact) {
            loginLabel = new JLabel(" " + currentUser.getName() + " logged in as " + currentUser.getTitle() + " for nonprofit " + ((Contact)currentUser).getNonprofitName());
        } else {
            loginLabel = new JLabel(" " + currentUser.getName() + " logged in as " + currentUser.getTitle());
        }
        setLayout(new BorderLayout());
        Box box = Box.createVerticalBox();
        box.add(loginLabel);
        box.add(dateLabel);
        add(box, BorderLayout.NORTH);
        add(screen, BorderLayout.CENTER);
    }
}
