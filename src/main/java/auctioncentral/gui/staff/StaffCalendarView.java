package auctioncentral.gui.staff;

import auctioncentral.gui.AbstractScreen;
import auctioncentral.gui.Window;
import auctioncentral.model.Calendar;
import auctioncentral.view.CalendarView;

import javax.swing.*;
import java.awt.*;

public class StaffCalendarView extends AbstractScreen {
    public StaffCalendarView(Window theRoot) {
        super(theRoot);

        JTextArea textArea = new JTextArea();
        CalendarView cv = new CalendarView(Calendar.inst());
        cv.show();
        textArea.setText(cv.getText());
        textArea.setEditable(false);
        textArea.setFont(new Font("monospaced", Font.PLAIN, 14));

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        add(textArea, c);
        JButton back = new JButton("Back");
        back.addActionListener(a -> getRoot().addScreen(new StaffHomeView(getRoot())));
        add(Box.createRigidArea(new Dimension(0, 30)), c);
        add(back, c);
    }
}
