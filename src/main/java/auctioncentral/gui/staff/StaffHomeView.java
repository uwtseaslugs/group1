package auctioncentral.gui.staff;

import auctioncentral.gui.AbstractScreen;
import auctioncentral.model.Staff;

import javax.swing.*;
import java.util.Observable;

public class StaffHomeView extends AbstractScreen {

    private JButton viewCalendarButton;

    public StaffHomeView() {
        viewCalendarButton = new JButton("View Calendar");

        add(viewCalendarButton);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
