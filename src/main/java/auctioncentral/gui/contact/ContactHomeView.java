package auctioncentral.gui.contact;

import auctioncentral.gui.AbstractScreen;
import auctioncentral.gui.StatusBorder;
import auctioncentral.model.Auction;
import auctioncentral.model.Calendar;
import auctioncentral.model.Contact;
import auctioncentral.model.LoginManager;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Observable;

/**
 * @author Hunter
 */
public class ContactHomeView extends AbstractScreen {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh a");

    private Auction auction;
    private JLabel auctionLabel;
    private JButton editAuctionButton;
    private JButton addAuctionButton;

    public ContactHomeView() {
        auction = Calendar.inst().getNextAuctionBy((Contact) LoginManager.inst().getCurrentUser());


        editAuctionButton = new JButton("Edit auction");
        addAuctionButton = new JButton("Request new auction");

        if (auction != null) {
            auctionLabel = new JLabel("You have an upcoming auction on " + dateFormat.format(auction.getDate()));
            addAuctionButton.setEnabled(false);
            addAuctionButton.setToolTipText("You can only have one upcoming auction at a time");
        } else {
            auctionLabel = new JLabel("You do not have an upcoming auction");
            editAuctionButton.setEnabled(false);
        }

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;

        add(auctionLabel, c);
        add(Box.createRigidArea(new Dimension(0, 10)), c);
        add(editAuctionButton, c);
        add(Box.createRigidArea(new Dimension(0, 10)), c);
        add(addAuctionButton, c);

        editAuctionButton.addActionListener(e -> {
            getRoot().addScreen(new StatusBorder(new ContactEditAuctionView(auction)));
        });
        addAuctionButton.addActionListener(e -> {
            getRoot().addScreen(new StatusBorder(new ContactAddAuctionView()));
        });

    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
