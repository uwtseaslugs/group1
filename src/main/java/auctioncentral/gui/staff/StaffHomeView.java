package auctioncentral.gui.staff;

import auctioncentral.gui.AbstractScreen;
import auctioncentral.model.Staff;

import javax.swing.*;
import java.util.Observable;

public class StaffHomeView extends AbstractScreen {

    private JButton viewCalendarButton;
    private JButton AdminButton;

    public StaffHomeView() {
        viewCalendarButton = new JButton("View Calendar");
        AdminButton = new JButton("Administrative functions");

        add(viewCalendarButton);
        add(AdminButton);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
