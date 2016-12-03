package auctioncentral.gui.staff;


import auctioncentral.gui.*;
import auctioncentral.model.Calendar;
import auctioncentral.model.LoginManager;
import auctioncentral.model.Staff;
import auctioncentral.gui.Window;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;

public class StaffHomeView extends AbstractScreen {

    private JButton viewCalendarButton;
    private JButton AdminButton;
    private JLabel StaffName;
    private JLabel currentMaxAuction;
    public StaffHomeView() {
        StaffName = new JLabel(((Staff) LoginManager.getInstance().getCurrentUser()).getName());
        currentMaxAuction = new JLabel("Current Max Auctions allowed: " + Calendar.inst().getMaxAuctions());
        viewCalendarButton = new JButton("View Calendar");
        AdminButton = new JButton("Administrative functions");


        add(currentMaxAuction);

        add(StaffName);

        add(viewCalendarButton);

        add(AdminButton);

        AdminButton.addActionListener(e -> {
           getRoot().addScreen(new StaffAdminMenu());
        });
    }

    @Override
    public void update(Observable o, Object arg) {


    }
}
