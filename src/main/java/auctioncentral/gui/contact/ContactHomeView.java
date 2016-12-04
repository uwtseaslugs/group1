package auctioncentral.gui.contact;

import auctioncentral.gui.AbstractScreen;
import auctioncentral.model.Auction;
import auctioncentral.model.Calendar;
import auctioncentral.model.Contact;
import auctioncentral.model.LoginManager;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Observable;

public class ContactHomeView extends AbstractScreen {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh a");

    private Auction auction;
    private JLabel auctionLabel;
    private JButton editAuctionButton;
    private JButton addAuctionButton;

    public ContactHomeView() {
        auction = Calendar.inst().getNextAuctionBy((Contact) LoginManager.inst().getCurrentUser());


        editAuctionButton = new JButton("Edit auction");
        addAuctionButton = new JButton("Add new auction");

        if (auction != null) {
            auctionLabel = new JLabel("You have an upcoming auction on " + dateFormat.format(auction.getDate()));
            addAuctionButton.setEnabled(false);
            addAuctionButton.setToolTipText("You can only have one upcoming auction at a time");
        } else {
            auctionLabel = new JLabel("You do not have an upcoming auction");
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
