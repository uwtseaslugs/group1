package auctioncentral.gui.contact;

import auctioncentral.gui.AbstractScreen;
import auctioncentral.model.Auction;
import auctioncentral.model.Calendar;
import auctioncentral.model.Contact;
import auctioncentral.model.LoginManager;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Observable;

public class ContactHomeView extends AbstractScreen {

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM d, uuuu");

    private Auction auction;
    private JLabel auctionLabel;
    private JButton editAuctionButton;
    private JButton addAuctionButton;

    public ContactHomeView() {
        auction = Calendar.inst().getNextAuctionBy((Contact) LoginManager.getInstance().getCurrentUser());


        editAuctionButton = new JButton("Edit auction");
        addAuctionButton = new JButton("Add new auction");

        if (auction != null) {
            auctionLabel = new JLabel("You have an upcoming auction on " + dateTimeFormatter.format(LocalDateTime.now()));
        } else {
            auctionLabel = new JLabel("You do not have any upcoming auctions");
            editAuctionButton.setEnabled(false);
        }

        add(auctionLabel);
        add(editAuctionButton);
        add(addAuctionButton);

        editAuctionButton.addActionListener(e -> {
            getRoot().addScreen(new ContactEditAuctionView(auction));
        });
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
