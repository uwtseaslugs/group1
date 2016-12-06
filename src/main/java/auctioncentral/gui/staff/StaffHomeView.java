package auctioncentral.gui.staff;
/**
 * @author Jon
 */

import auctioncentral.gui.*;
import auctioncentral.model.Calendar;
import auctioncentral.model.LoginManager;
import auctioncentral.model.Staff;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Observable;

public class StaffHomeView extends AbstractScreen {

    private JButton viewCalendarButton;
    private JButton AdminButton;
    private JButton exitButton;
    private JLabel StaffName;
    private JLabel currentMaxAuction;
    private JLabel DateLabel;
    private static DateTimeFormatter date = DateTimeFormatter.ofPattern("MMMM d, uuuu");

    public StaffHomeView(Window w) {
        super(w);
        
        StaffName = new JLabel(((Staff) LoginManager.inst().getCurrentUser()).getName());
        currentMaxAuction = new JLabel("<html>Current Auctions: " + Calendar.inst().getAuctionsPastDate(new Date()).size() + "<br>Current Max Auctions allowed<html>: " + Calendar.inst().getMaxAuctions());
        viewCalendarButton = new JButton("View Calendar");
        AdminButton = new JButton("Administrative functions");
        exitButton = new JButton("Exit Auction Central");
        DateLabel = new JLabel(date.format(LocalDateTime.now()));

        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,5,5,5);
        c.fill = GridBagConstraints.HORIZONTAL;

        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.gridx = 0;
        c.gridy = 0;
        gbl.setConstraints(StaffName, c);
        c.gridy = 1;
        gbl.setConstraints(currentMaxAuction,c);
        c.gridy = 2;

        c.weightx = 1.0;
        c.weighty = 1.0;
        gbl.setConstraints(DateLabel,c);

        c.gridx = 3;
        c.weighty = 0.0;
        c.weightx = 0.0;
        c.anchor = GridBagConstraints.LAST_LINE_END;
        c.gridy = 4;
        gbl.setConstraints(viewCalendarButton, c);
        c.gridy = 5;
        gbl.setConstraints(AdminButton, c);
        c.gridy = 6;
        gbl.setConstraints(exitButton, c);

        add(currentMaxAuction);
        add(StaffName);
        add(DateLabel);
        add(viewCalendarButton);
        add(AdminButton);
        add(exitButton);

        AdminButton.addActionListener(e -> {
                getRoot().addScreen(new StaffAdminMenu(getRoot()));
        });

        exitButton.addActionListener(a -> System.exit(0));
    }
}
